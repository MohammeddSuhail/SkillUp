package com.example.skillup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends AppCompatActivity {

    CardView faq0, faq01, faq1, faq2, faq3, faq4, faq5, faq6, faq7, faq8, faq9, faq10, faq11, faq12, faq13, faq14, faq4i;
    LinearLayout efaq0, efaq01, efaq1, efaq2, efaq3, efaq4, efaq5, efaq6, efaq7, efaq8, efaq9, efaq10, efaq11, efaq12, efaq13, efaq14, efaq4i;
    View v0, v01, v1, v2, v3, v4,v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v4i;
    ImageView back_press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        getSupportActionBar().hide();

        back_press = findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(GuideActivity.this, AllActivity.class);
                startActivity(in);
            }
        });

        faq0=(CardView) findViewById(R.id.faq0);
        v0=findViewById(R.id.v0);
        efaq0=findViewById(R.id.efaq0);

        faq01=(CardView) findViewById(R.id.faq01);
        v01=findViewById(R.id.v01);
        efaq01=findViewById(R.id.efaq01);

        faq1=(CardView) findViewById(R.id.faq1);
        v1=findViewById(R.id.v1);
        efaq1=findViewById(R.id.efaq1);

        faq2=(CardView) findViewById(R.id.faq2);
        v2=findViewById(R.id.v2);
        efaq2=findViewById(R.id.efaq2);

        faq3=(CardView) findViewById(R.id.faq3);
        v3=findViewById(R.id.v3);
        efaq3=findViewById(R.id.efaq3);



        faq4=(CardView) findViewById(R.id.faq4);
        v4=findViewById(R.id.v4);
        efaq4=findViewById(R.id.efaq4);

        faq5=(CardView) findViewById(R.id.faq5);
        v5=findViewById(R.id.v5);
        efaq5=findViewById(R.id.efaq5);

        faq6=(CardView) findViewById(R.id.faq6);
        v6=findViewById(R.id.v6);
        efaq6=findViewById(R.id.efaq6);

        faq7=(CardView) findViewById(R.id.faq7);
        v7=findViewById(R.id.v7);
        efaq7=findViewById(R.id.efaq7);

        faq8=(CardView) findViewById(R.id.faq8);
        v8=findViewById(R.id.v8);
        efaq8=findViewById(R.id.efaq8);

        faq9=(CardView) findViewById(R.id.faq9);
        v9=findViewById(R.id.v9);
        efaq9=findViewById(R.id.efaq9);

        faq10=(CardView) findViewById(R.id.faq10);
        v10=findViewById(R.id.v10);
        efaq10=findViewById(R.id.efaq10);

        faq11=(CardView) findViewById(R.id.faq11);
        v11=findViewById(R.id.v11);
        efaq11=findViewById(R.id.efaq11);

        faq12=(CardView) findViewById(R.id.faq12);
        v12=findViewById(R.id.v12);
        efaq12=findViewById(R.id.efaq12);


        faq0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(efaq0.getVisibility()==View.GONE) {
                    v0.setBackgroundResource(R.drawable.up_24);
                    efaq0.setVisibility(View.VISIBLE);
                }
                else{
                    v0.setBackgroundResource(R.drawable.down_24);
                    efaq0.setVisibility(View.GONE);

                }

            }
        });

        faq01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(efaq01.getVisibility()==View.GONE) {
                    v01.setBackgroundResource(R.drawable.up_24);
                    efaq01.setVisibility(View.VISIBLE);
                }
                else{
                    v01.setBackgroundResource(R.drawable.down_24);
                    efaq01.setVisibility(View.GONE);
                }

            }
        });

        faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(efaq1.getVisibility()==View.GONE) {
                    v1.setBackgroundResource(R.drawable.up_24);
                    efaq1.setVisibility(View.VISIBLE);
                }
                else{
                    v1.setBackgroundResource(R.drawable.down_24);
                    efaq1.setVisibility(View.GONE);
                }

            }
        });
        faq2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(efaq2.getVisibility()==View.GONE) {
                            v2.setBackgroundResource(R.drawable.up_24);
                            efaq2.setVisibility(View.VISIBLE);
                        }
                        else{
                            v2.setBackgroundResource(R.drawable.down_24);
                            efaq2.setVisibility(View.GONE);
                        }

                    }
                });
        faq3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(efaq3.getVisibility()==View.GONE) {
                                    v3.setBackgroundResource(R.drawable.up_24);
                                    efaq3.setVisibility(View.VISIBLE);
                                }
                                else{
                                    v3.setBackgroundResource(R.drawable.down_24);
                                    efaq3.setVisibility(View.GONE);
                                }

                            }
                        });


       faq4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(efaq4.getVisibility()==View.GONE) {
                                    v4.setBackgroundResource(R.drawable.up_24);
                                    efaq4.setVisibility(View.VISIBLE);
                                }
                                else{
                                    v4.setBackgroundResource(R.drawable.down_24);
                                    efaq4.setVisibility(View.GONE);
                                }

                            }
                        });

       faq5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq5.getVisibility()==View.GONE) {
                   v5.setBackgroundResource(R.drawable.up_24);
                   efaq5.setVisibility(View.VISIBLE);
               }
               else{
                   v5.setBackgroundResource(R.drawable.down_24);
                   efaq5.setVisibility(View.GONE);
               }

           }
       });
       faq6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq6.getVisibility()==View.GONE) {
                   v6.setBackgroundResource(R.drawable.up_24);
                   efaq6.setVisibility(View.VISIBLE);
               }
               else{
                   v6.setBackgroundResource(R.drawable.down_24);
                   efaq6.setVisibility(View.GONE);
               }

           }
       });

       faq7.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq7.getVisibility()==View.GONE) {
                   v7.setBackgroundResource(R.drawable.up_24);
                   efaq7.setVisibility(View.VISIBLE);
               }
               else{
                   v7.setBackgroundResource(R.drawable.down_24);
                   efaq7.setVisibility(View.GONE);
               }

           }
       });
       faq8.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq8.getVisibility()==View.GONE) {
                   v8.setBackgroundResource(R.drawable.up_24);
                   efaq8.setVisibility(View.VISIBLE);
               }
               else{
                   v8.setBackgroundResource(R.drawable.down_24);
                   efaq8.setVisibility(View.GONE);
               }

           }
       });
       faq9.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq9.getVisibility()==View.GONE) {
                   v9.setBackgroundResource(R.drawable.up_24);
                   efaq9.setVisibility(View.VISIBLE);
               }
               else{
                   v9.setBackgroundResource(R.drawable.down_24);
                   efaq9.setVisibility(View.GONE);
               }

           }
       });
       faq10.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq10.getVisibility()==View.GONE) {
                   v10.setBackgroundResource(R.drawable.up_24);
                   efaq10.setVisibility(View.VISIBLE);
               }
               else{
                   v10.setBackgroundResource(R.drawable.down_24);
                   efaq10.setVisibility(View.GONE);
               }

           }
       });

       faq11.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq11.getVisibility()==View.GONE) {
                   v11.setBackgroundResource(R.drawable.up_24);
                   efaq11.setVisibility(View.VISIBLE);
               }
               else{
                   v11.setBackgroundResource(R.drawable.down_24);
                   efaq11.setVisibility(View.GONE);
               }

           }
       });

       faq12.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(efaq12.getVisibility()==View.GONE) {
                   v12.setBackgroundResource(R.drawable.up_24);
                   efaq12.setVisibility(View.VISIBLE);
               }
               else{
                   v12.setBackgroundResource(R.drawable.down_24);
                   efaq12.setVisibility(View.GONE);
               }

           }
       });

    }
}