package com.codingchallengers.skillup;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        //sort of sleep for few seconds
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                //checking internet connectivity
                if(!isNetworkAvailable(SplashScreen.this)){
                    Toast.makeText(SplashScreen.this, "No Internet", Toast.LENGTH_LONG).show();
                }


                //if user exists then we check if he has filed setup activity, if yes->AllActivity if no->setUpActivity
                if(mUser!=null){
                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //checking if data is present inside database for the signed in user
                            if(snapshot.exists()){
                                Toast.makeText(SplashScreen.this,"success",Toast.LENGTH_SHORT);


                                Boolean setupFlag = snapshot.child("setupFlag").getValue(Boolean.class);
                                if(setupFlag){
                                    Intent intent = new Intent(SplashScreen.this,AllActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    //if user is already logged in (mainly when user comes to setup page and exits app without completing setup)
                                    Intent intent = new Intent(SplashScreen.this,SetUpActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            }
                            else{
                                Intent intent = new Intent(SplashScreen.this,SetUpActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Intent intent = new Intent(SplashScreen.this,SignInActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1300);

    }

    //for checl=king internet connectivity
    public static boolean isNetworkAvailable(Context activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}