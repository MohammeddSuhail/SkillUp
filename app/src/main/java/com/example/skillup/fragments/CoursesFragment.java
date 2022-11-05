package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.skillup.R;

public class CoursesFragment extends Fragment {

    public static FragmentTransaction f;

    CardView cn,dbms,os,oops;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.courses_fragment, container, false);

        f = getFragmentManager().beginTransaction();

//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cn = (CardView) view.findViewById(R.id.cncardview);
        dbms = (CardView)view.findViewById(R.id.dbmscardview);
        os = (CardView)view.findViewById(R.id.oscardview);
        oops =(CardView) view.findViewById(R.id.oopscardview);

        CoursesFragment cf = new CoursesFragment();
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new CnFragment()).commit();
            }
        });
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new DbmsFragment()).commit();
            }
        });
        os.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new OsFragment()).commit();
            }
        });
        oops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new OopsFragment()).commit();
            }
        });


        return view;
    }

//    public void changeFragment(Fragment obj){
//        getFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).commit();
//    }
}
