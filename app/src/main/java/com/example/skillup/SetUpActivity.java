package com.example.skillup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skillup.databinding.ActivitySetUpBinding;
import com.example.skillup.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SetUpActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    ActivitySetUpBinding binding;

    Uri imageUri;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorageRef;

    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();
        StorageRef = FirebaseStorage.getInstance().getReference().child("ProfileImage");

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for getting images
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {

        String userName = binding.name.getText().toString();
        String city = binding.cityId.getText().toString();
        String country = binding.country.getText().toString();
        String profession = binding.proffesion.getText().toString();

        if(userName.isEmpty() || userName.length()<3){
            showError(binding.name,"Username is not valid");
        }
        else if(city.isEmpty()){
            showError(binding.cityId,"City is not valid");
        }
        else if(country.isEmpty()){
            showError(binding.country,"Country is not valid");
        }
        else if(profession.isEmpty() || profession.length()<3){
            showError(binding.proffesion,"Profession is not valid");
        }
        else if(imageUri==null){
            Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();
        }
        else{
            mLoadingBar =  new ProgressDialog(this);
            mLoadingBar.setTitle("Adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            //adding the image
            StorageRef.child(mUser.getUid()).putFile(imageUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull  Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        //getting the url of the place where image is stored
                        StorageRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {  //Image successfully stored
                                Users user = new Users(userName,city,country,profession,uri.toString(),"Offline",true);

                                //adding user under "Users" directory
                                mRef.child("Users").child(mUser.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        mLoadingBar.dismiss();

                                        Intent intent = new Intent(SetUpActivity.this,AllActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(SetUpActivity.this, "Setup Profile Completed", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(SetUpActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        mLoadingBar.dismiss();
                        Toast.makeText(SetUpActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }else{
                        mLoadingBar.dismiss();
                        Toast.makeText(SetUpActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            binding.profileImage.setImageURI(imageUri);
        }
    }
}