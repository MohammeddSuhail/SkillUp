package com.codingchallengers.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codingchallengers.skillup.Note.CNnotesFragment;
import com.codingchallengers.skillup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CnFragment extends Fragment {

    public static FragmentTransaction f;

    CardView osi, cn_net_cardview, cn_trans_cardview, cn_appn_cardview, cn_hw_cardview, cn_imp_cardview, cn_notes_cardview,cn_pin_cardview;
    TextView search;
    static int imp=0;
    VideoListFragment videoListFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cn_fragment,container,false);

        osi = view.findViewById(R.id.cn_osi_cardview);
        cn_net_cardview = view.findViewById(R.id.cn_net_cardview);
        cn_trans_cardview = view.findViewById(R.id.cn_trans_cardview);
        cn_appn_cardview = view.findViewById(R.id.cn_appn_cardview);
        cn_hw_cardview = view.findViewById(R.id.cn_hw_cardview);
        cn_imp_cardview = view.findViewById(R.id.cn_imp_cardview);
        cn_notes_cardview = view.findViewById(R.id.cn_notes_cardview);
        cn_pin_cardview = view.findViewById(R.id.cn_pin_cardview);
        search = view.findViewById(R.id.cn_search);


        FirebaseAuth mAuth;
        FirebaseUser mUser;
        DatabaseReference mRef;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();

        f = getFragmentManager().beginTransaction();

        osi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN","Introduction_and_OSI"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        cn_net_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN"," Network_Layer"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        cn_trans_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN","Transport_Layer"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_appn_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN","Application_Layer"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_hw_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN","Hardware"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_imp_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imp=1;
                String[] arguments = {"CN","All_Videos"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"CN","All_Videos"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_notes_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CNnotesFragment()).addToBackStack(null).commit();
            }
        });


        cn_pin_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"Pinned",mUser.getUid(),"CN"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
