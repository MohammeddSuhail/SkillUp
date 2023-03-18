package com.codingchallengers.skillup.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingchallengers.skillup.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EachProfileUpdateFragment extends Fragment {

    String tv_name,et_name,db_child_name;

    TextView update_tv;
    EditText update_et;
    CardView save;
    ImageView back;

    DatabaseReference mUserRef;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_each_profile_update, container, false);

        update_tv = view.findViewById(R.id.update_textView);
        update_et = view.findViewById(R.id.update_editText);
        save = view.findViewById(R.id.update_save);
        back = view.findViewById(R.id.back_profile_update);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        tv_name = getArguments().getString("tv_name");
        et_name = getArguments().getString("et_name");
        db_child_name = getArguments().getString("db_child_name");

        update_tv.setText(update_tv.getText()+tv_name);
        update_et.setText(et_name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserRef.child(mUser.getUid()).child(db_child_name).setValue(update_et.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            }
        });


        //back pressed
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }
}