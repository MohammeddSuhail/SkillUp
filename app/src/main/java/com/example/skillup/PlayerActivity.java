package com.example.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        String[] vid = getIntent().getStringArrayExtra("video");
        TextView t = (TextView) findViewById(R.id.vtitle);
        String wholeThing = "";
        for (int i = 0; i < vid.length; i++) {
            wholeThing += vid[i] +"\n";
        }
        t.setText(vid[2]);


        YouTubePlayerView ypv = findViewById(R.id.youtube_player_view);
        YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(vid[0]);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(),
                        "Failed",
                        Toast.LENGTH_SHORT).show();
            }
        };
        ypv.initialize("AIzaSyBmNsfFLi8vqW2ZZE-ZRJTUd5sfRnw74bQ",listener);



    }
}