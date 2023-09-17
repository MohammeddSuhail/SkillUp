package com.codingchallengers.skillup.fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingchallengers.skillup.Adapter.PostAdapter;
import com.codingchallengers.skillup.R;
import com.codingchallengers.skillup.model.Posts;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EachCommunityFragment extends Fragment {

    private static final int REQUEST_CODE = 101;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef,PostRef;
    StorageReference postImgRef;

    String com;

    ProgressDialog mLoadingBar;

    Uri imageUri;
    public static String profileImageUrlV,usernameV,postUsn;

    RecyclerView recyclerView;

    PostAdapter adapter;

    Bitmap bmp;
    ByteArrayOutputStream baos;

    //for 'add post' dialog box
    TextView communityTitle;
    CardView openNewPostDialog, postUpload, postCancel, cv_PostImageDisplay;
    EditText newPostDesc;
    ImageView newPostImage, newPostImageDisplay;
    String postDescStr;
    Dialog dialog;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.each_community_fragment, container, false);

        com = getArguments().getString("com");

        communityTitle = view.findViewById(R.id.communityTitle);
        openNewPostDialog = view.findViewById(R.id.openNewPostDialog);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        postImgRef = FirebaseStorage.getInstance().getReference().child("PostImage");
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mLoadingBar =  new ProgressDialog(getContext());


        //Below 3 lines for Firebase RecycleView
        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Posts> options = new FirebaseRecyclerOptions.Builder<Posts>().setQuery(PostRef.child(com),Posts.class).build();

        if(com.equals("DSA"))
            communityTitle.setText("App Updates");
        else if(com.equals("OOPS"))
            communityTitle.setText("DSA and OOPS");
        else if(com.equals("CN"))
            communityTitle.setText("Placement Corner");
        else if(com.equals("OS"))
            communityTitle.setText("Conceptual Doubts");
        else if(com.equals("DBMS"))
            communityTitle.setText("Seniors");
        else
            communityTitle.setText(com);

        openNewPostDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatePost();
            }
        });


        //for getting username and url of the profile, which will be used while creating post object
        mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profileImageUrlV = snapshot.child("profileImage").getValue().toString();
                usernameV = snapshot.child("userName").getValue().toString();
                postUsn = snapshot.child("usn").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Sorry Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new PostAdapter(options);
        recyclerView.setAdapter(adapter);






        //back pressed
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CommunityFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });



        return view;
    }

    private void CreatePost() {
        dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.add_post_layout);
        dialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.add_post_dialog_bg));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        //dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        postUpload = dialog.findViewById(R.id.postUpload);
        postCancel = dialog.findViewById(R.id.postCancel);
        newPostDesc = dialog.findViewById(R.id.new_post_desc);
        newPostImage = dialog.findViewById(R.id.new_post_image);
        newPostImageDisplay = dialog.findViewById(R.id.new_post_img_display);
        cv_PostImageDisplay = dialog.findViewById(R.id.cv_postImageDisplay);

        cv_PostImageDisplay.setVisibility(View.GONE);

        newPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        postUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDescStr = newPostDesc.getText().toString();
                if(postDescStr.equals("")){
                    Toast.makeText(getContext(),"Please give description for your post",Toast.LENGTH_SHORT).show();
                }
                else{
                    AddPost();
                }
            }
        });

        postCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }



    private void AddPost() {

        mLoadingBar.setTitle("Posting");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatter.format(date);

        String finalPostDesc = postDescStr ;

        //New Code
        if(imageUri != null){

            byte[] fileInBytes = baos.toByteArray();
            postImgRef.child(strDate+mUser.getUid()).putBytes(fileInBytes).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if(task.isSuccessful()){//image successfully added to firebase
                        //getting the url of the place where image is stored
                        postImgRef.child(strDate+mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                //storing in the real-time database
                                HashMap hashMap = new HashMap();
                                hashMap.put("datePost",strDate);
                                hashMap.put("postImageUrl",uri.toString());
                                hashMap.put("postDec", finalPostDesc);
                                hashMap.put("userProfileImage",profileImageUrlV);
                                hashMap.put("username",usernameV);
                                hashMap.put("userId",mUser.getUid());
                                hashMap.put("com",com);
                                hashMap.put("postUsn",postUsn);

                                PostRef.child(com).child(strDate+mUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful()){
                                            mLoadingBar.dismiss();
                                            dialog.dismiss();
                                            imageUri = null;

                                            adapter.notifyItemInserted(recyclerView.getAdapter().getItemCount());

                                            //To scroll down the recyclerview to show the newly added comment
                                            recyclerView.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                                                    Toast.makeText(getContext(),"Post added",Toast.LENGTH_SHORT).show();
                                                }
                                            }, 1000); // 2 second delay
                                        }
                                        else{
                                            mLoadingBar.dismiss();
                                            dialog.dismiss();
                                            imageUri = null;
                                            Toast.makeText(getContext(),""+task.getException().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    }else{
                        mLoadingBar.dismiss();
                        dialog.dismiss();
                        imageUri = null;
                        Toast.makeText(getContext(),""+task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else{

            //without image post
            HashMap hashMap = new HashMap();
            hashMap.put("datePost",strDate);
            hashMap.put("postImageUrl",null);
            hashMap.put("postDec", finalPostDesc);
            hashMap.put("userProfileImage",profileImageUrlV);
            hashMap.put("username",usernameV);
            hashMap.put("userId",mUser.getUid());
            hashMap.put("com",com);
            hashMap.put("postUsn",postUsn);

            PostRef.child(com).child(strDate+mUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()){
                        mLoadingBar.dismiss();
                        dialog.dismiss();

                        adapter.notifyItemInserted(recyclerView.getAdapter().getItemCount());

                        //To scroll down the recyclerview to show the newly added comment
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                                Toast.makeText(getContext(),"Post added",Toast.LENGTH_SHORT).show();
                            }
                        }, 1000); // 1 second delay
                    }
                    else{
                        mLoadingBar.dismiss();
                        dialog.dismiss();
                        Toast.makeText(getContext(),""+task.getException().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //for getting image from gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            imageUri = data.getData();
            cv_PostImageDisplay.setVisibility(View.VISIBLE);
            newPostImageDisplay.setImageURI(imageUri);

            //steps to compress image before uploading
            bmp = null;
            try {
                bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            baos = new ByteArrayOutputStream();

            //here you can choose quality factor in third parameter(ex. i chosen 25)
            bmp.compress(Bitmap.CompressFormat.JPEG, 25, baos);
        }
    }

}
