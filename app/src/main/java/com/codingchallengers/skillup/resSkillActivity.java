package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resSkillActivity extends AppCompatActivity {

    TextInputEditText skill_et,skill_et2;
    Button form_skill_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_skill);

        skill_et = findViewById(R.id.form_skill_et_skill);
        skill_et2 = findViewById(R.id.form_skill_et_skill2);
        form_skill_btn_save = findViewById(R.id.form_skill_btn_save);

        form_skill_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.Skill1 = skill_et.getText().toString();
                EditDetailsActivity.Skill2 = skill_et2.getText().toString();

                Intent intent = new Intent(resSkillActivity.this, EditDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}