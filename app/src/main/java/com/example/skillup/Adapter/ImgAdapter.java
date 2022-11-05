package com.example.skillup.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.R;
import com.example.skillup.fragments.CnFragment;
import com.example.skillup.fragments.CoursesFragment;
import com.example.skillup.fragments.OopsFragment;
import com.example.skillup.fragments.OsFragment;
import com.example.skillup.fragments.DbmsFragment;

import java.util.List;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;

    public ImgAdapter(Context ctx, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            gridIcon = itemView.findViewById(R.id.imageView2);

            CoursesFragment cf = new CoursesFragment();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(getAdapterPosition()==0){
                        CoursesFragment.f.replace(R.id.fragment_container, new CnFragment()).commit();
                    }
                    else if(getAdapterPosition()==1){
                        CoursesFragment.f.replace(R.id.fragment_container, new DbmsFragment()).commit();
                    }
                    else if(getAdapterPosition()==2){
                        CoursesFragment.f.replace(R.id.fragment_container, new OsFragment()).commit();
                    }
                    else if(getAdapterPosition()==3){
                        CoursesFragment.f.replace(R.id.fragment_container, new OopsFragment()).commit();
                    }
//                    else if(getAdapterPosition()==4){
//                    CoursesFragment.f.replace(R.id.fragment_container, new dsaFragment()).commit();
//                        Intent intent = new Intent(itemView.getContext(), UiuxActivity.class);
//                        itemView.getContext().startActivity(intent);
//
//
//
//                        Activity activity = (Activity) itemView.getContext();
//                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                    }
//                    else if(getAdapterPosition()==5){
//                        Intent intent = new Intent(itemView.getContext(), IotActivity.class);
//                        itemView.getContext().startActivity(intent);
//                        Activity activity = (Activity) itemView.getContext();
//                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                    }

                }
            });
        }
    }
}