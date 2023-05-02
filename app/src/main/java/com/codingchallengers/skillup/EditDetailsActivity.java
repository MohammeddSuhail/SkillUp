package com.codingchallengers.skillup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDetailsActivity extends AppCompatActivity {

    FragmentManager fm = getSupportFragmentManager();
    private Button btn_per, btn_edu, btn_exp, btn_skill, btn_obj, btn_pro, btn_view_cv;
    public static String name ,email, contact;
    public static String CollegeBEName, CollegeBECourse, CollegeBEYear,CollegeBEMarks;
    public static String ExpComp1Name, ExpComp1Loc, ExpComp1Year;



    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);




        btn_per = findViewById(R.id.btn_personal_detail);
        btn_edu = findViewById(R.id.btn_educational_detail);
        btn_exp = findViewById(R.id.btn_experience_detail);
        btn_skill = findViewById(R.id.btn_skill_detail);
        btn_obj = findViewById(R.id.btn_objective_detail);
        btn_pro = findViewById(R.id.btn_project_detail);

        btn_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resPerActivity.class);
                startActivity(intent);
            }
        });

        btn_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resEduActivity.class);
                startActivity(intent);
            }
        });
        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resExpActivity.class);
                startActivity(intent);
            }
        });
        btn_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resSkillActivity.class);
                startActivity(intent);
            }
        });
        btn_obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resObjActivity.class);
                startActivity(intent);
            }
        });
        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resProjActivity.class);
                startActivity(intent);
            }
        });

        btn_view_cv = findViewById(R.id.view_cv_btn);


//        btn_view_cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startViewCVActivity();
//            }
//        });





        if(name != null){
            Toast.makeText(EditDetailsActivity.this, EditDetailsActivity.name, Toast.LENGTH_LONG).show();
        }


    }

//    protected void startViewCVActivity(){
//        Intent intent2 = new Intent(getApplicationContext(), ViewCVActivity.class);
//        intent2.putExtra("ProfileId", ProfileId);
//        intent2.putExtra("CategoryName", categoryName);
//        intent2.putExtra("TemplateFilePath", templateFilePath);
//        intent2.putExtra("TemplateImgPath", templateImgPath);
//        startActivity(intent2);
//    }


}
