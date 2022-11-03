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

import java.util.ArrayList;
import java.util.List;

public class OsFragment extends Fragment {

    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    ImgAdapter imgAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.os_fragment,container,false);

        dataList = view.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Introduction\nto OS");
        titles.add("Processes\nand Threads");
        titles.add("Process\nScheduling");
        titles.add("Process\nSync");
        titles.add("Memory\nManagement");
        titles.add("Page\nReplacement");
        titles.add("UNIX\nCommands");
        titles.add("Important\nTopics");

        images.add(R.drawable.os_intro_logo);
        images.add(R.drawable.os_process_logo);
        images.add(R.drawable.os_schedule_logo);
        images.add(R.drawable.os_sync_logo);
        images.add(R.drawable.os_memory_logo);
        images.add(R.drawable.os_page_logo);
        images.add(R.drawable.os_command_logo);
        images.add(R.drawable.imp_logo);

        imgAdapter = new ImgAdapter(getContext(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(imgAdapter);

        return view;
    }
}
