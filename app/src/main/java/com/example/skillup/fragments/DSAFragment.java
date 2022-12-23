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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DSAFragment extends Fragment {

    public static FragmentTransaction f;
    CardView time_complex_cardview, dp_cardview, math_cardview, binsearch_cardview, strings_cardview, slidwindow_cardview, recursion_cardview, array_cardview, dsa_guide_cardview, desc_cardview;
    VideoListFragment videoListFragment;
    DSADescFragment dsaDescFragment;
    DSAGuideFragment dsaGuideFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dsa_fragment, container, false);

        FirebaseAuth mAuth;
        FirebaseUser mUser;
        DatabaseReference mRef;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        dsaDescFragment = new DSADescFragment();
        dsaGuideFragment = new DSAGuideFragment();


        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();

        f = getFragmentManager().beginTransaction();



        time_complex_cardview = view.findViewById(R.id.time_complex_cardview);
        binsearch_cardview = view.findViewById(R.id.binsearch_cardview);
        slidwindow_cardview = view.findViewById(R.id.slidwindow_cardview);
        math_cardview = view.findViewById(R.id.math_cardview);
        recursion_cardview = view.findViewById(R.id.recursion_cardview);
        array_cardview = view.findViewById(R.id.array_cardview);
        strings_cardview = view.findViewById(R.id.strings_cardview);
        dp_cardview = view.findViewById(R.id.dp_cardview);

        desc_cardview = view.findViewById(R.id.desc_cardview);
        dsa_guide_cardview = view.findViewById(R.id.dsa_guide_cardview);


        time_complex_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Time_Complexity"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        array_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Arrays"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        strings_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Strings"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        binsearch_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Binary_Search"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        recursion_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Recursion"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        slidwindow_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Sliding_Window"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });
        dp_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Dynamic_Programming"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        math_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"DSA","Math_Pattern_Bit"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        desc_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, dsaDescFragment).addToBackStack(null).commit();
            }
        });

        dsa_guide_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, dsaGuideFragment).addToBackStack(null).commit();
            }
        });
        return view;
    }
}