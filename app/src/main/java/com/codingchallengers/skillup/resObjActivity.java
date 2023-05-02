package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resObjActivity extends AppCompatActivity {

    TextInputEditText form_obj_et_obj;
    Button form_obj_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_obj);

        form_obj_et_obj = findViewById(R.id.form_obj_et_obj);
        form_obj_btn_save = findViewById(R.id.form_obj_btn_save);

        form_obj_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.Objective = form_obj_et_obj.getText().toString().trim();

                Intent intent = new Intent(resObjActivity.this, EditDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}