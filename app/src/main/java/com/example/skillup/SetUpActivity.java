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

import java.util.HashMap;

public class SetUpActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    ActivitySetUpBinding binding;

    Uri imageUri;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorageRef;

    boolean isAllFieldsChecked = false;
    String fullName, yearOfGrad, course, branch, currYear, profession;

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
                checkAndSaveData();
            }
        });
    }

    private void checkAndSaveData() {

        fullName = binding.name.getText().toString();
        yearOfGrad = binding.graduationYear.getText().toString();
        course = binding.course.getText().toString();
        branch = binding.branch.getText().toString();
        currYear = binding.currYear.getText().toString();
        profession = binding.profession.getText().toString();

        // store the returned value of the dedicated function which checks
        // whether the entered data is valid or if any fields are left blank.
        isAllFieldsChecked = CheckAllFields();


        if(isAllFieldsChecked){
            mLoadingBar =  new ProgressDialog(this);
            mLoadingBar.setTitle("Adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            if(imageUri!=null){
                addUserAlongWithImage();
            }
           /* else{
                addUserWithoutImage();
            }*/
        }
    }


    private void addUserAlongWithImage() {
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
                                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                                    hashMap.put("userName",fullName);
                                    hashMap.put("yearOfGrad",yearOfGrad);
                                    hashMap.put("course",course);
                                    hashMap.put("branch",branch);
                                    hashMap.put("currYear",currYear);
                                    hashMap.put("profession",profession);
                                    hashMap.put("profileImage",uri.toString());
                                    hashMap.put("status","Offline");
                                    hashMap.put("setupFlag",true);

                                    //adding user under "Users" directory
                                    mRef.child("Users").child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
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

   /* private void addUserWithoutImage() {
        Users user = new Users(fullName,yearOfGrad,course,branch,currYear,profession,city,null,"Offline",true);

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
    }*/

    private boolean CheckAllFields() {
        if(fullName.isEmpty() || fullName.length()<3){
            showError(binding.name,"Username is not valid");
            return false;
        }
        else if(yearOfGrad.isEmpty() || yearOfGrad.length()<4){
            showError(binding.graduationYear,"Year of Graduation is not valid");
            return false;
        }
        else if(course.isEmpty() || course.length()<2){
            showError(binding.course,"Course is not valid");
            return false;
        }
        else if(branch.isEmpty() || branch.length()<2){
            showError(binding.branch,"Branch is not valid");
            return false;
        }
        else if(currYear.isEmpty()){
            showError(binding.currYear,"This field is required!");
            return false;
        }
        else if(profession.isEmpty() || profession.length()<3){
            showError(binding.profession,"Profession is not valid");
            return false;
        }
        else if(imageUri==null){
            Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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