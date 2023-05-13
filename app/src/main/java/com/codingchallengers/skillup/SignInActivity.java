package com.codingchallengers.skillup;

import android.app.AlertDialog;

import android.app.Dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.codingchallengers.skillup.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private FirebaseAuth mAuth;
    DatabaseReference mRef;

    FirebaseUser user;
    ProgressDialog mLoadingBar;
    FirebaseUser mUser;

    CardView signinCardview;
    Boolean setupFlag;
    boolean isAllFieldsChecked = false;
    String email,pwd;

    Button btn_cancel, btn_resend, btn_okay;
    TextView tv_email, tv_email2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        //Sign-in animation - 2 lines
        signinCardview=findViewById(R.id.signin_cardview);
        signinCardview.startAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_in));

        binding.newAccountId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });

        binding.forgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResetPasswordDialog();
            }
        });

        binding.signInButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndSignIn();
            }
        });

        //if user is already logged in (mainly when user comes to setup page and exits app without completing setup)
        if(mAuth.getCurrentUser()!=null){
            mRef.child("Users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    setupFlag = snapshot.child("setupFlag").getValue(Boolean.class);
                    if(setupFlag){
                        Intent intent = new Intent(SignInActivity.this,AllActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(SignInActivity.this,SetUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //Intent intent = new Intent(SignInActivity.this,AllActivity.class);
//            intent.putExtra("interest",interest);
           // startActivity(intent);
           // finish();
        }

    }


    private void checkAndSignIn() {
        email = binding.emailId.getText().toString().toLowerCase().trim();
        pwd = binding.pwdId.getText().toString();

        // to check whether the entered data is valid or if any fields are left blank.
        isAllFieldsChecked = CheckAllFields();

        if(isAllFieldsChecked){
            mLoadingBar =  new ProgressDialog(SignInActivity.this);
            mLoadingBar.setTitle("Signing In");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            //checking if the user is present in firebase
            mAuth.signInWithEmailAndPassword(email,pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mLoadingBar.dismiss();
                                //credentials were valid

                                user = task.getResult().getUser();
                                if(!user.isEmailVerified()){
                                    FirebaseAuth.getInstance().signOut();
                                    resendEmailDialog();
                                }
                                else{
                                    mRef.child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            setupFlag = snapshot.child("setupFlag").getValue(Boolean.class);
                                            if(setupFlag){
                                                Intent intent = new Intent(SignInActivity.this,AllActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else{
                                                Intent intent = new Intent(SignInActivity.this,SetUpActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }

                            }else{
                                //credentials were invalid
                                mLoadingBar.dismiss();
                                Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void resendEmailDialog() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.verify_email_dialog2);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.verify_email_dialog_bg));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        btn_resend = dialog.findViewById(R.id.btn_resend);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        tv_email2 = dialog.findViewById(R.id.tv_email2);

        tv_email2.setText(user.getEmail());

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                user.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Verification email sent
                                    verifyEmailDialog();
                                  /*  Toast.makeText(getApplicationContext(),
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
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
            }
        });

        dialog.show();
    }

    private boolean CheckAllFields() {
        if(email.isEmpty()){
            showError(binding.emailId,"This field is required!");
            return false;
        }
        else if(!email.endsWith("@nmamit.in")){
            showError(binding.emailId,"The email should end with '@nmamit.in' domain");
            return false;
        }
        else if(pwd.isEmpty()){
            showError(binding.pwdId,"This field is required!");
            return false;
        }
        else if(pwd.length()<8){
            showError(binding.pwdId,"Password must be minimum 8 characters");
            return false;
        }
        return true;
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    ProgressDialog loadingBar;
    private void showResetPasswordDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText emailet= new EditText(this);

        emailet.setHint("Email");
        emailet.setMinEms(16);
        emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailet);
        linearLayout.setPadding(20,20,20,20);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email=emailet.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(SignInActivity.this,"Please enter valid email",Toast.LENGTH_SHORT).show();
                }
                else{
                    beginRecovery(email);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void beginRecovery(String email) {
        loadingBar=new ProgressDialog(this);
        loadingBar.setMessage("Sending Email....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        // calling sendPasswordResetEmail
        // open your email and write the new
        // password and then you can login
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loadingBar.dismiss();
                if(task.isSuccessful())
                {
                    // if isSuccessful then done message will be shown
                    // and you can change the password
                    Toast.makeText(SignInActivity.this,"Password reset email sent",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SignInActivity.this,"Failed to send password reset email",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}