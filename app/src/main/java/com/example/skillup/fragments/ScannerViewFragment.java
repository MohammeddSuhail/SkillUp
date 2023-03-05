package com.example.skillup.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.skillup.R;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerViewFragment extends Fragment implements ZXingScannerView.ResultHandler{

    QrScannerFragment qrScannerFragment;

    ZXingScannerView scannerView;

    public ScannerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scanner_view, container, false);

        qrScannerFragment = new QrScannerFragment();

        scannerView = new ZXingScannerView(getContext());
//        setContentView(R.layout.activity_scanner_view);
        getActivity().setContentView(scannerView);                      //above line change to this since while loading we want this to load


        //for runtime permission
        Dexter.withContext(getContext())
                .withPermission(android.Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }


                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


        //back pressed
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        scannerView.stopCamera();
                        getActivity().setContentView(R.layout.activity_all);
                        QrScannerFragment qrScannerFragment = new QrScannerFragment();
                        Bundle args = new Bundle();
                        args.putString("et_qr_result","QR Code Result");
                        qrScannerFragment.setArguments(args);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, qrScannerFragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        getActivity().setContentView(R.layout.activity_all);
        //what should we do with result of scan
        Bundle args = new Bundle();

       // Toast.makeText(getContext(),result.getText(),Toast.LENGTH_SHORT).show();

        args.putString("et_qr_result",result.getText());
        qrScannerFragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, qrScannerFragment).commit();
    }

}