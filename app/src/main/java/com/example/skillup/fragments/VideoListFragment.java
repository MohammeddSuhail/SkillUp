package com.example.skillup.fragments;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.skillup.PlayerActivity;
import com.example.skillup.R;
import com.example.skillup.model.Video;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class VideoListFragment extends Fragment {

    String com, mod,imp;

    DatabaseReference modRef;

    RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Video, MyViewHolder> adapter;

    public static Application act;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_list_fragment, container, false);

        act = getActivity().getApplication();

        String[] arguments = getArguments().getStringArray("arguments");


        //Toast.makeText(getContext(), com+" "+mod, Toast.LENGTH_SHORT).show();

        modRef = FirebaseDatabase.getInstance().getReference();
        //.child(com).child(mod);

        for (int i = 0; i < arguments.length; i++) {
            modRef = modRef.child(arguments[i]);
        }

        //Below 3 lines for Firebase RecycleView
        recyclerView = view.findViewById(R.id.recyclerViewVideoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Video> options = new FirebaseRecyclerOptions.Builder<Video>().setQuery(modRef, Video.class).build();

//        adapter = new VideoAdapter(options);
//        recyclerView.setAdapter(adapter);


        adapter = new FirebaseRecyclerAdapter<Video, MyViewHolder>(options) {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent,false);

                return new MyViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Video model) {
                final String videoKey = getRef(position).getKey();

                if(model.getImportant()==1)
                    imp = "IMPORTANT";
                else
                    imp = "";

                holder.videoThumbNail.setImageDrawable(null);
                holder.video_title.setText(model.getVideoTitle());
                holder.important.setText(imp);
                holder.duration.setText(model.getDuration().toString());
                holder.module_name.setText(model.getModule());


                Picasso.get().load("https://img.youtube.com/vi/" + model.getVideoId() + "/maxresdefault.jpg").into(holder.videoThumbNail);

                Video curVid = new Video(model.getVideoId(), model.getVideoLink(), model.getVideoTitle(), model.getID(), model.getDuration(), model.getImportant(), model.getModule());

                String[] vid = {model.getVideoId(), model.getVideoLink(), model.getVideoTitle(), model.getID()+"", model.getDuration()+"", model.getImportant()+"",model.getModule()};



                //if the rows recyclerView in the find friend is clicked
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplication(), PlayerActivity.class);
                        intent.putExtra("video", vid);
                        //intent.putExtra("userKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };


        adapter.startListening();
        recyclerView.setAdapter(adapter);




        return view;

    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView videoThumbNail;
        TextView video_title, important, duration, module_name;
        LinearLayout wholeVidRowId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            videoThumbNail = itemView.findViewById(R.id.video_thumbnail);
            video_title = itemView.findViewById(R.id.video_title);
            important = itemView.findViewById(R.id.important);
            duration = itemView.findViewById(R.id.duration);
            module_name = itemView.findViewById(R.id.module_name);
            wholeVidRowId = itemView.findViewById(R.id.wholeVidRowId);
        }
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
