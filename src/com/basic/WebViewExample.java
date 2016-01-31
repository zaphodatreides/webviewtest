package com.basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebViewExample extends Activity{ 

    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_example);

        webView = (WebView) findViewById(R.id.webview);

        Button button = (Button) findViewById (R.id.run);
        button.setOnClickListener(new OnClickListener() {           
            @Override
            public void onClick(View v) {
                gotoPage();             
            }
        });

    }

    private void gotoPage(){

        EditText text = (EditText) findViewById(R.id.url);
        String url = text.getText().toString();

        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        webView.loadUrl(url);

    }

    private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }

}