package com.example.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        String[] vid = getIntent().getStringArrayExtra("video");

        TextView t = findViewById(R.id.textView4);


        String wholeThing = "";

        for (int i = 0; i < vid.length; i++) {
            wholeThing += vid[i] +"\n";
        }

        t.setText(wholeThing);

    }
}