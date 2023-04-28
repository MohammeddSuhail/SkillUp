package com.codingchallengers.skillup.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingchallengers.skillup.R;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView profileImage;
    public TextView username, comment, timeAgo;

    public CommentViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        profileImage = itemView.findViewById(R.id.profileImage_comment);
        username = itemView.findViewById(R.id.username_comment);
        comment = itemView.findViewById(R.id.commentTV);
        timeAgo = itemView.findViewById(R.id.timeAgo3);

    }
}
