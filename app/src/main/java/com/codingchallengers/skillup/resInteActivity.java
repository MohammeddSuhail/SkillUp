package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class resInteActivity extends AppCompatActivity {

    TextInputEditText form_obj_et_obj_inte;
    Button form_obj_btn_save_inte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_inte);

        form_obj_et_obj_inte = findViewById(R.id.form_obj_et_obj_inte);

        form_obj_btn_save_inte = findViewById(R.id.form_obj_btn_save_inte);


        //setting values, if already existing
        if(isItOk(EditDetailsActivity.MyInterests  )){
            form_obj_et_obj_inte.setText(EditDetailsActivity.MyInterests );
        }



        form_obj_btn_save_inte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetailsActivity.MyInterests = form_obj_et_obj_inte.getText().toString().trim();

                Intent intent = new Intent(resInteActivity.this, EditDetailsActivity.class);
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