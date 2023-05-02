package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resExpActivity extends AppCompatActivity {

    TextInputEditText company_et, job_et, sdate_et, edate_et, desc_et;
    Button form_exp_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_exp);

        company_et = findViewById(R.id.form_exp_et_company);
        job_et = findViewById(R.id.form_exp_et_job);
        sdate_et = findViewById(R.id.form_exp_et_sdate);
        edate_et = findViewById(R.id.form_exp_et_edate);
        desc_et = findViewById(R.id.form_exp_et_desc);
        form_exp_btn_save = findViewById(R.id.form_exp_btn_save);


        form_exp_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.ExpComp1Name = company_et.getText().toString();
                EditDetailsActivity.ExpComp1Role = job_et.getText().toString();
                EditDetailsActivity.ExpComp1Start = sdate_et.getText().toString();
                EditDetailsActivity.ExpComp1End = edate_et.getText().toString();
                EditDetailsActivity.ExpComp1Desc = desc_et.getText().toString();

                Intent intent = new Intent(resExpActivity.this, EditDetailsActivity.class);
                startActivity(intent);
            }
        });



    }
}