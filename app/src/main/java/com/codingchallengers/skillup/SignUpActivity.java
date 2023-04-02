package com.codingchallengers.skillup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.codingchallengers.skillup.databinding.ActivitySignUpBinding;
import com.codingchallengers.skillup.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    private FirebaseAuth auth;
    DatabaseReference mRef;
    CardView signupCardview;
    ProgressDialog mLoadingBar;

    boolean isAllFieldsChecked = false;
    String email, pw, usn, phoneNo, conPwd;
    String usnFromEmail;
    FirebaseUser user;

    Button btn_okay;
    TextView tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();

        //Sign-in animation - 2 lines
        signupCardview=findViewById(R.id.sign_up_cardview);
        signupCardview.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_in));

        binding.alreadyAccountId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        });


        binding.signUpButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndSignUp();
            }
        });
    }

    private void checkAndSignUp() {
        email = binding.emailId.getText().toString().trim().toLowerCase();
        pw = binding.passwordId.getText().toString();
        conPwd = binding.conPwdId.getText().toString();
        usn = binding.usn.getText().toString().trim();
        phoneNo = binding.phoneNo.getText().toString().trim();


        isAllFieldsChecked = CheckAllFields();

        if(isAllFieldsChecked){
            mLoadingBar =  new ProgressDialog(SignUpActivity.this);
            mLoadingBar.setTitle("Signing Up");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            //adding user
            auth.createUserWithEmailAndPassword(email,pw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //done adding email and password in auth
                                mLoadingBar.dismiss();
                                user = task.getResult().getUser();

                                //setupFlag = false, since setup part of the user is not completed.
                                Users newUser = new Users(usn,phoneNo,false);
                                mRef.child("Users").child(user.getUid()).setValue(newUser);

                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Verification email sent
                                                    FirebaseAuth.getInstance().signOut();
                                                    verifyEmailDialog();

                                                 /*   Toast.makeText(getApplicationContext(),
                                                            "Verification email sent to " + user.getEmail() + "\nPlease verify your email id" ,
                                                            Toast.LENGTH_LONG).show();*/
                                                    Log.d("Verification", "Verification email sent to " + user.getEmail());
                                                } else {
                                                    // Error sending verification email
                                                    Log.e("TAG", "sendEmailVerification", task.getException());
                                                    Toast.makeText(getApplicationContext(),
                                                            "Failed to send verification email. Please wait and try again!",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }else{
                                mLoadingBar.dismiss();
                                Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    private void verifyEmailDialog() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.verify_email_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.verify_email_dialog_bg));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        btn_okay = dialog.findViewById(R.id.btn_okay);
        tv_email = dialog.findViewById(R.id.tv_email);

        tv_email.setText(user.getEmail());

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });


        dialog.show();
    }

    private boolean CheckAllFields() {
        if(usn.isEmpty()){
            showError(binding.usn,"This field is required!");
            return false;
        }
        else if(usn.length()<5){
            showError(binding.usn,"USN is not valid");
            return false;
        }
        else if(email.isEmpty()){
            showError(binding.emailId,"This field is required!");
            return false;
        }
        else if(!email.endsWith("@nmamit.in")){
            showError(binding.emailId,"The email should end with '@nmamit.in' domain");
            return false;
        }
        else if(phoneNo.isEmpty()){
            showError(binding.phoneNo,"This field is required!");
            return false;
        }
        else if(phoneNo.length()<7){
            showError(binding.phoneNo,"Phone no. is not valid");
            return false;
        }
        else if(pw.isEmpty()){
            showError(binding.passwordId,"This field is required!");
            return false;
        }
        else if(pw.length()<8){
            showError(binding.passwordId,"Password must be minimum 8 characters");
            return false;
        }
        else if(conPwd.isEmpty()){
            showError(binding.conPwdId,"This field is required!");
            return false;
        }
        else if(!pw.equals(conPwd)){
            showError(binding.conPwdId,"Password is not matching with confirm password");
            return false;
        }

        int index = email.indexOf('@');
        usnFromEmail = email.substring(0,index);
        if(!usnFromEmail.toLowerCase(Locale.ROOT).equals(usn.toLowerCase(Locale.ROOT))){
            showError(binding.usn,"USN is not matching with USN from college email");
            return false;
        }

        return true;
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}