package com.example.skillup;


import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        String[] vid = getIntent().getStringArrayExtra("video");
        TextView t = (TextView) findViewById(R.id.vtitle);
        YouTubePlayerView ypv = findViewById(R.id.youtube_player_view);


        String wholeThing = "";
        for (int i = 0; i < vid.length; i++) {
            wholeThing += vid[i] +"\n";
        }
        t.setText(vid[2]);

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




//        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.loadVideo(vid[0]);
//                youTubePlayer.play();
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//                Toast.makeText(getApplicationContext(),
//                        "Failed",
//                        Toast.LENGTH_SHORT).show();
//            }
//        };
//        ypv.initialize("AIzaSyBmNsfFLi8vqW2ZZE-ZRJTUd5sfRnw74bQ",listener);



    }
}