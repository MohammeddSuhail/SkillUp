package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;

public class CommunityFragment extends Fragment {
    CardView csDSA, csCN, csDBMS, csOS, csOOPS; //community section options

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_fragment, container, false);

        csDSA = view.findViewById(R.id.csDSA);
        csCN = view.findViewById(R.id.csCN);
        csDBMS = view.findViewById(R.id.csDBMS);
        csOS = view.findViewById(R.id.csOS);
        csOOPS = view.findViewById(R.id.csOOPS);

        EachCommunityFragment eachCommunityFragment = new EachCommunityFragment();
        Bundle args = new Bundle();

        getActivity().setTitle("Community");

        csDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DSA");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        csCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        csDBMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        csOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","OS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        csOOPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","OOPS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
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
                        getActivity().setTitle("Placemaker");
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });


        return view;
    }
}
