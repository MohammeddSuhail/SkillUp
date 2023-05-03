package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class resPerActivity extends AppCompatActivity {

    private TextInputEditText form_personal_et_name, form_personal_et_email, form_personal_et_contact;
    Button form_personal_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_per);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>SkillUp</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2a2b2d")));

        form_personal_et_name = findViewById(R.id.form_personal_et_name);
        form_personal_et_email = findViewById(R.id.form_personal_et_email);
        form_personal_et_contact = findViewById(R.id.form_personal_et_contact);

        form_personal_btn_save = findViewById(R.id.form_personal_btn_save);


        //setting values, if already existing
        if(isItOk(EditDetailsActivity.FullName)){
            form_personal_et_name.setText(EditDetailsActivity.FullName);
        }

        if(isItOk(EditDetailsActivity.myFullEmail)){
            form_personal_et_email.setText(EditDetailsActivity.myFullEmail);
        }

        if(isItOk(EditDetailsActivity.myPhoneNumber)){
            form_personal_et_contact.setText(EditDetailsActivity.myPhoneNumber);
        }



        form_personal_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.FullName = form_personal_et_name.getText().toString().trim();
                EditDetailsActivity.myFullEmail = form_personal_et_email.getText().toString().trim();
                EditDetailsActivity.myPhoneNumber = form_personal_et_contact.getText().toString().trim();

                Intent intent = new Intent(resPerActivity.this, EditDetailsActivity.class);
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