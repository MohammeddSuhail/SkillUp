package com.codingchallengers.skillup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codingchallengers.skillup.R;

public class DSAGuideFragment extends Fragment {

    public static FragmentTransaction f;
    CardView time_complex_cardview, math_cardview, binsearch_cardview, slidwindow_cardview, recursion_cardview, array_cardview, desc_cardview;
    VideoListFragment videoListFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dsa_guide_fragment, container, false);

        return view;
    }
}
