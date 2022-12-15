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

public class DSAFragment extends Fragment {

    CardView os_intro_cardview, os_notes_cardview, os_process_cardview, os_schedule_cardview, os_sync_cardview, os_mem_cardview, os_page_cardview, os_cmd_cardview, os_pin_cardview;
    VideoListFragment videoListFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dsa_fragment, container, false);

        return view;
    }
}