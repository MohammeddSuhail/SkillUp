package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;

public class DbmsFragment extends Fragment {

    CardView dbms_intro_cardview, dbms_key_cardview, dbms_sql_cardview, dbms_normal_cardview, dbms_trans_cardview;

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

        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();



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

        return view;
    }
}
