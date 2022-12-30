package com.example.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.skillup.model.Experience;

import java.util.Locale;

public class ExpViewActivity extends AppCompatActivity {

    String[] experience;

    TextView company_name, role, type, placed_year, ctc, rounds,advice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ie_each_fragment);

        getSupportActionBar().hide();

        experience = getIntent().getStringArrayExtra("experience");

        Experience exp = new Experience(experience[0],experience[1],experience[2],experience[3],experience[4],experience[5],experience[6],Integer.parseInt(experience[7]));


        company_name = (TextView) findViewById(R.id.company_name);
        role = (TextView) findViewById(R.id.role);
        type = (TextView) findViewById(R.id.type);
        placed_year = (TextView) findViewById(R.id.placed_year);
        ctc = (TextView) findViewById(R.id.ctc);
        rounds = (TextView) findViewById(R.id.rounds);
        advice = (TextView) findViewById(R.id.advice);

        String round = exp.getRounds().replace('₹','\n');


        company_name.setText(exp.getCompanyName());
        role.setText(exp.getRole());
        type.setText(exp.getType());
        placed_year.setText(exp.getCampusDriveYear()+"");
        ctc.setText(exp.getCTCOffered());
        rounds.setText(exp.getRounds());
        advice.setText(exp.getAdviceToFreshers());

    }
}