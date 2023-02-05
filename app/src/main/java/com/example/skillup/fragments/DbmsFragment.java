package com.example.skillup.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbmsFragment extends Fragment {

    CardView dbms_notes_cardview, dbms_intro_cardview, dbms_key_cardview, dbms_sql_cardview, dbms_normal_cardview, dbms_trans_cardview, dbms_imp_cardview, dbms_pin_cardview;

    VideoListFragment videoListFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dbms_fragment,container,false);


        dbms_intro_cardview = view.findViewById(R.id.dbms_intro_cardview);
        dbms_key_cardview = view.findViewById(R.id.dbms_key_cardview);
        dbms_normal_cardview = view.findViewById(R.id.dbms_normal_cardview);
        dbms_sql_cardview = view.findViewById(R.id.dbms_sql_cardview);
        dbms_trans_cardview = view.findViewById(R.id.dbms_trans_cardview);
        dbms_imp_cardview = view.findViewById(R.id.dbms_imp_cardview);
        dbms_pin_cardview = view.findViewById(R.id.dbms_pin_cardview);
        dbms_notes_cardview = view.findViewById(R.id.dbms_notes_cardview);

        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();


        FirebaseAuth mAuth;
        FirebaseUser mUser;
        DatabaseReference mRef;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();



        dbms_intro_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS","Introduction_to_DBMS"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        dbms_key_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS"," Keys_and_Relationships"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_normal_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS","Normalization_in_Database"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_sql_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS","SQL_Queries"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_trans_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS","Transaction_and_schedules"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_pin_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"Pinned",mUser.getUid(),"DBMS"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_imp_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DBMS","All_Videos"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_notes_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://drive.google.com/file/d/1E6L93W-tUSWgnKhoJr8UqaIQmJbGiqJg/view?usp=sharing";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return view;
    }
}
