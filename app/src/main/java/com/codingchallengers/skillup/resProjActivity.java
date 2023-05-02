package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resProjActivity extends AppCompatActivity {

    TextInputEditText title_et, desc_et, title_et2, desc_et2;
    Button form_proj_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_proj);

        title_et = findViewById(R.id.form_pro_et_title);
        desc_et = findViewById(R.id.form_pro_et_desc);
        title_et2 = findViewById(R.id.form_pro_et_title2);
        desc_et2 = findViewById(R.id.form_pro_et_desc2);
        form_proj_btn_save = findViewById(R.id.form_proj_btn_save);


        form_proj_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.Project1Name = title_et.getText().toString().trim();
                EditDetailsActivity.Project1Desc = desc_et.getText().toString().trim();
                EditDetailsActivity.Project2Name = title_et.getText().toString().trim();
                EditDetailsActivity.Project2Desc = desc_et.getText().toString().trim();

                Intent intent = new Intent(resProjActivity.this, EditDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}