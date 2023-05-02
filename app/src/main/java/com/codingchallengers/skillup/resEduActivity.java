package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resEduActivity extends AppCompatActivity {

    TextInputEditText degree_et, university_et , grade_et ,year_et;
    Button form_edu_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_edu);

        degree_et = findViewById(R.id.form_edu_et_degree);
        university_et = findViewById(R.id.form_edu_et_university);
        grade_et = findViewById(R.id.form_edu_et_grade);
        year_et = findViewById(R.id.form_edu_et_year);
        form_edu_btn_save = findViewById(R.id.form_edu_btn_save);

        form_edu_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.CollegeBECourse = degree_et.getText().toString();
                EditDetailsActivity.CollegeBEName = university_et.getText().toString();
                EditDetailsActivity.CollegeBEMarks = grade_et.getText().toString();
                EditDetailsActivity.CollegeBEYear = year_et.getText().toString();

                Intent intent = new Intent(resEduActivity.this, EditDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}