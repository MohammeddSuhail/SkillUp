package com.example.skillup.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillup.ExpViewActivity;
import com.example.skillup.PlayerActivity;
import com.example.skillup.R;
import com.example.skillup.model.Experience;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IntExpListFragment extends Fragment {


    DatabaseReference modRef;

    RecyclerView recyclerView;

    FirebaseRecyclerAdapter<Experience, IntExpListFragment.ExpViewHolder> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_list_fragment,container,false);


        modRef = FirebaseDatabase.getInstance().getReference().child("Interview_Experiences");



        //Below 3 lines for Firebase RecycleView
        recyclerView = view.findViewById(R.id.recyclerViewVideoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        FirebaseRecyclerOptions<Experience> options = new FirebaseRecyclerOptions.Builder<Experience>().setQuery(modRef, Experience.class).build();



        adapter = new FirebaseRecyclerAdapter<Experience, ExpViewHolder>(options) {

            @NonNull
            @Override
            public ExpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ie_list_item, parent,false);

                return new IntExpListFragment.ExpViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ExpViewHolder holder, int position, @NonNull Experience model) {

                Experience exp = new Experience(model.getCTCOffered(),model.getAdviceToFreshers(),model.getCompanyName(),model.getRole(),model.getRounds(),model.getSelected(),model.getType(),model.getCampusDriveYear());

                holder.company_name.setText(exp.getCompanyName());
                holder.placed_year.setText(exp.getCampusDriveYear()+"");
                holder.role.setText(exp.getRole());
                holder.type.setText(exp.getType());
                holder.ctc.setText(exp.getCTCOffered());


                String[] experience = {exp.getCTCOffered(),exp.getAdviceToFreshers(),exp.getCompanyName(),exp.getRole(),exp.getRounds(),exp.getSelected(),exp.getType(),exp.getCampusDriveYear()+""};

                //if the rows recyclerView in the find friend is clicked
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplication(), ExpViewActivity.class);
                        intent.putExtra("experience", experience);
                        startActivity(intent);
                    }
                });


            }


        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);


        return view;
    }




    public class ExpViewHolder extends RecyclerView.ViewHolder{

        TextView company_name, placed_year, role, type, ctc;


        public ExpViewHolder(@NonNull View itemView) {
            super(itemView);

            company_name = itemView.findViewById(R.id.company_name);
            placed_year = itemView.findViewById(R.id.placed_year);;
            role = itemView.findViewById(R.id.role);;
            type = itemView.findViewById(R.id.type);;
            ctc = itemView.findViewById(R.id.ctc);;
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
