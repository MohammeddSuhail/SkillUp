package com.example.skillup;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ImageViewActivity extends AppCompatActivity {

    ImageView imageView,download_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.imageView);

        String url = getIntent().getStringExtra("url");
        Picasso.get().load(url).into(imageView);

    }
}