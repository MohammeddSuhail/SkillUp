package com.example.skillup.fragments;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skillup.R;


public class QrScannerFragment extends Fragment {

    ScannerViewFragment scannerViewFragment;

    String et_qr_result;

    Button scanbtn, copybtn,openinbrowser;
    EditText scantext;

    public QrScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_scanner, container, false);

        scanbtn = (Button) view.findViewById(R.id.scanbtn);
        scantext = (EditText) view.findViewById(R.id.scantext);
        copybtn = (Button) view.findViewById(R.id.copybtn);
        openinbrowser = (Button) view.findViewById(R.id.openinbrowser);

        et_qr_result = getArguments().getString("et_qr_result");
        scantext.setText(et_qr_result);

        scannerViewFragment = new ScannerViewFragment();

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,scannerViewFragment).commit();
            }
        });

        copybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scannedtext = scantext.getText().toString().trim();
                if(scannedtext.equals("QR Code Result")){
                    Toast.makeText(getContext(),"Please scan the QR Code First!!!",Toast.LENGTH_SHORT).show();
                }else {
                    ClipboardManager clipboard = (ClipboardManager)  getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Data", scannedtext);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(),"Text Copied",Toast.LENGTH_SHORT).show();
                }
            }
        });

        openinbrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scannedtext = scantext.getText().toString().trim();
                if(scannedtext.equals("QR Code Result")){
                    Toast.makeText(getContext(),"Please scan the QR Code First!!!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY,scannedtext);
                    startActivity(intent);
                }
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
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }
}