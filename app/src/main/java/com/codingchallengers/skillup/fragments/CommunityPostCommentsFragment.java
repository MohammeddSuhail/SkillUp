package com.codingchallengers.skillup.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codingchallengers.skillup.Adapter.CommentViewHolder;
import com.codingchallengers.skillup.Adapter.PostAdapter;
import com.codingchallengers.skillup.ImageViewActivity;
import com.codingchallengers.skillup.R;
import com.codingchallengers.skillup.model.Comment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommunityPostCommentsFragment extends Fragment {

    String postId, com;
    DatabaseReference PostRef, likeRef, commentsRef;
    LinearLayout backToCommunityPost;
    ImageView postImage2, likeImage2, commentSend2, addCommentProfilePic;
    CardView cv_postImage2;
    TextView profileUsername, timeAgo, postDesc;
    CircleImageView profileImagePost;
    TextView likeCounter2, commentCounter2;
    EditText inputComment2;

    int totalComs;
    String username, userProfileImage, datePost, postImageUrl, postDec;

    FirebaseRecyclerOptions<Comment> CommentOption;
    FirebaseRecyclerAdapter<Comment, CommentViewHolder> CommentAdapter;

    public RecyclerView recyclerViewCom;


    public CommunityPostCommentsFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community_post_comments, container, false);

        postId = getArguments().getString("postId");
        com = getArguments().getString("com");

        postImage2 = view.findViewById(R.id.postImage2);
        cv_postImage2 = view.findViewById(R.id.cv_postImage2);
        profileImagePost = view.findViewById(R.id.profileImagePost2);
        profileUsername = view.findViewById(R.id.profileUsername2);
        timeAgo = view.findViewById(R.id.timeAgo2);
        postDesc = view.findViewById(R.id.postDesc2);
        backToCommunityPost = view.findViewById(R.id.backToCommunityPost);
        likeImage2 = view.findViewById(R.id.likeImage2);
        likeCounter2 = view.findViewById(R.id.likeCounter2);
        commentCounter2 = view.findViewById(R.id.commentCounter2);
        commentSend2 = view.findViewById(R.id.commentSend2);
        inputComment2 = view.findViewById(R.id.inputComment2);
        recyclerViewCom = view.findViewById(R.id.recyclerViewComments2);
        addCommentProfilePic = view.findViewById(R.id.addCommentProfilePic);

        PostRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        likeRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        commentsRef = FirebaseDatabase.getInstance().getReference().child("Comments");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        PostRef.child(com).child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username = snapshot.child("username").getValue().toString();
                userProfileImage = snapshot.child("userProfileImage").getValue().toString();
                datePost = snapshot.child("datePost").getValue().toString();
                postDec = snapshot.child("postDec").getValue().toString();
                if(snapshot.child("postImageUrl").exists()){
                    postImageUrl = snapshot.child("postImageUrl").getValue().toString();
                }

                if(postImageUrl == null){
                    cv_postImage2.setVisibility(View.GONE);
                }
                else{
                    Picasso.get().load(postImageUrl).into(postImage2);
                }

                Picasso.get().load(userProfileImage).into(profileImagePost);
                profileUsername.setText(username);
                timeAgo.setText(getTimeAgo(datePost));
                postDesc.setText(postDec);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Picasso.get().load(EachCommunityFragment.profileImageUrlV).into(addCommentProfilePic);

        postImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.putExtra("url",postImageUrl);
                v.getContext().startActivity(intent);
            }
        });

        backToCommunityPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        //for changing the color of like adding and removing the likes(as well as user) from Likes folder in firebase
        likeImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeRef.child(postId).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            //if user clicks like button, but if he has already had pressed like button for that post,
                            //then second time pressing of like button means removing the like
                            likeRef.child(postId).child(userId).removeValue();
                            likeImage2.setColorFilter(Color.GRAY);
                        }else{
                            //user clicking the like button for fist time for a post
                            likeRef.child(postId).child(userId).setValue("like");
                            likeImage2.setColorFilter(Color.GREEN);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(v.getContext(), ""+error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //to count the likes and change its color
        countLikes(postId,userId,likeRef);

        //loading comment
        loadComment(postId);
        //counting and setting commentCounter
        countComments(postId,userId);

        commentSend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = inputComment2.getText().toString();
                if(comment.isEmpty()){
                    Toast.makeText(v.getContext(), "Enter the comment",Toast.LENGTH_SHORT).show();
                }else{
                    addComment(postId,commentsRef,userId,comment);
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
                        EachCommunityFragment eachCommunityFragment = new EachCommunityFragment();
                        Bundle args = new Bundle();
                        args.putString("com",com);
                        eachCommunityFragment.setArguments(args);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    private String getTimeAgo(String datePost) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long time = sdf.parse(datePost).getTime();
//            long now = System.currentTimeMillis();
            Date date = new Date();
            String strDate = sdf.format(date);
            long now = sdf.parse(strDate).getTime();
            CharSequence ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            return ago+"";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void countLikes(String postId, String userId, DatabaseReference likeRef) {

        likeRef.child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //for setting likes
                if (snapshot.exists()){
                    int totalLikes = (int) snapshot.getChildrenCount();
                    likeCounter2.setText(totalLikes+"");
                }else{
                    likeCounter2.setText("0");
                }

                //for setting color of likes
                if (snapshot.child(userId).exists()){
                    likeImage2.setColorFilter(Color.GREEN);
                }else{
                    likeImage2.setColorFilter(Color.GRAY);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }

    public void countComments(String postId, String userId) {
        commentsRef.child(postId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    totalComs = (int) snapshot.getChildrenCount();
                    commentCounter2.setText(""+totalComs);
                }else{
                    commentCounter2.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void loadComment(String postKey) {
        recyclerViewCom.setLayoutManager(new LinearLayoutManager(recyclerViewCom.getContext()));
        CommentOption = new FirebaseRecyclerOptions.Builder<Comment>().setQuery(commentsRef.child(postKey), Comment.class).build();
        CommentAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(CommentOption) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CommentViewHolder holder, int position, @NonNull @NotNull Comment model) {
                Picasso.get().load(model.getProfileImage()).into(holder.profileImage);
                holder.username.setText(model.getUsername());
                holder.comment.setText(model.getComment());
                holder.timeAgo.setText(getTimeAgo(model.getCommentDate()));
            }

            @NonNull
            @NotNull
            @Override
            public CommentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_comment, parent, false);
                return new CommentViewHolder(view);
            }
        };
        CommentAdapter.startListening();
        recyclerViewCom.setAdapter(CommentAdapter);
    }

    private void addComment(String postId, DatabaseReference commentsRef, String userId, String comment) {

        Date date1 = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate1 = formatter1.format(date1);

        HashMap hashMap = new HashMap();
        hashMap.put("username", EachCommunityFragment.usernameV);
        hashMap.put("profileImage", EachCommunityFragment.profileImageUrlV);
        hashMap.put("comment",comment);
        hashMap.put("userId",userId);
        hashMap.put("commentDate",strDate1);

        commentsRef.child(postId).child(strDate1+userId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull @NotNull Task task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(),"Comment Added",Toast.LENGTH_SHORT).show();

                    //to hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputComment2.getWindowToken(), 0);

                    CommentAdapter.notifyItemInserted(totalComs);
                    inputComment2.setText(null);

                    //To scroll down the recyclerview to show the newly added comment
                    recyclerViewCom.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewCom.scrollToPosition(recyclerViewCom.getAdapter().getItemCount() - 1);
                        }
                    }, 2000); // 2 second delay
                }else{
                    Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}