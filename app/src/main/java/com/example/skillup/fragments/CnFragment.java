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

public class CnFragment extends Fragment {

    public static FragmentTransaction f;

    CardView osi, cn_net_cardview, cn_trans_cardview, cn_appn_cardview, cn_hw_cardview, cn_imp_cardview;

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



        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();

        f = getFragmentManager().beginTransaction();



        osi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                args.putString("mod","Introduction_and_OSI");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        cn_net_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                args.putString("mod"," Network_Layer");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });


        cn_trans_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                args.putString("mod","Transport_Layer");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_appn_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                args.putString("mod","Application_Layer");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        cn_hw_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("com","CN");
                args.putString("mod","Hardware");
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });




        return view;
    }
}
