package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.skillup.Note.CNnotesFragment;
import com.example.skillup.Note.OSNotesFragment;
import com.example.skillup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OsFragment extends Fragment {

    CardView os_imp_cardview, os_intro_cardview, os_notes_cardview, os_process_cardview, os_schedule_cardview, os_sync_cardview, os_mem_cardview, os_page_cardview, os_cmd_cardview, os_pin_cardview;
    VideoListFragment videoListFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.os_fragment,container,false);

        os_notes_cardview = view.findViewById(R.id.os_notes_cardview);
        os_intro_cardview = view.findViewById(R.id.os_intro_cardview);
        os_process_cardview = view.findViewById(R.id.os_process_cardview);
        os_schedule_cardview = view.findViewById(R.id.os_schedule_cardview);
        os_sync_cardview = view.findViewById(R.id.os_sync_cardview);
        os_mem_cardview = view.findViewById(R.id.os_mem_cardview);
        os_page_cardview = view.findViewById(R.id.os_page_cardview);
        os_cmd_cardview = view.findViewById(R.id.os_cmd_cardview);
        os_imp_cardview = view.findViewById(R.id.os_imp_cardview);
        os_pin_cardview = view.findViewById(R.id.os_pin_cardview);

        FirebaseAuth mAuth;
        FirebaseUser mUser;
        DatabaseReference mRef;

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference();

        videoListFragment = new VideoListFragment();
        Bundle args = new Bundle();

        os_intro_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS"," Introduction_to_OS"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_process_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","Processes_and_Threads"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_schedule_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","Process_Scheduling"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_sync_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","Process_Synchronization"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_mem_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","Memory_Management"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_notes_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new OSNotesFragment()).addToBackStack(null).commit();
            }
        });


        os_page_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","Page_Replacement"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_cmd_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","UNIX_Commands"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_pin_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"Pinned",mUser.getUid(),"OS"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        os_imp_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] arguments = {"OS","All_Videos"};
                args.putStringArray("arguments",arguments);
                videoListFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, videoListFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
