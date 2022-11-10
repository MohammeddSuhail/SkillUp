package com.example.skillup;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Objects;

public class PlayerActivity extends AppCompatActivity {
    String[] vid;
    TextView title, module,imp;
    YouTubePlayerView ypv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        vid = getIntent().getStringArrayExtra("video");
        title = (TextView) findViewById(R.id.vtitle);
        module=(TextView) findViewById(R.id.module);
        imp = (TextView) findViewById(R.id.imp);

        ypv = findViewById(R.id.youtube_player_view);

        module.setText(vid[6]);

        if(vid[5].equals("0")){
            imp.setVisibility(View.GONE);
        }

        Log.d("hihi", vid[5]+":");
        String wholeThing = "";
        for (int i = 0; i < vid.length; i++) {
            wholeThing += vid[i] +"\n";
        }
        title.setText(vid[2]);

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