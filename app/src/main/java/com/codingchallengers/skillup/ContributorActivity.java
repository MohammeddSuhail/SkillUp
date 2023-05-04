package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContributorActivity extends AppCompatActivity {

    CardView prathamesh, suhail, ranjan, ranjith, anuroop, sabarinath, prajith,nagaraj;
    ImageView back_press;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor);


      getSupportActionBar().hide();

        back_press = findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ContributorActivity.this, AllActivity.class);
                startActivity(in);
            }
        });

        prathamesh = findViewById(R.id.prathamesh);
        suhail = findViewById(R.id.suhail);
        ranjan = findViewById(R.id.ranjan);
        ranjith = findViewById(R.id.ranjith);
        anuroop = findViewById(R.id.anuroop);
        sabarinath = findViewById(R.id.sabarinath);
        prajith = findViewById(R.id.prajith);
        nagaraj = findViewById(R.id.nagaraj);

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

        nagaraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContributorActivity.this, ContriDetailActivity.class);
                i1.putExtra("name", "Nagaraj M");
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



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContributorActivity.this, AllActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}