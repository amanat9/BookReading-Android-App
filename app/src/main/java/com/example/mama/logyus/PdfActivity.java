package com.example.mama.logyus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("prideprejudice.pdf").load();
    }

    
}
