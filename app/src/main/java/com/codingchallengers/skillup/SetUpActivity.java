package com.codingchallengers.skillup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingchallengers.skillup.databinding.ActivitySetUpBinding;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    Bitmap bmp;
    ByteArrayOutputStream baos;

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

        binding.addProfileImage.setOnClickListener(new View.OnClickListener() {
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

        fullName = binding.name.getText().toString().trim();
        yearOfGrad = binding.graduationYear.getText().toString().trim();
        course = binding.course.getText().toString().trim();
        branch = binding.branch.getText().toString().trim();
        currYear = binding.currYear.getText().toString().trim();
        profession = binding.profession.getText().toString().trim();


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
           else{
                imageUri = Uri.parse("android.resource://com.codingchallengers.skillup/drawable/user_icon_default_dark");
                addUserWithoutImage();
           }
        }
    }

    private void addUserAlongWithImage() {
        //adding the image
        byte[] fileInBytes = baos.toByteArray();
        StorageRef.child(mUser.getUid()).putBytes(fileInBytes)
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
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                            Toast.makeText(SetUpActivity.this, "Done. Thank you!", Toast.LENGTH_SHORT).show();
                        }else{
                            mLoadingBar.dismiss();
                            Toast.makeText(SetUpActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUserWithoutImage() {
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
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
                            Toast.makeText(SetUpActivity.this, "Done. Thank you!", Toast.LENGTH_SHORT).show();
                        }else{
                            mLoadingBar.dismiss();
                            Toast.makeText(SetUpActivity.this, "Not Done", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean CheckAllFields() {
        if(fullName.isEmpty()){
            showError(binding.name,"This field is required!");
            return false;
        }
        else if(fullName.length()<3){
            showError(binding.name,"Username is not valid");
            return false;
        }
        else if(yearOfGrad.isEmpty()){
            showError(binding.graduationYear,"This field is required!");
            return false;
        }
        else if(yearOfGrad.length()<4){
            showError(binding.graduationYear,"Year of Graduation is not valid");
            return false;
        }
        else if(course.isEmpty()){
            showError(binding.course,"This field is required!");
            return false;
        }
        else if(course.length()<2){
            showError(binding.course,"Course is not valid");
            return false;
        }
        else if(branch.isEmpty()){
            showError(binding.branch,"This field is required!");
            return false;
        }
        else if(branch.length()<2){
            showError(binding.branch,"Branch is not valid");
            return false;
        }
        else if(currYear.isEmpty()){
            showError(binding.currYear,"This field is required!");
            return false;
        }
        else if(profession.isEmpty()){
            showError(binding.profession,"This field is required!");
            return false;
        }
        else if(profession.length()<3){
            showError(binding.profession,"Profession is not valid");
            return false;
        }
        /*else if(imageUri==null){
            Toast.makeText(this,"Please select an image",Toast.LENGTH_SHORT).show();
            return false;
        }*/
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

            //steps to compress image before uploading
            bmp = null;
            try {
                bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            baos = new ByteArrayOutputStream();

            //here you can choose quality factor in third parameter(ex. i chosen 25)
            bmp.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        }
    }
}