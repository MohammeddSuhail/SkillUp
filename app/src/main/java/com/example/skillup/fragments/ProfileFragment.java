package com.example.skillup.fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
    /*EditText inputUsername,inputCity,inputCountry,inputProfession;
    Button btnUpdate;*/
    TextView tv_userName,tv_usn,tv_phone,tv_yearOfGrad, tv_course, tv_branch, tv_currYear, tv_profession;


    Uri imageUri;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    StorageReference StorageRef;

    ProgressDialog mLoadingBar;

    DatabaseReference mRef;

    String userName,yearOfGrad,course,branch,currYear,profession, profileImageUrl;
    String usn, phoneNo;

    CardView cv_userName,cv_usn,cv_phone,cv_yearOfGrad, cv_course, cv_branch, cv_currYear, cv_profession;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);



        //getActivity().getSupportFragmentManager().popBackStack();
        //CircleImageView profileImage = view.findViewById(R.id.profile_pic_header);

        /*profileImageView = view.findViewById(R.id.circleImageView);
        inputUsername = view.findViewById(R.id.inputUsername);
        inputCity = view.findViewById(R.id.inputCity);
        inputCountry = view.findViewById(R.id.inputCountry);
        inputProfession = view.findViewById(R.id.inputProfession);
        btnUpdate = view.findViewById(R.id.btnUpdate);*/

        profileImageView = view.findViewById(R.id.profileImage);
        tv_userName = view.findViewById(R.id.tv_userName);
        tv_usn = view.findViewById(R.id.tv_usn);
        tv_phone = view.findViewById(R.id.tv_phone);
        tv_yearOfGrad = view.findViewById(R.id.tv_yearOfGrad);
        tv_course = view.findViewById(R.id.tv_course);
        tv_branch = view.findViewById(R.id.tv_branch);
        tv_currYear = view.findViewById(R.id.tv_currYear);
        tv_profession = view.findViewById(R.id.tv_profession);

        cv_userName = view.findViewById(R.id.cv_userName);
        cv_usn = view.findViewById(R.id.cv_usn);
        cv_phone = view.findViewById(R.id.cv_phone);
        cv_yearOfGrad = view.findViewById(R.id.cv_yearOfGrad);
        cv_course = view.findViewById(R.id.cv_course);
        cv_branch = view.findViewById(R.id.cv_branch);
        cv_currYear = view.findViewById(R.id.cv_currYear);
        cv_profession = view.findViewById(R.id.cv_profession);

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
                    profileImageUrl = snapshot.child("profileImage").getValue().toString();
                    //String city = snapshot.child("city").getValue().toString();
                 // String country = snapshot.child("country").getValue().toString();
                    userName = snapshot.child("userName").getValue().toString();
                    try {
                        usn = snapshot.child("usn").getValue().toString();
                    }
                    catch (Exception e){
                        usn=" ";
                    }
                    try {
                        phoneNo = snapshot.child("phoneNo").getValue().toString();
                    }
                    catch (Exception e){
                        phoneNo="";
                    }
                    yearOfGrad = snapshot.child("yearOfGrad").getValue().toString();
                    course = snapshot.child("course").getValue().toString();
                    branch = snapshot.child("branch").getValue().toString();
                    currYear = snapshot.child("currYear").getValue().toString();
                    profession = snapshot.child("profession").getValue().toString();

                    Picasso.get().load(profileImageUrl).into(profileImageView);
                    tv_userName.setText(userName);
                    tv_usn.setText(usn);
                    tv_phone.setText(phoneNo);
                    tv_yearOfGrad.setText(yearOfGrad);
                    tv_course.setText(course);
                    tv_branch.setText(branch);
                    tv_currYear.setText(currYear);
                    tv_profession.setText(profession);
                }else{
                    Toast.makeText(getContext(),"Data doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        EachProfileUpdateFragment eachProfileUpdateFragment = new EachProfileUpdateFragment();
        Bundle args = new Bundle();

        cv_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Name");
                args.putString("et_name",userName);
                args.putString("db_child_name","userName");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_usn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","USN");
                args.putString("et_name",usn);
                args.putString("db_child_name","usn");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Phone No.");
                args.putString("et_name",phoneNo);
                args.putString("db_child_name","phoneNo");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_yearOfGrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Year of Graduation");
                args.putString("et_name",yearOfGrad);
                args.putString("db_child_name","yearOfGrad");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Course of Study");
                args.putString("et_name",course);
                args.putString("db_child_name","course");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Branch of Study");
                args.putString("et_name",branch);
                args.putString("db_child_name","branch");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_currYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Current Year of Study");
                args.putString("et_name",currYear);
                args.putString("db_child_name","currYear");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });

        cv_profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("tv_name","Profession");
                args.putString("et_name",profession);
                args.putString("db_child_name","profession");
                eachProfileUpdateFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachProfileUpdateFragment).commit();
            }
        });


/*
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
        });*/



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


            mLoadingBar =  new ProgressDialog(getContext());
            mLoadingBar.setTitle("Updating Profile image");
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
            if(url[0] != null){
                //updating profileImage url, if the image was successfully stored
                //hashMap.put("profileImage", url[0]);
                mUserRef.child(mUser.getUid()).child("profileImage").setValue(url[0]).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(),"Profile image updated successfully",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


}
