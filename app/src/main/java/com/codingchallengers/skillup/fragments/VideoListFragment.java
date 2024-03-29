package com.codingchallengers.skillup.fragments;

import android.app.Application;
import android.app.ProgressDialog;
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


import com.codingchallengers.skillup.PlayerActivity;
import com.codingchallengers.skillup.R;
import com.codingchallengers.skillup.model.Video;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class VideoListFragment extends Fragment {

    String com, mod,imp;
    String ch="";
    DatabaseReference modRef;

    RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Video, MyViewHolder> adapter;

    public static Application act;

    ProgressDialog mLoadingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_list_fragment, container, false);


        //for loading bar
        mLoadingBar =  new ProgressDialog(getContext());
        mLoadingBar.setTitle("Loading");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();


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

                holder.videoThumbNail.setImageDrawable(null);
                holder.video_title.setText(model.getVideoTitle());


//                    if (model.getImportant().toString().equals("1"))
//                        holder.important.setText("IMPORTANT");
//                    else
//                        holder.important.setVisibility(View.GONE);

                holder.duration.setText(model.getDuration().toString());
                holder.module_name.setText(model.getModule());

                holder.important.setVisibility(View.GONE);

                if (model.getImportant().toString().equals("1")) {
                    holder.important.setVisibility(View.VISIBLE);
                    holder.important.setText("IMPORTANT");
                }
//

//                    holder.important.setVisibility(View.GONE);
//              https://img.youtube.com/vi/9SgLBjXqwd4/maxresdefault.jpg
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


            //removing loading bar once data is fetched and displayed
            @Override
            public void onDataChanged() {
                mLoadingBar.dismiss();
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
