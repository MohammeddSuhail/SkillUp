package com.example.skillup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class QrScannerActivity extends AppCompatActivity {

    Button copybtn,openinbrowser;
    CardView scanbtn;
    public static EditText scantext;
    ImageView back_press;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        scanbtn = (CardView) findViewById(R.id.scanbtn);
        scantext = (EditText) findViewById(R.id.scantext);
        copybtn = (Button) findViewById(R.id.copybtn);
        openinbrowser = (Button)findViewById(R.id.openinbrowser);
        back_press = findViewById(R.id.back_press);

        getSupportActionBar().hide();

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(QrScannerActivity.this, AllActivity.class);
                startActivity(in);
            }
        });

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScannerViewActivity.class));
            }
        });

        copybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scannedtext = scantext.getText().toString().trim();
                if(scannedtext.equals("QR Code Result")){
                    Toast.makeText(getApplicationContext(),"Please scan the QR Code",Toast.LENGTH_SHORT).show();
                }
                else if(scannedtext.length()==0){
                    Toast.makeText(getApplicationContext(),"Nothing to copy!",Toast.LENGTH_SHORT).show();
                }
                else {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Data", scannedtext);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(),"Copied to clipboard",Toast.LENGTH_SHORT).show();
                }
            }
        });

        openinbrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scannedtext = scantext.getText().toString().trim();
                if(scannedtext.equals("QR Code Result")){
                    Toast.makeText(getApplicationContext(),"Please scan the QR Code",Toast.LENGTH_SHORT).show();
                }
                else if(scannedtext.length()==0){
                    Toast.makeText(getApplicationContext(),"Invalid action!",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY,scannedtext);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QrScannerActivity.this, AllActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}