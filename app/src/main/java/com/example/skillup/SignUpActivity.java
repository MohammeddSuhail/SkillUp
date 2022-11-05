package com.example.skillup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.skillup.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    private FirebaseAuth auth;
    CardView signupCardview;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

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
                mLoadingBar =  new ProgressDialog(SignUpActivity.this);
                mLoadingBar.setTitle("Signing Up");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                //adding user to (firebase authentication)
                String email = binding.emailId.getText().toString();
                String pw = binding.passwordId.getText().toString();

                auth.createUserWithEmailAndPassword(email,pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //done adding email and password in auth
                                    mLoadingBar.dismiss();
                                    Intent i = new Intent(SignUpActivity.this,SetUpActivity.class);
                                    startActivity(i);
                                    finish();

                                    Toast.makeText(SignUpActivity.this,"Account Successfully Created",Toast.LENGTH_SHORT).show();
                                }else{
                                    mLoadingBar.dismiss();
                                    Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}