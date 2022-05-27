package com.harsh.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    TextView tvTitle,tvSource,tvDate;
    ImageView back;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitle = findViewById(R.id.tvtitle);
        tvSource = findViewById(R.id.tvSource);
        tvDate = findViewById(R.id.tvDate);
        back = findViewById(R.id.back);
        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");


        //Setting webpage
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        //Back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Details.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

    }
}