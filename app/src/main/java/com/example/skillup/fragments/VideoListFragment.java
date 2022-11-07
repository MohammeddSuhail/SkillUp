package com.example.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.Adapter.VideoAdapter;
import com.example.skillup.R;
import com.example.skillup.model.Video;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class VideoListFragment extends Fragment {

    String com, mod;

    DatabaseReference modRef;

    RecyclerView recyclerView;

    VideoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_list_fragment, container, false);


        com = getArguments().getString("com");
        mod = getArguments().getString("mod");

        //Toast.makeText(getContext(), com+" "+mod, Toast.LENGTH_SHORT).show();

        modRef = FirebaseDatabase.getInstance().getReference().child(com).child(mod);

        //Below 3 lines for Firebase RecycleView
        recyclerView = view.findViewById(R.id.recyclerViewVideoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Video> options = new FirebaseRecyclerOptions.Builder<Video>().setQuery(modRef, Video.class).build();


        adapter = new VideoAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.startListening();
    }
}
