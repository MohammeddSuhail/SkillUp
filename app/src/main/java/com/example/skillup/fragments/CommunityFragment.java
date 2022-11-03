package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;

public class CommunityFragment extends Fragment {
    TextView textViewDSA, textViewCN, textViewDBMS, textViewOS, textViewOOPS;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_fragment, container, false);

        textViewDSA = view.findViewById(R.id.textViewDSA);
        textViewCN = view.findViewById(R.id.textViewCN);
        textViewDBMS = view.findViewById(R.id.textViewDBMS);
        textViewOS = view.findViewById(R.id.textViewOS);
        textViewOOPS = view.findViewById(R.id.textViewOOPS);

        EachCommunityFragment eachCommunityFragment = new EachCommunityFragment();
        Bundle args = new Bundle();


        textViewDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DSA");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        textViewCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        textViewDBMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        textViewOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","OS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });

        textViewOOPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","OOPS");
                eachCommunityFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,eachCommunityFragment).commit();
            }
        });


        return view;
    }
}
