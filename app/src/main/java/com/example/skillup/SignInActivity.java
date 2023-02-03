package com.example.skillup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private FirebaseAuth mAuth;
    DatabaseReference mRef;
    ProgressDialog mLoadingBar;
    FirebaseUser mUser;
    CardView signinCardview;
    Boolean setupFlag;
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

        binding.signInButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingBar =  new ProgressDialog(SignInActivity.this);
                mLoadingBar.setTitle("Signing In");
                mLoadingBar.setCanceledOnTouchOutside(false);
                mLoadingBar.show();

                //checking if the user is present in firebase
                String email = binding.emailId.getText().toString();
                String pw = binding.pwdId.getText().toString();

                mAuth.signInWithEmailAndPassword(email,pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    mLoadingBar.dismiss();
                                    //credentials were valid
                                    FirebaseUser user = task.getResult().getUser();
                                    if(!user.isEmailVerified()){
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            // Verification email sent
                                                            FirebaseAuth.getInstance().signOut();
                                                            Toast.makeText(getApplicationContext(),
                                                                    "Verification email sent to " + user.getEmail() + "\nPlease verify your email id" ,
                                                                    Toast.LENGTH_LONG).show();
                                                            Log.d("Verification", "Verification email sent to " + user.getEmail());
                                                        } else {
                                                            // Error sending verification email
                                                            Log.e("TAG", "sendEmailVerification", task.getException());
                                                            Toast.makeText(getApplicationContext(),
                                                                    "Failed to send verification email.",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
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

//                                    Intent intent = new Intent(SignInActivity.this,Util.class);

                                    }

                                }else{
                                    //credentials were invalid
                                    mLoadingBar.dismiss();
                                    Toast.makeText(SignInActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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

}