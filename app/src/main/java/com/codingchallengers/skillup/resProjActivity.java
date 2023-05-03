package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resProjActivity extends AppCompatActivity {

    TextInputEditText title_et, desc_et, title_et2, desc_et2, title_et3, desc_et3;
    Button form_proj_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_proj);

        title_et = findViewById(R.id.form_pro_et_title);
        desc_et = findViewById(R.id.form_pro_et_desc);
        title_et2 = findViewById(R.id.form_pro_et_title2);
        desc_et2 = findViewById(R.id.form_pro_et_desc2);
        title_et3 = findViewById(R.id.form_pro_et_title3);
        desc_et3 = findViewById(R.id.form_pro_et_desc3);

        form_proj_btn_save = findViewById(R.id.form_proj_btn_save);


        //setting values, if already existing
        if(isItOk(EditDetailsActivity.Project1Name )){
            title_et.setText(EditDetailsActivity.Project1Name);
        }

        if(isItOk(EditDetailsActivity.Project1Desc)){
            desc_et.setText(EditDetailsActivity.Project1Desc);
        }

        if(isItOk(EditDetailsActivity.Project2Name )){
            title_et2.setText(EditDetailsActivity.Project2Name);
        }

        if(isItOk(EditDetailsActivity.Project2Desc)){
            desc_et2.setText(EditDetailsActivity.Project2Desc);
        }

        if(isItOk(EditDetailsActivity.Project3Name )){
            title_et3.setText(EditDetailsActivity.Project3Name);
        }

        if(isItOk(EditDetailsActivity.Project3Desc)){
            desc_et3.setText(EditDetailsActivity.Project3Desc);
        }


        form_proj_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.Project1Name = title_et.getText().toString().trim();
                EditDetailsActivity.Project1Desc = desc_et.getText().toString().trim();
                EditDetailsActivity.Project2Name = title_et2.getText().toString().trim();
                EditDetailsActivity.Project2Desc = desc_et2.getText().toString().trim();
                EditDetailsActivity.Project3Name = title_et3.getText().toString().trim();
                EditDetailsActivity.Project3Desc = desc_et3.getText().toString().trim();


                Intent intent = new Intent(resProjActivity.this, EditDetailsActivity.class);
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