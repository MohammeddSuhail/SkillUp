package com.example.skillup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class ContributorActivity extends AppCompatActivity {

    CardView prathamesh, suhail, ranjan, ranjith, anuroop, sabarinath, prajith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);


        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#121212"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Top Contributors</font>"));

        prathamesh = findViewById(R.id.prathamesh);
        suhail = findViewById(R.id.suhail);
        ranjan = findViewById(R.id.ranjan);
        ranjith = findViewById(R.id.ranjith);
        anuroop = findViewById(R.id.anuroop);
        sabarinath = findViewById(R.id.sabarinath);
        prajith = findViewById(R.id.prajith);

        prathamesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Prathamesh S Pai");
                startActivity(i1);
            }
        });
        suhail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Mohammed Suhail");
                startActivity(i1);
            }
        });
        ranjan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Ranjan Upadhya");
                startActivity(i1);
            }
        });

        prajith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Prajith Shetty");
                startActivity(i1);
            }
        });

        sabarinath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Sabarinath");
                startActivity(i1);
            }
        });
        ranjith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Ranjith Kumar R");
                startActivity(i1);
            }
        });
        anuroop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "R Anuroop");
                startActivity(i1);
            }
        });
    }
}