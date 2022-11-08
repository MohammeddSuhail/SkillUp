package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

//        getFragmentManager().popBackStack();

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
                CoursesFragment.f.replace(R.id.fragment_container, new CnFragment()).addToBackStack(null).commit();
            }
        });
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new DbmsFragment()).addToBackStack(null).commit();
            }
        });
        os.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new OsFragment()).addToBackStack(null).commit();
            }
        });
        oops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CoursesFragment.f.replace(R.id.fragment_container, new OopsFragment()).addToBackStack(null).commit();
            }
        });




        //back pressed
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }
}
