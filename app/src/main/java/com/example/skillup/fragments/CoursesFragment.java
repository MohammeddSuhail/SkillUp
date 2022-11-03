package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.Adapter.ImgAdapter;
import com.example.skillup.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    ImgAdapter imgAdapter;
    public static FragmentTransaction f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.courses_fragment, container, false);

        f = getFragmentManager().beginTransaction();

//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataList = view.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Computer\nNetworks");
        titles.add("DBMS &\nSQL");
        titles.add("Operating\nSystem");
        titles.add("OOPS\nConcepts");

        images.add(R.drawable.cn_logo);
        images.add(R.drawable.dbms_logo);
        images.add(R.drawable.os_logo);
        images.add(R.drawable.oops_logo);

        imgAdapter = new ImgAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(imgAdapter);

        return view;
    }

//    public void changeFragment(Fragment obj){
//        getFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).commit();
//    }
}
