package com.codingchallengers.skillup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

public class resumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("file:///android_asset/index.html"); //box-sizing: border-box;


        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrintManager printManager = (PrintManager) resumeActivity.this.getSystemService(Context.PRINT_SERVICE);
                String jobName = "Resume Document";
                // Get a print adapter instance
                PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
                // Create a print job with name and adapter instance
                PrintJob printJob = printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
            }
        });







        //edit html
        InputStream is = null;
        int size = 0;

        try {
            is = getAssets().open("index.html");
            size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            //is.close();

            String str = new String(buffer);
            str = str.replace("FirstName", "Mohammed");
            str = str.replace("LastName", "Suhail");

            webView.loadDataWithBaseURL("fake://not/needed", str,"text/html","utf-8","");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}