package com.codingchallengers.skillup.fragments;

import android.content.Intent;
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

import com.codingchallengers.skillup.ContributorActivity;
import com.codingchallengers.skillup.EditDetailsActivity;
import com.codingchallengers.skillup.GuideActivity;
import com.codingchallengers.skillup.R;

public class HomeFragment extends Fragment {

    public static FragmentTransaction f;


    CardView courses,faq,mentor, ie, resBuilder;
    TextView actiontext;

    public static String course;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

//        getFragmentManager().popBackStack();

        f = getFragmentManager().beginTransaction();

        actiontext = getActivity().findViewById(R.id.v_action_text);
        actiontext.setText("Dashboard");

        courses = (CardView) view.findViewById(R.id.cscourse);
        faq = (CardView) view.findViewById(R.id.faqcardiew1);
        ie = (CardView) view.findViewById(R.id.ie_cardview);
        mentor = (CardView) view.findViewById(R.id.mentorcardview);
        resBuilder = (CardView) view.findViewById(R.id.resumebuildcardview);

        HomeFragment cf = new HomeFragment();
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment.f.replace(R.id.fragment_container, new CoursesFragment()).addToBackStack(null).commit();
            }
        });

        ie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment.f.replace(R.id.fragment_container, new IntExpListFragment()).addToBackStack(null).commit();
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GuideActivity.class);
                startActivity(intent);
            }
        });

        mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContributorActivity.class);
                startActivity(intent);
            }
        });


        resBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditDetailsActivity.class);
                startActivity(intent);
            }
        });

//
//
//
//        cn = (CardView)view.findViewById(R.id.cncardview);
//        dbms = (CardView)view.findViewById(R.id.dbmscardview);
//        os = (CardView)view.findViewById(R.id.oscardview);
//        oops =(CardView)view.findViewById(R.id.oopscardview);
//        dsa=(CardView)view.findViewById(R.id.dsacardview);
//        ie = (CardView)view.findViewById(R.id.ie_cardview);
//        res = (CardView)view.findViewById(R.id.rescardview);
//        rescardviewBuilder = (CardView) view.findViewById(R.id.rescardviewBuilder);
//
//
//        HomeFragment cf = new HomeFragment();
//        cn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "CN";
//                HomeFragment.f.replace(R.id.fragment_container, new CnFragment()).addToBackStack(null).commit();
//            }
//        });
//        dbms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "DBMS";
//                HomeFragment.f.replace(R.id.fragment_container, new DbmsFragment()).addToBackStack(null).commit();
//            }
//        });
//        os.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "OS";
//                HomeFragment.f.replace(R.id.fragment_container, new OsFragment()).addToBackStack(null).commit();
//            }
//        });
//
//
//        oops.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "OOPS";
//                VideoListFragment videoListFragment = new VideoListFragment();
//                Bundle args = new Bundle();
//                String[] arguments = {"OOPS","introduction_to_OOPS"};
//                args.putStringArray("arguments",arguments);
//                videoListFragment.setArguments(args);
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
//            }
//        });
//
//
//        res.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "RES";
//                VideoListFragment videoListFragment = new VideoListFragment();
//                Bundle args = new Bundle();
//                String[] arguments = {"RES","resume_building"};
//                args.putStringArray("arguments",arguments);
//                videoListFragment.setArguments(args);
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
//            }
//        });
//
//        dsa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                course = "DSA";
//                HomeFragment.f.replace(R.id.fragment_container, new DSAFragment()).addToBackStack(null).commit();
//            }
//        });
//
//        ie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeFragment.f.replace(R.id.fragment_container, new IntExpListFragment()).addToBackStack(null).commit();
//            }
//        });
//
//
//        rescardviewBuilder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), EditDetailsActivity.class);
//                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });
//
//
//
//
//        //back pressed
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//
//        view.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (event.getAction() == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        getActivity().finish();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });


        return view;
    }
}
