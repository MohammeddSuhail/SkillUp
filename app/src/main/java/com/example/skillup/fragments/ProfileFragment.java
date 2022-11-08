package com.example.skillup.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillup.AllActivity;
import com.example.skillup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_CODE = 101;

    CircleImageView profileImageView;
    EditText inputUsername,inputCity,inputCountry,inputProfession;
    Button btnUpdate;

    Uri imageUri;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    StorageReference StorageRef;

    ProgressDialog mLoadingBar;

    DatabaseReference mRef;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);


        //getActivity().getSupportFragmentManager().popBackStack();
        //CircleImageView profileImage = view.findViewById(R.id.profile_pic_header);

        profileImageView = view.findViewById(R.id.circleImageView);
        inputUsername = view.findViewById(R.id.inputUsername);
        inputCity = view.findViewById(R.id.inputCity);
        inputCountry = view.findViewById(R.id.inputCountry);
        inputProfession = view.findViewById(R.id.inputProfession);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        StorageRef = FirebaseStorage.getInstance().getReference().child("ProfileImage");

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for getting images
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE);
            }
        });



        //getting the user details and putting in the views of profile
        mUserRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String profileImageUrl = snapshot.child("profileImage").getValue().toString();
                    String city = snapshot.child("city").getValue().toString();
                    String country = snapshot.child("country").getValue().toString();
                    String profession = snapshot.child("profession").getValue().toString();
                    String username = snapshot.child("userName").getValue().toString();

                    Picasso.get().load(profileImageUrl).into(profileImageView);
                    inputCity.setText(city);
                    inputUsername.setText(username);
                    inputCountry.setText(country);
                    inputProfession.setText(profession);
                }else{
                    Toast.makeText(getContext(),"Data doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });



        //updating profile
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = inputCity.getText().toString();
                String country = inputCountry.getText().toString();
                String profession = inputProfession.getText().toString();
                String username = inputUsername.getText().toString();

                if(imageUri == null){
                    //if no profileImage update is needed
                    //userName,city,country,profession,status, profileImage,interest;
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    hashMap.put("city",city);
                    hashMap.put("country",country);
                    hashMap.put("profession",profession);
                    hashMap.put("userName",username);

                    mUserRef.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                            //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                        }
                    });
                }else{
                    //if profileImage update is required
                    mLoadingBar =  new ProgressDialog(getContext());
                    mLoadingBar.setTitle("Updating Profile");
                    mLoadingBar.setCanceledOnTouchOutside(false);
                    mLoadingBar.show();

                    //adding the image
                    final String[] url = {null};
                    StorageRef.child(mUser.getUid()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if(task.isSuccessful())
                                    {
                                        //getting the url of the place where image is stored
                                        StorageRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {  //Image successfully stored
                                                    url[0] = uri.toString();
                                            }
                                        });
                                        mLoadingBar.dismiss();
                                    }else{
                                        mLoadingBar.dismiss();
                                        Toast.makeText(getContext(), "Profile Image Update Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                    });

                    //userName,city,country,profession,status, profileImage,interest;
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("city",city);
                    hashMap.put("country",country);
                    hashMap.put("profession",profession);
                    hashMap.put("userName",username);
                    if(url[0] != null){
                        //updating profileImage url, if the image was successfully stored
                        hashMap.put("profileImage", url[0]);
                    }

                    mUserRef.child(mUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                            //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                        }
                    });


                    //setting pic
                    //Picasso.get().load(url[0]).into(AllActivity.profileImage);

                }





            }
        });



        //back pressed
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }


}
