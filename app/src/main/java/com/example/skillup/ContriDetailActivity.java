package com.example.skillup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class ContriDetailActivity extends AppCompatActivity {

    String name, ctc, companies_placed, cgpa, core;
    TextView nametv, company_listtv, Cgpatv, Coretv;
    ImageView img;
    LinearLayout full_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contri_detail);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#121212"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Top Contributor</font>"));

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        img = findViewById(R.id.image);
        full_desc = findViewById(R.id.full_desc);

        full_desc.startAnimation(AnimationUtils.loadAnimation(this,R.anim.go_up));
        if(Objects.equals(name, "Prathamesh S Pai")){
            ctc="15 Lakhs";
            companies_placed = "• Informatica (CTC: 15 Lakhs)\n• TCS (MRC - CTC: 3.3 Lakhs)";
            cgpa = "8.50";
            core = "Mobile Application Development";
            img.setImageResource(R.drawable.me);
        }

        if(Objects.equals(name, "Sabarinath")){
            ctc="22 Lakhs";
            companies_placed = "• Cohesity (CTC: 22 Lakhs)\n• Tismo (CTC: 7.25 Lakhs)";
            cgpa = "8.88";
            core = "• Web Development (MERN)\n• Cloud Computing";
            img.setImageResource(R.drawable.sab);
        }

        if(Objects.equals(name, "R Anuroop")){
            ctc="14.5 Lakhs";
            companies_placed = "Capillary Technologies (CTC: 14.5 Lakhs)";
            cgpa = "9.61";
            core = "• Web Development\n• Blockchain";
            img.setImageResource(R.drawable.anuroop);
        }

        if(Objects.equals(name, "Ranjan Upadhya")){
            ctc="14.5 Lakhs";
            companies_placed = "Daimler Truck (CTC: 10 Lakhs)";
            cgpa = "9.17";
            core = "• Web Development\n• Java Programming";
            img.setImageResource(R.drawable.ranjan);
        }

        if(Objects.equals(name, "Mohammed Suhail")){
            ctc="12 Lakhs";
            companies_placed = "ACI Worldwide (CTC: 12 Lakhs)";
            cgpa = "9.39";
            core = "Android Development";
            img.setImageResource(R.drawable.suhail);
        }

        if(Objects.equals(name, "Ranjith Kumar R")){
            ctc="12 Lakhs";
            companies_placed = "• Daimler Truck (CTC: 10 Lakhs)\n• Accenture (MRC - CTC: 4.5 Lakhs)\n• Capgemini (MRC - CTC: 4.25 Lakhs)\n• DXC (MRC - CTC: 4.2 Lakhs)";
            cgpa = "9.31";
            core = "Web Development";
            img.setImageResource(R.drawable.ran);
        }

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