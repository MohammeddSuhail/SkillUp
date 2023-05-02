package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resEduActivity extends AppCompatActivity {

    TextInputEditText degree_et, university_et , grade_et, year_et, degree_et2, university_et2 , grade_et2, year_et2;
    Button form_edu_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_edu);

        degree_et = findViewById(R.id.form_edu_et_degree);
        university_et = findViewById(R.id.form_edu_et_university);
        grade_et = findViewById(R.id.form_edu_et_grade);
        year_et = findViewById(R.id.form_edu_et_year);

        degree_et2 = findViewById(R.id.form_edu_et_degreePU);
        university_et2 = findViewById(R.id.form_edu_et_universityPU);
        grade_et2 = findViewById(R.id.form_edu_et_gradePU);
        year_et2 = findViewById(R.id.form_edu_et_yearPU);

        form_edu_btn_save = findViewById(R.id.form_edu_btn_save);


        //setting values, if already existing
        if(isItOk(EditDetailsActivity.CollegeBECourse)){
            degree_et.setText(EditDetailsActivity.CollegeBECourse);
        }

        if(isItOk(EditDetailsActivity.CollegeBEName)){
            university_et.setText(EditDetailsActivity.CollegeBEName);
        }

        if(isItOk(EditDetailsActivity.CollegeBEMarks)){
            grade_et.setText(EditDetailsActivity.CollegeBEMarks);
        }

        if(isItOk(EditDetailsActivity.CollegeBEYear)){
            year_et.setText(EditDetailsActivity.CollegeBEYear);
        }



        if(isItOk(EditDetailsActivity.CollegeOtherCourse)){
            degree_et2.setText(EditDetailsActivity.CollegeOtherCourse);
        }

        if(isItOk(EditDetailsActivity.CollegeOtherName)){
            university_et2.setText(EditDetailsActivity.CollegeOtherName);
        }

        if(isItOk(EditDetailsActivity.CollegeOtherMarks)){
            grade_et2.setText(EditDetailsActivity.CollegeOtherMarks);
        }

        if(isItOk(EditDetailsActivity.CollegeOtherYear)){
            year_et2.setText(EditDetailsActivity.CollegeOtherYear);
        }



        form_edu_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.CollegeBECourse = degree_et.getText().toString();
                EditDetailsActivity.CollegeBEName = university_et.getText().toString();
                EditDetailsActivity.CollegeBEMarks = grade_et.getText().toString();
                EditDetailsActivity.CollegeBEYear = year_et.getText().toString();

                EditDetailsActivity.CollegeOtherCourse = degree_et2.getText().toString();
                EditDetailsActivity.CollegeOtherName = university_et2.getText().toString();
                EditDetailsActivity.CollegeOtherMarks = grade_et2.getText().toString();
                EditDetailsActivity.CollegeOtherYear = year_et2.getText().toString();

                Intent intent = new Intent(resEduActivity.this, EditDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }


    private boolean isItOk(String str) {
        if(str == null || str.equals(""))
            return false;
        return true;
    }
}