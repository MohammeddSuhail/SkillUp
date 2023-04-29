package com.codingchallengers.skillup.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingchallengers.skillup.ImageViewActivity;
import com.codingchallengers.skillup.R;
import com.codingchallengers.skillup.fragments.CommunityPostCommentsFragment;
import com.codingchallengers.skillup.fragments.EachCommunityFragment;
import com.codingchallengers.skillup.model.Comment;
import com.codingchallengers.skillup.model.Posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class PostAdapter extends FirebaseRecyclerAdapter<Posts, PostAdapter.MyViewHolder> {

    DatabaseReference likeRef;
    DatabaseReference commentsRef, postRef;

    Context context;
    FragmentManager manager;

    //public static final int MATCH_PARENT;


    public PostAdapter(@NonNull FirebaseRecyclerOptions<Posts> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NotNull Posts model) {
        //loading the post
        final String postKey = getRef(position).getKey();
        String com = model.getCom();

        holder.postDesc.setText(model.getPostDec());

        String timeAgo = getTimeAgo(model.getDatePost());
        holder.timeAgo.setText(timeAgo);

        holder.username.setText(model.getUsername());

        //some changes with or without image
        if(model.getPostImageUrl() == null){
            holder.cv_postImage.setVisibility(View.GONE);
        }else{
            Picasso.get().load(model.getPostImageUrl()).fit().centerCrop().into(holder.postImage);
        }

        //loading the user profile pic on post
        Picasso.get().load(model.getUserProfileImage()).fit().centerCrop().into(holder.profileImage);

        holder.postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.putExtra("url",model.getPostImageUrl());
                v.getContext().startActivity(intent);
            }
        });



        //creating Likes folder in database
        likeRef = FirebaseDatabase.getInstance().getReference().child("Likes");
        commentsRef = FirebaseDatabase.getInstance().getReference().child("Comments");
        postRef = FirebaseDatabase.getInstance().getReference().child("Posts");
        //getting id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(!userId.equals(model.getUserId())){
            holder.btnMenu.setVisibility(View.GONE);
        }

        // Attach a PopupMenu to the button view
        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.community_post_menu);

                // Set an OnMenuItemClickListener for the menu options
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.action_delete) {
                            // Handle the Delete option
                            // Create a new AlertDialog.Builder object
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);

                            builder.setTitle("Delete Post")
                                    .setMessage("Are you sure you want to delete this post?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            DeletePost(com, postKey);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do nothing and dismiss the dialog box
                                            dialog.dismiss();
                                        }
                                    });

                            AlertDialog dialog = builder.create();
                            dialog.show();

                            return true;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        //for changing the color of like adding and removing the likes(as well as user) from Likes folder in firebase
        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeRef.child(postKey).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            //if user clicks like button, but if he has already had pressed like button for that post,
                            //then second time pressing of like button means removing the like
                            likeRef.child(postKey).child(userId).removeValue();
                            holder.likeImage.setColorFilter(Color.GRAY);
                        }else{
                            //user clicking the like button for fist time for a post
                            likeRef.child(postKey).child(userId).setValue("like");
                            holder.likeImage.setColorFilter(Color.GREEN);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(v.getContext(), ""+error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        //old: for only post with image
        //count the likes and changes it's color
//        if(model.getPostImageUrl() != null)
//            holder.countLikes(postKey,userId,likeRef);


        //new: for post with image and without image
        //count the likes and changes it's color
        holder.countLikes(postKey,userId,likeRef);


        holder.openCommentsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CommunityPostCommentsFragment communityPostCommentsFragment = new CommunityPostCommentsFragment();
                Bundle args = new Bundle();
                args.putString("postId",postKey);
                args.putString("com",com);
                communityPostCommentsFragment.setArguments(args);
                manager.beginTransaction().replace(R.id.fragment_container,communityPostCommentsFragment).addToBackStack("each community fragment").commit();
            }
        });

        //counting counting and setting commentCounter
        holder.countComments(postKey,userId);

        //to check if the post is uploaded by other user
        if(!model.getUserId().equals(userId)){
            holder.flag = true;
        }
    }

    private void DeletePost(String com, String postId) {
        postRef.child(com).child(postId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                         // Node deleted successfully
                        //likes and comments of the post are deleted
                        likeRef.child(postId).removeValue();
                        commentsRef.child(postId).removeValue();
                        //notifyItemRemoved(position);
                        Toast.makeText(context.getApplicationContext(), "Post deleted successfully",Toast.LENGTH_SHORT).show();
                     }
            }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to delete node
                        Toast.makeText(context.getApplicationContext(), "Post could not be deleted",Toast.LENGTH_SHORT).show();
                    }
                });
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

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);
        return new MyViewHolder(view);
    }

    //ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView openCommentsFragment;
        CardView cv_postImage;
        CircleImageView profileImage;
        ImageView postImage,likeImage;
        TextView username,timeAgo,postDesc,likeCounter,commentsCounter, tv_comment;
        LinearLayout commentsPart;
        boolean flag = false;
        LinearLayout btnMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImagePost);
            postImage = itemView.findViewById(R.id.postImage);
            username = itemView.findViewById(R.id.profileUsername);
            timeAgo = itemView.findViewById(R.id.timeAgo);
            postDesc = itemView.findViewById(R.id.postDesc);
            likeImage = itemView.findViewById(R.id.likeImage);
            likeCounter = itemView.findViewById(R.id.likeCounter);
            commentsCounter = itemView.findViewById(R.id.commentCounter);
            commentsPart = itemView.findViewById(R.id.commentsPart);
            openCommentsFragment = itemView.findViewById(R.id.openCommentsFragment);
            cv_postImage = itemView.findViewById(R.id.cv_postImage);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            context = itemView.getContext();
            manager = ((AppCompatActivity)context).getSupportFragmentManager();
            btnMenu = itemView.findViewById(R.id.postMenu);
        }

        public void countLikes(String postKey, String userId, DatabaseReference likeRef) {

            likeRef.child(postKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    //for setting likes
                    if (snapshot.exists()){
                        int totalLikes = (int) snapshot.getChildrenCount();
                        likeCounter.setText(totalLikes+"");
                    }else{
                        likeCounter.setText("0");
                    }

                    //for setting color of likes
                    if (snapshot.child(userId).exists()){
                        likeImage.setColorFilter(Color.GREEN);
                    }else{
                        likeImage.setColorFilter(Color.GRAY);
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });


        }

        public void countComments(String postKey, String userId) {
            commentsRef.child(postKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        int totalComs = (int) snapshot.getChildrenCount();
                        commentsCounter.setText(""+totalComs);
                    }else{
                        commentsCounter.setText("0");
                        if(flag){
                            commentsCounter.setVisibility(View.GONE);
                            tv_comment.setText("Be the first to comment");
                            tv_comment.setTextColor(Color.parseColor("#a0a0a2"));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
    }

}
