package com.codingchallengers.skillup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
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

import java.io.IOException;
import java.io.InputStream;

public class EditDetailsActivity extends AppCompatActivity {

    private Button btn_per, btn_edu, btn_exp, btn_skill, btn_obj, btn_pro, btn_view_cv;
    public static String FullName, myFullEmail, myPhoneNumber;
    public static String CollegeBEName, CollegeBECourse, CollegeBEYear,CollegeBEMarks;
    public static String ExpComp1Name, ExpComp1Desc, ExpComp1Start, ExpComp1End,ExpComp1Role;
    public static String Skill1, Skill2;
    public static String Objective;
    public static String Project1Name, Project1Desc, Project2Name, Project2Desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVisibility(View.GONE);




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


        btn_view_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit html
                InputStream is = null;
                int size = 0;

                try {
                    is = getAssets().open("index.html");
                    size = is.available();

                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    //is.close();

                    String str = new String(buffer);


                    str = str.replace("FullName", FullName);
                    str = str.replace("myFullEmail", myFullEmail);
                    str = str.replace("myPhoneNumber", myPhoneNumber);

                    str = str.replace("CollegeBEName", CollegeBEName);
                    str = str.replace("CollegeBECourse", CollegeBECourse);
                    str = str.replace("CollegeBEYear", CollegeBEYear);
                    str = str.replace("CollegeBEMarks", CollegeBEMarks);

                    str = str.replace("ExpComp1Name", ExpComp1Name);
                    str = str.replace("ExpComp1Desc", ExpComp1Desc);
                    str = str.replace("ExpComp1Start", ExpComp1Start);
                    str = str.replace("ExpComp1End", ExpComp1End);
                    str = str.replace("ExpComp1Role", ExpComp1Role);

                    str = str.replace("Skill1", Skill1);
                    str = str.replace("Skill2", Skill2);

                    str = str.replace("Objective", Objective);

                    str = str.replace("Project1Name", Project1Name);
                    str = str.replace("Project1Desc", Project1Desc);
                    str = str.replace("Project2Name", Project2Name);
                    str = str.replace("Project2Desc", Project2Desc);







                    webView.loadDataWithBaseURL("fake://not/needed", str,"text/html","utf-8","");


                    PrintManager printManager = (PrintManager) EditDetailsActivity.this.getSystemService(Context.PRINT_SERVICE);
                    String jobName = "Resume Document";
                    // Get a print adapter instance
                    PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
                    // Create a print job with name and adapter instance
                    PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





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
