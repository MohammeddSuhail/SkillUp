package com.example.skillup.Adapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.R;
import com.example.skillup.fragments.CnFragment;
import com.example.skillup.fragments.PlayerFrag;
import com.example.skillup.model.Video;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoAdapter extends FirebaseRecyclerAdapter<Video, VideoAdapter.MyViewHolder> {

    public VideoAdapter(@NonNull FirebaseRecyclerOptions<Video> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Video model) {
        final String videoKey = getRef(position).getKey();


//        holder.videoThumbNail.setImageDrawable(R.drawable.naruto);
        holder.video_title.setText(model.getVideoTitle());
        holder.important.setText(model.getImportant().toString());
        holder.duration.setText(model.getDuration().toString());
        holder.module_name.setText("Module");


        Video curVid = new Video(model.getVideoId(), model.getVideoLink(), model.getVideoTitle(), model.getID(), model.getDuration(), model.getImportant());

        String[] vid = {model.getVideoId(), model.getVideoLink(), model.getVideoTitle(), model.getID()+"", model.getDuration()+"", model.getImportant()+""};



        holder.wholeVidRowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerFrag playerFragment = new PlayerFrag();
                Bundle args = new Bundle();
                args.putStringArray("video",vid);
                playerFragment.setArguments(args);
                CnFragment.f.replace(R.id.fragment_container, playerFragment).commit();
            }
        });

    }


    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent,false);

        return new MyViewHolder(view);
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
}
