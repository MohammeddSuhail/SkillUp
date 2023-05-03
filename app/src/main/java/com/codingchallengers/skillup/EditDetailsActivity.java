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

    private Button btn_per, btn_edu, btn_exp, btn_skill, btn_obj, btn_pro, btn_view_cv, btn_interest_detail;
    public static String FullName, myFullEmail, myPhoneNumber;
    public static String CollegeBEName, CollegeBECourse, CollegeBEYear,CollegeBEMarks, CollegeOtherName, CollegeOtherCourse, CollegeOtherYear, CollegeOtherMarks;
    public static String ExpComp1Name, ExpComp1Desc, ExpComp1Start, ExpComp1End,ExpComp1Role, ExpComp2Name, ExpComp2Desc, ExpComp2Start ,ExpComp2End, ExpComp2Role;
    public static String Skill1, Skill2;
    public static String Objective;
    public static String Project1Name, Project1Desc, Project2Name, Project2Desc, Project3Name, Project3Desc;
    public static String MyInterests;


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
        btn_interest_detail = findViewById(R.id.btn_interest_detail);

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


        btn_interest_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDetailsActivity.this, resInteActivity.class);
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

                    boolean personal = true, edu = true, exp1 = true, exp2 = true, skills = true, obj = true, proj1 = true , proj2 = true, proj3 = true, inte = true;


                    //checking null or empty string for exp section
                    if(! (isItOk(ExpComp1Name) && isItOk(ExpComp1Desc) && isItOk(ExpComp1Start) && isItOk(ExpComp1End) && isItOk(ExpComp1Role)) ){
                        exp1 = false;
                        //Toast.makeText(EditDetailsActivity.this,"Fill Experience 1 Details",Toast.LENGTH_LONG).show();
                    }
                    if(! (isItOk(ExpComp2Name) && isItOk(ExpComp2Desc) && isItOk(ExpComp2Start) && isItOk(ExpComp2End) && isItOk(ExpComp2Role)) ){
                        exp2 = false;
                        //Toast.makeText(EditDetailsActivity.this,"Fill Experience 2 Details",Toast.LENGTH_LONG).show();
                    }


                    //checking null or empty string for proj section
                    if(! (isItOk(Project1Name) && isItOk(Project1Desc))){
                        proj1 = false;
                        //Toast.makeText(EditDetailsActivity.this,"Fill Project 1 Details",Toast.LENGTH_LONG).show();
                    }
                    if(! (isItOk(Project2Name) && isItOk(Project2Desc))){
                        proj2 = false;
                        //Toast.makeText(EditDetailsActivity.this,"Fill Project 2 Details",Toast.LENGTH_LONG).show();
                    }
                    if(! (isItOk(Project3Name) && isItOk(Project3Desc))){
                        proj3 = false;
                        //Toast.makeText(EditDetailsActivity.this,"Fill Project 3 Details",Toast.LENGTH_LONG).show();
                    }


                    //personal info
                    if(! (isItOk(FullName) && isItOk(myFullEmail) && isItOk(myPhoneNumber))){
                        personal = false;
                        Toast.makeText(EditDetailsActivity.this,"Fill Personal Details",Toast.LENGTH_LONG).show();
                    }
                    else if(! (isItOk(CollegeBEName) && isItOk(CollegeBECourse) && isItOk(CollegeBEYear) && isItOk(CollegeBEMarks)  &&  isItOk(CollegeOtherName) && isItOk(CollegeBECourse) && isItOk(CollegeOtherYear) && isItOk(CollegeOtherMarks)) ){
                        edu = false;
                        Toast.makeText(EditDetailsActivity.this,"Fill Education Details",Toast.LENGTH_LONG).show();
                    }
                    else if(! (isItOk(Skill1) && isItOk(Skill2))){
                        skills = false;
                        Toast.makeText(EditDetailsActivity.this,"Fill Skill Details",Toast.LENGTH_LONG).show();
                    }
                    else if(! isItOk(Objective)){
                        obj = false;
                        Toast.makeText(EditDetailsActivity.this,"Fill Objective",Toast.LENGTH_LONG).show();
                    }

                    else if(! isItOk(MyInterests)){
                        inte = false;
                        Toast.makeText(EditDetailsActivity.this,"Fill Interest Details",Toast.LENGTH_LONG).show();
                    }else{

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
                        str = str.replace("CollegeOtherName", CollegeOtherName);
                        str = str.replace("CollegeOtherCourse", CollegeOtherCourse);
                        str = str.replace("CollegeOtherYear", CollegeOtherYear);
                        str = str.replace("CollegeOtherMarks", CollegeOtherMarks);



                        //for experience
                        if((!exp1) && (!exp2)){
                            //both exp2 not there
                            str = str.replace("<div class=\"section\">\n" +
                                    "      <div class=\"section__title\">Experience</div>\n" +
                                    "      <div class=\"section__list\">\n" +
                                    "        <div class=\"section__list-item\">\n" +
                                    "          <div class=\"left\">\n" +
                                    "            <div class=\"name\">ExpComp1Name</div>\n" +
                                    "            <div class=\"addr\">ExpComp1Desc</div>\n" +
                                    "            <div class=\"duration\">ExpComp1Start - ExpComp1End</div>\n" +
                                    "          </div>\n" +
                                    "          <div class=\"right\">\n" +
                                    "            <div class=\"name\">ExpComp1Role</div>\n" +
                                    "          </div>\n" +
                                    "        </div>\n" +
                                    "        <div class=\"section__list-item\">\n" +
                                    "          <div class=\"left\">\n" +
                                    "            <div class=\"name\">ExpComp2Name</div>\n" +
                                    "            <div class=\"addr\">ExpComp2Desc</div>\n" +
                                    "            <div class=\"duration\">ExpComp2Start - ExpComp2End</div>\n" +
                                    "          </div>\n" +
                                    "          <div class=\"right\">\n" +
                                    "            <div class=\"name\">ExpComp2Role</div>\n" +
                                    "          </div>\n" +
                                    "        </div>\n" +
                                    "\n" +
                                    "      </div>\n" +
                                    "    </div>", "");
                        }
                        else if(exp1 && (!exp2)){
                            //exp1 is there but exp2 not there
                            str = str.replace("ExpComp1Name", ExpComp1Name);
                            str = str.replace("ExpComp1Desc", ExpComp1Desc);
                            str = str.replace("ExpComp1Start", ExpComp1Start);
                            str = str.replace("ExpComp1End", ExpComp1End);
                            str = str.replace("ExpComp1Role", ExpComp1Role);

                            str = str.replace("<div class=\"section__list-item\">\n" +
                                    "          <div class=\"left\">\n" +
                                    "            <div class=\"name\">ExpComp2Name</div>\n" +
                                    "            <div class=\"addr\">ExpComp2Desc</div>\n" +
                                    "            <div class=\"duration\">ExpComp2Start - ExpComp2End</div>\n" +
                                    "          </div>\n" +
                                    "          <div class=\"right\">\n" +
                                    "            <div class=\"name\">ExpComp2Role</div>\n" +
                                    "          </div>\n" +
                                    "        </div>", "");
                        }
                        else if((!exp1) && exp2){
                            //no exp1 but exp2 is there
                            str = str.replace("ExpComp2Name", ExpComp2Name);
                            str = str.replace("ExpComp2Desc", ExpComp2Desc);
                            str = str.replace("ExpComp2Start", ExpComp2Start);
                            str = str.replace("ExpComp2End", ExpComp2End);
                            str = str.replace("ExpComp2Role", ExpComp2Role);

                            str = str.replace("<div class=\"section__list-item\">\n" +
                                    "          <div class=\"left\">\n" +
                                    "            <div class=\"name\">ExpComp1Name</div>\n" +
                                    "            <div class=\"addr\">ExpComp1Desc</div>\n" +
                                    "            <div class=\"duration\">ExpComp1Start - ExpComp1End</div>\n" +
                                    "          </div>\n" +
                                    "          <div class=\"right\">\n" +
                                    "            <div class=\"name\">ExpComp1Role</div>\n" +
                                    "          </div>\n" +
                                    "        </div>", "");
                        }
                        else{
                            //both exp1 and exp2 are there
                            str = str.replace("ExpComp1Name", ExpComp1Name);
                            str = str.replace("ExpComp1Desc", ExpComp1Desc);
                            str = str.replace("ExpComp1Start", ExpComp1Start);
                            str = str.replace("ExpComp1End", ExpComp1End);
                            str = str.replace("ExpComp1Role", ExpComp1Role);
                            str = str.replace("ExpComp2Name", ExpComp2Name);
                            str = str.replace("ExpComp2Desc", ExpComp2Desc);
                            str = str.replace("ExpComp2Start", ExpComp2Start);
                            str = str.replace("ExpComp2End", ExpComp2End);
                            str = str.replace("ExpComp2Role", ExpComp2Role);
                        }





                        str = str.replace("Skill1", Skill1);
                        str = str.replace("Skill2", Skill2);

                        str = str.replace("Objective", Objective);



                        //proj section

                        String projItemStart = "<div class=\"section__list\">\n" +
                                "        <div class=\"section__list-item\">\n" +
                                "          <div class=\"name\">";


                        String projItemMid = "</div>\n" +
                                "          <div class=\"text\">";

                        String projItemEnd = "</div>\n" +
                                "        </div>\n" +
                                "      </div>";


                        String projWhole = "<div class=\"section\">\n" +
                                "      <div class=\"section__title\">Projects</div>\n" +
                                "      ProjectList\n" +
                                "    </div>";


                        String ProjectList = "";


                        if((!proj1) && (!proj2) && (!proj3)){
                            //all 3 proj not there
                            str = str.replace(projWhole, "");
                        }else{
                            //if proj is there
                            if(proj1){
                                ProjectList += projItemStart + Project1Name + projItemMid + Project1Desc + projItemEnd;
                            }
                            if(proj2){
                                if(proj1)
                                    ProjectList += "<br>";
                                ProjectList +=  projItemStart + Project2Name + projItemMid + Project2Desc + projItemEnd;
                            }
                            if(proj3){
                                if(proj1 || proj2)
                                    ProjectList += "<br>";
                                ProjectList += projItemStart + Project3Name + projItemMid + Project3Desc + projItemEnd;
                            }
                            str = str.replace("ProjectList", ProjectList);
                        }


                        str = str.replace("MyInterests", MyInterests);





                        webView.loadDataWithBaseURL("fake://not/needed", str,"text/html","utf-8","");


                        PrintManager printManager = (PrintManager) EditDetailsActivity.this.getSystemService(Context.PRINT_SERVICE);
                        String jobName = "Resume Document";
                        // Get a print adapter instance
                        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
                        // Create a print job with name and adapter instance
                        PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





    }

    private boolean isItOk(String str) {
        if(str == null || str.equals(""))
            return false;
        return true;
    }

}
