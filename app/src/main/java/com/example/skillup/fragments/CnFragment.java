package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.Adapter.ImgAdapter;
import com.example.skillup.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CnFragment extends Fragment {

    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    ImgAdapter imgAdapter;
    //CNVideo[] cnList;
    //FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cn_fragment,container,false);



        dataList = view.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Introduction\nand OSI");
        titles.add("Network\nLayer");
        titles.add("Transport\nLayer");
        titles.add("Application\nLayer");
        titles.add("Networking\nHardware");
        titles.add("Important\nTopics");

        images.add(R.drawable.cn_osi_logo);
        images.add(R.drawable.cn_net_logo);
        images.add(R.drawable.cn_transport_logo);
        images.add(R.drawable.cn_appn_logo);
        images.add(R.drawable.cn_hard_logo);
        images.add(R.drawable.imp_logo);

        imgAdapter = new ImgAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(imgAdapter);


        return view;
    }
}
