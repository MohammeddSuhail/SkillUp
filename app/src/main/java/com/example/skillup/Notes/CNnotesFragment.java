package com.example.skillup.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillup.R;

public class CNnotesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cn_notes_fragment, container, false);

        WebView browser = (WebView) view.findViewById(R.id.webvie);
        browser.loadUrl("https://drive.google.com/file/d/1EraWa_yVfFHJqOMG2nG91fbg2g1FHm30");


        return view;
    }
}
