package com.example.skillup;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.example.skillup.fragments.CoursesFragment;
import com.example.skillup.model.Video;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class PlayerActivity extends AppCompatActivity {
    String[] vid;
    TextView title, module,imp;
    YouTubePlayerView ypv;
    ImageView pinBtn, unPinBtn;



    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        vid = getIntent().getStringArrayExtra("video");
        Video vide = new Video(vid[0],vid[1],vid[2],Long.parseLong(vid[3]),Long.parseLong(vid[4]),Long.parseLong(vid[5]),vid[6]);

        title = (TextView) findViewById(R.id.vtitle);
        module=(TextView) findViewById(R.id.module);
        imp = (TextView) findViewById(R.id.imp);

        ypv = findViewById(R.id.youtube_player_view);

        module.setText(vide.getModule());

        if(vid[5].equals("0")){
            imp.setVisibility(View.GONE);
        }

        title.setText(vide.getVideoTitle());


        getLifecycle().addObserver(ypv);
        ypv.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = vid[0];
                youTubePlayer.loadVideo(videoId, 0);
                Log.d("Jj", "Thomson");
            }
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ypv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }





        //pin video

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        pinBtn = findViewById(R.id.pin);
        unPinBtn = findViewById(R.id.unpin);

        setPin();


        pinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap hashMap = new HashMap();
                String videoId, videoLink, videoTitle, Module;
                Long ID, duration, important;
                hashMap.put("videoId",vid[0]);
                hashMap.put("videoLink",vid[1]);
                hashMap.put("videoTitle", vid[2]);
                hashMap.put("ID",Long.parseLong(vid[3]));
                hashMap.put("duration",Long.parseLong(vid[4]));
                hashMap.put("important",Long.parseLong(vid[5]));
                hashMap.put("Module", vid[6]);


                //Toast.makeText(PlayerActivity.this, CoursesFragment.course, Toast.LENGTH_SHORT).show();   .setValue(vide) or .updateChildren(hashMap)
                mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PlayerActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlayerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


//                mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        if(snapshot.exists()){
////                            mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).removeValue();
////                            Toast.makeText(PlayerActivity.this,"Removed",Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            //not there so add
//                            HashMap hashMap = new HashMap();
//                            String videoId, videoLink, videoTitle, Module;
//                            Long ID, duration, important;
//                            hashMap.put("videoId",vid[0]);
//                            hashMap.put("videoLink",vid[1]);
//                            hashMap.put("videoTitle", vid[2]);
//                            hashMap.put("ID",Long.parseLong(vid[3]));
//                            hashMap.put("duration",Long.parseLong(vid[4]));
//                            hashMap.put("important",Long.parseLong(vid[5]));
//                            hashMap.put("Module", vid[6]);
//
//
//                            //Toast.makeText(PlayerActivity.this, CoursesFragment.course, Toast.LENGTH_SHORT).show();   .setValue(vide) or .updateChildren(hashMap)
//                            mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    Toast.makeText(PlayerActivity.this, "Added", Toast.LENGTH_SHORT).show();
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(PlayerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });


            }
        });


        unPinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).removeValue();
                Toast.makeText(PlayerActivity.this,"Removed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setPin() {
        mRef.child("Pinned").child(mUser.getUid()).child(CoursesFragment.course).child(vid[0]).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //pinBtn.setVisibility(View.GONE);

                }
                else{
                    //pinBtn.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}