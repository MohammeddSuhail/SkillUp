package com.example.skillup.Note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;

import butterknife.OnClick;

public class CNnotesFragment extends Fragment {
    TextView network, topo, types;
    TextView networkd;
    LinearLayout topod, typesd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cn_notes_fragment, container, false);

       network = view.findViewById(R.id.network);
       networkd=view.findViewById(R.id.networkdetails);
       topo = view.findViewById(R.id.topo);
       topod = view.findViewById(R.id.topod);
       types = view.findViewById(R.id.types);
       typesd = view.findViewById(R.id.typesd);


       network.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(networkd.getVisibility() == View.VISIBLE)
               networkd.setVisibility(View.GONE);
               else
                   networkd.setVisibility(View.VISIBLE);

           }
       });

        topo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topod.getVisibility() == View.VISIBLE)
                    topod.setVisibility(View.GONE);
                else
                    topod.setVisibility(View.VISIBLE);

            }
        });

        types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(typesd.getVisibility() == View.VISIBLE)
                    typesd.setVisibility(View.GONE);
                else
                    typesd.setVisibility(View.VISIBLE);

            }
        });


        return view;
    }

    @OnClick(R.id.network)
    void submitButton(View view) {
        networkd.setVisibility(View.VISIBLE);
    }
}
