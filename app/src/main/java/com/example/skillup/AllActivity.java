package com.example.skillup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skillup.databinding.ActivityAllBinding;
import com.example.skillup.fragments.CommunityFragment;
import com.example.skillup.fragments.CoursesFragment;
import com.example.skillup.fragments.MentorsFragment;
import com.example.skillup.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    ActivityAllBinding binding;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    String profileImageUrlV,usernameV,professionV;

    CircleImageView profileImage;
    TextView name,profession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CoursesFragment()).commit();
            //for indicating that home fragment is checked
            navigationView.setCheckedItem(R.id.nav_courses);
        }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CoursesFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;

            case R.id.nav_community:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CommunityFragment()).commit();
                break;

            case R.id.nav_mentors:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MentorsFragment()).commit();
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

                    Picasso.get().load(profileImageUrlV).into(profileImage);
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
}