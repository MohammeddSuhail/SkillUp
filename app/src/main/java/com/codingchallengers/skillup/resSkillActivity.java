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

public class resSkillActivity extends AppCompatActivity {

    TextInputEditText skill_et1, skill_et2, skill_et3, skill_et4, skill_et5;
    Button form_skill_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_skill);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>SkillUp</font>"));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2a2b2d")));

        skill_et1 = findViewById(R.id.form_skill_et_skill1);
        skill_et2 = findViewById(R.id.form_skill_et_skill2);
        skill_et3 = findViewById(R.id.form_skill_et_skill3);
        skill_et4 = findViewById(R.id.form_skill_et_skill4);
        skill_et5 = findViewById(R.id.form_skill_et_skill5);

        form_skill_btn_save = findViewById(R.id.form_skill_btn_save);


        //setting values, if already existing
        if(EditDetailsActivity.skillList.size() != 0){
            int size = EditDetailsActivity.skillList.size();

            if(size >= 1)
                skill_et1.setText(EditDetailsActivity.skillList.get(0));

            if(size >= 2)
                skill_et2.setText(EditDetailsActivity.skillList.get(1));

            if(size >= 3)
                skill_et3.setText(EditDetailsActivity.skillList.get(2));

            if(size >= 4)
                skill_et4.setText(EditDetailsActivity.skillList.get(3));

            if(size >= 5)
                skill_et5.setText(EditDetailsActivity.skillList.get(4));
        }


        form_skill_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isItOk(skill_et1.getText().toString()) && (!isDuplicate(skill_et1.getText().toString())) )
                    EditDetailsActivity.skillList.add(skill_et1.getText().toString());

                if(isItOk(skill_et2.getText().toString()) && (!isDuplicate(skill_et2.getText().toString())) )
                    EditDetailsActivity.skillList.add(skill_et2.getText().toString());

                if(isItOk(skill_et3.getText().toString()) && (!isDuplicate(skill_et3.getText().toString())) )
                    EditDetailsActivity.skillList.add(skill_et3.getText().toString());

                if(isItOk(skill_et4.getText().toString()) && (!isDuplicate(skill_et4.getText().toString())) )
                    EditDetailsActivity.skillList.add(skill_et4.getText().toString());

                if(isItOk(skill_et5.getText().toString()) && (!isDuplicate(skill_et5.getText().toString())) )
                    EditDetailsActivity.skillList.add(skill_et5.getText().toString());

                Intent intent = new Intent(resSkillActivity.this, EditDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private boolean isDuplicate(String str) {
        for (int i = 0; i < EditDetailsActivity.skillList.size(); i++) {
            if(EditDetailsActivity.skillList.get(i).equals(str))
                return true;
        }
        return false;
    }


    private boolean isItOk(String str) {
        if(str == null || str.equals(""))
            return false;
        return true;
    }
}