package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class ContriDetailActivity extends AppCompatActivity {

    String name, ctc, companies_placed, cgpa, core,linkedId;
    TextView nametv, company_listtv, Cgpatv, Coretv;
    Button linkedin;
    ImageView img, back_press;
    LinearLayout full_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contri_detail);

        getSupportActionBar().hide();

        back_press = findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ContriDetailActivity.this, ContributorActivity.class);
                startActivity(in);
            }
        });

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        img = findViewById(R.id.image);
        linkedin = findViewById(R.id.linkedin);

        full_desc = findViewById(R.id.full_desc);

        full_desc.startAnimation(AnimationUtils.loadAnimation(this,R.anim.go_up));
        if(Objects.equals(name, "Prathamesh S Pai")){
            ctc="15 Lakhs";
            companies_placed = "• Informatica \n• TCS (MRC)";
            cgpa = "8.60";
            core = "• Mobile Application Development\n• API/UI Testing";
            linkedId = "prathameshspai";
            img.setImageResource(R.drawable.me);
        }

        if(Objects.equals(name, "Sabarinath")){
            ctc="22 Lakhs";
            companies_placed = "• Cohesity \n• Tismo";
            cgpa = "8.88";
            core = "• Web Development (MERN)\n• Cloud Computing";
            img.setImageResource(R.drawable.sab);
            linkedId = "sabarinath3072001";
        }

        if(Objects.equals(name, "R Anuroop")){
            ctc="14.5 Lakhs";
            companies_placed = "Capillary Technologies";
            cgpa = "9.61";
            core = "• Web Development\n• Blockchain";
            img.setImageResource(R.drawable.anuroop);
            linkedId = "anuroop-ravindra-821b67231";
        }

        if(Objects.equals(name, "Ranjan R Upadhya")){
            ctc="14.5 Lakhs";
            companies_placed = "Daimler Truck";
            cgpa = "9.24";
            core = "• Web Development\n• Core Java\n• Spring boot";
            linkedId = "ranjan-r-upadhya-9b6a39267";
            img.setImageResource(R.drawable.ranjan);
        }

        if(Objects.equals(name, "Mohammed Suhail")){
            ctc="12 Lakhs";
            companies_placed = "• ACI Worldwide \n• Accenture (MRC)";
            cgpa = "9.39";
            core = "• Android Development\n• Web Development";

            linkedId = "mohammed-suhail-997aa1213";
            img.setImageResource(R.drawable.suhail);
        }

        if(Objects.equals(name, "Prajith Shetty")){
            ctc="12 Lakhs";
            companies_placed = "• Tismo Technology\n• Capgemini (MRC)";
            cgpa = "9.51";
            core = "• Web & App Development\n• Machine Learning";

            linkedId = "prajith-shetty";
            img.setImageResource(R.drawable.prajith);
        }

        if(Objects.equals(name, "Nagaraj M")){
            ctc="12 Lakhs";
            companies_placed = "• Sony";
            cgpa = "9.07";
            core = "• Web Development\n• Android App Development";

            linkedId = "nagaraj-m-69499a234";
            img.setImageResource(R.drawable.nagaraj);
        }

        if(Objects.equals(name, "Ranjith Kumar R")){
            ctc="12 Lakhs";
            companies_placed = "• Daimler Truck \n• Accenture (MRC)\n• Capgemini (MRC)\n• DXC (MRC)";
            cgpa = "9.31";
            core = "• Full-Stack Web Development\n• Kubernetes";
            linkedId = "ranjithkumarr999";
            img.setImageResource(R.drawable.ran);
        }

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + linkedId));
                final PackageManager packageManager = getApplicationContext().getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/" + linkedId));
                }
                startActivity(intent);
            }
        });

        nametv = findViewById(R.id.name);
        company_listtv=findViewById(R.id.company_list);
        Cgpatv=findViewById(R.id.Cgpa);
        Coretv=findViewById(R.id.CoreCompetency);

        nametv.setText(name);
        company_listtv.setText(companies_placed);
        Coretv.setText(core);
        Cgpatv.setText(cgpa);

    }
}