package com.codingchallengers.skillup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codingchallengers.skillup.Notes.NotesActivity;
import com.codingchallengers.skillup.databinding.ActivityAllBinding;

import com.codingchallengers.skillup.fragments.CommunityFragment;
import com.codingchallengers.skillup.fragments.HomeFragment;
import com.codingchallengers.skillup.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    ActivityAllBinding binding;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    String profileImageUrlV,usernameV,professionV;

    public static CircleImageView profileImage;
    TextView name,profession;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottom_nav_home:
                                // handle home item selection
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                                return true;
                            case R.id.bottom_nav_notes:
                                // handle notifications item selection
                                Intent intent = new Intent(AllActivity.this, NotesActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                return true;
                            case R.id.bottom_nav_community:
                                // handle dashboard item selection
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CommunityFragment()).commit();
                                return true;
                            case R.id.bottom_nav_profile:
                                // handle notifications item selection
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                                return true;
                        }
                        return false;
                    }
                });
        setNavigationVisibility(true);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();


        NavigationView navigationView = binding.navView;
        //to access the views present in header of drawer
        View view = navigationView.inflateHeaderView(R.layout.nav_header);

        profileImage = view.findViewById(R.id.profile_pic_header);
        name = view.findViewById(R.id.user_name_header);
        profession = view.findViewById(R.id.profession_header);

        //onClickListener for items of navigation bar
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        drawer = binding.drawerLayout;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();

        if(savedInstanceState == null){
            //at first home fragment is loaded so
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            //for indicating that home fragment is checked
            navigationView.setCheckedItem(R.id.nav_courses);
        }
        getSupportActionBar().hide();
    }


    //if pressed back it should not close the the app, but go to home page
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;

            case R.id.nav_notes:
                Intent intent = new Intent(AllActivity.this, NotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;


            case R.id.nav_scanner:
//                QrScannerFragment qrScannerFragment = new QrScannerFragment();
//                Bundle args = new Bundle();
//                args.putString("et_qr_result","QR Code Result");
//                qrScannerFragment.setArguments(args);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,qrScannerFragment).commit();
                Intent intentt = new Intent(AllActivity.this, QrScannerActivity.class);
                intentt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentt);
                break;

            case R.id.nav_guide:
                Intent in = new Intent(AllActivity.this, GuideActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                break;



            case R.id.nav_community:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CommunityFragment()).commit();
                break;


//
//            case R.id.nav_mentors:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MentorFragment()).commit();
//                break;

            case R.id.nav_contributors:
                Intent inten = new Intent(AllActivity.this, ContributorActivity.class);
                inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inten);
                break;

            case R.id.nav_logout:
                mAuth.signOut();
                Intent i = new Intent(AllActivity.this,SignInActivity.class);
                startActivity(i);
                finish();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mUser==null){
            Intent i = new Intent(AllActivity.this,SignInActivity.class);
            startActivity(i);
            finish();
        }else{
            mRef.child("Users").child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    profileImageUrlV = snapshot.child("profileImage").getValue().toString();
                    usernameV = snapshot.child("userName").getValue().toString();
                    professionV = snapshot.child("profession").getValue().toString();

                    Picasso.get().load(profileImageUrlV).fit().centerCrop().into(profileImage);
                    name.setText(usernameV);
                    profession.setText(professionV);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AllActivity.this,"Sorry Something Went Wrong",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    protected void setNavigationVisibility(boolean visible) {
        bottomNavigationView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

}