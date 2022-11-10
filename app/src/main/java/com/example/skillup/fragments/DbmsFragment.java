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
                args.putString("com","DBMS");
                args.putString("mod","Introduction_to_DBMS");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        dbms_key_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                args.putString("mod"," Keys_and_Relationships");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_normal_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                args.putString("mod","Normalization_in_Database");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_sql_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                args.putString("mod","SQL_Queries");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        dbms_trans_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","DBMS");
                args.putString("mod","Transaction_and_schedules");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
