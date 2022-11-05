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

import com.example.skillup.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    FirebaseUser mUser;
    CardView signinCardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

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

        binding.signInButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingBar =  new ProgressDialog(SignInActivity.this);
                mLoadingBar.setTitle("Signing In");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                //checking if the user is present in firebase
                String email = binding.emailId.getText().toString();
                String pw = binding.passwordId.getText().toString();

                mAuth.signInWithEmailAndPassword(email,pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //credentials were valid
                                    mLoadingBar.dismiss();
//                                    Intent intent = new Intent(SignInActivity.this,Util.class);
                                    Intent intent = new Intent(SignInActivity.this,AllActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    //credentials were invalid
                                    mLoadingBar.dismiss();
                                    Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        //if user is already logged in
        if(mAuth.getCurrentUser()!=null){
//            Intent intent = new Intent(SignInActivity.this,AllActivity.class);
            Intent intent = new Intent(SignInActivity.this,AllActivity.class);
//            intent.putExtra("interest",interest);
            startActivity(intent);
            finish();
        }

    }

}