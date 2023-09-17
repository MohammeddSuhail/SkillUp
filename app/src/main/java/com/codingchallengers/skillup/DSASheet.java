package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class DSASheet extends AppCompatActivity {

    CardView striverSDE, striverDSA, apnacollegeDSA, lovebabbarDSA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsasheet);

        getSupportActionBar().hide();

        striverSDE = (CardView) findViewById(R.id.striverSDE);
        striverDSA = (CardView) findViewById(R.id.striverDSA);
        apnacollegeDSA = (CardView) findViewById(R.id.apnacollegeDSA);
        lovebabbarDSA = (CardView) findViewById(R.id.lovebabbarDSA);

        striverSDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://takeuforward.org/interviews/strivers-sde-sheet-top-coding-interview-problems/"; // Replace with your desired URL

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

//                if (isIntentSafe) {
                    startActivity(intent);
//                }
            }
        });

        striverDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2/"; // Replace with your desired URL

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

//                if (isIntentSafe) {
                    startActivity(intent);
//                }
            }
        });

        apnacollegeDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://docs.google.com/spreadsheets/u/0/d/1hXserPuxVoWMG9Hs7y8wVdRCJTcj3xMBAEYUOXQ5Xag/htmlview";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

//                if (isIntentSafe) {
                    startActivity(intent);
//                }
            }
        });

        lovebabbarDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.geeksforgeeks.org/dsa-sheet-by-love-babbar/";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

//                if (isIntentSafe) {
                    startActivity(intent);
//                }
            }
        });
    }
}