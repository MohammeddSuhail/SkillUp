package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resExpActivity extends AppCompatActivity {

    TextInputEditText company_et, job_et, sdate_et, edate_et, desc_et, company_et2, job_et2, sdate_et2, edate_et2, desc_et2;
    Button form_exp_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_exp);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>SkillUp</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2a2b2d")));

        company_et = findViewById(R.id.form_exp_et_company);
        job_et = findViewById(R.id.form_exp_et_job);
        sdate_et = findViewById(R.id.form_exp_et_sdate);
        edate_et = findViewById(R.id.form_exp_et_edate);
        desc_et = findViewById(R.id.form_exp_et_desc);

        company_et2 = findViewById(R.id.form_exp_et_company2);
        job_et2 = findViewById(R.id.form_exp_et_job2);
        sdate_et2 = findViewById(R.id.form_exp_et_sdate2);
        edate_et2 = findViewById(R.id.form_exp_et_edate2);
        desc_et2 = findViewById(R.id.form_exp_et_desc2);

        form_exp_btn_save = findViewById(R.id.form_exp_btn_save);



        //setting values, if already existing
        if(isItOk(EditDetailsActivity.ExpComp1Name)){
            company_et.setText(EditDetailsActivity.ExpComp1Name);
        }

        if(isItOk(EditDetailsActivity.ExpComp1Role)){
            job_et.setText(EditDetailsActivity.ExpComp1Role);
        }

        if(isItOk(EditDetailsActivity.ExpComp1Start)){
            sdate_et.setText(EditDetailsActivity.ExpComp1Start);
        }

        if(isItOk(EditDetailsActivity.ExpComp1End)){
            edate_et.setText(EditDetailsActivity.ExpComp1End);
        }

        if(isItOk(EditDetailsActivity.ExpComp1Desc)){
            desc_et.setText(EditDetailsActivity.ExpComp1Desc);
        }



        if(isItOk(EditDetailsActivity.ExpComp2Name)){
            company_et2.setText(EditDetailsActivity.ExpComp2Name);
        }

        if(isItOk(EditDetailsActivity.ExpComp2Role)){
            job_et2.setText(EditDetailsActivity.ExpComp2Role);
        }

        if(isItOk(EditDetailsActivity.ExpComp2Start)){
            sdate_et2.setText(EditDetailsActivity.ExpComp2Start);
        }

        if(isItOk(EditDetailsActivity.ExpComp2End)){
            edate_et2.setText(EditDetailsActivity.ExpComp2End);
        }

        if(isItOk(EditDetailsActivity.ExpComp2Desc)){
            desc_et2.setText(EditDetailsActivity.ExpComp2Desc);
        }




        form_exp_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.ExpComp1Name = company_et.getText().toString();
                EditDetailsActivity.ExpComp1Role = job_et.getText().toString();
                EditDetailsActivity.ExpComp1Start = sdate_et.getText().toString();
                EditDetailsActivity.ExpComp1End = edate_et.getText().toString();
                EditDetailsActivity.ExpComp1Desc = desc_et.getText().toString();

                EditDetailsActivity.ExpComp2Name = company_et2.getText().toString();
                EditDetailsActivity.ExpComp2Role = job_et2.getText().toString();
                EditDetailsActivity.ExpComp2Start = sdate_et2.getText().toString();
                EditDetailsActivity.ExpComp2End = edate_et2.getText().toString();
                EditDetailsActivity.ExpComp2Desc = desc_et2.getText().toString();


                Intent intent = new Intent(resExpActivity.this, EditDetailsActivity.class);
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