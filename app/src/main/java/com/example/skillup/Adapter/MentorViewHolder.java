package com.example.skillup.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.R;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MentorViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView profileImageF;
    public TextView username,profession;

    public MentorViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        profileImageF = itemView.findViewById(R.id.profileImageFri);
        username = itemView.findViewById(R.id.usernameFri);
        profession = itemView.findViewById(R.id.professionFri);

    }
}
