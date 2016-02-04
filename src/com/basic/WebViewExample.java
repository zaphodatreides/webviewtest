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
import android.widget.Toast;
import android.util.Log;
import java.io.RandomAccessFile; 


public class WebViewExample extends Activity{ 

    WebView webView;
    WebView webView2;
    EditText text;
    int scrollx,scrolly;
    String mub = "file:///sdcard/beautifulsoup/";
    String mue = "/test3.html";
    String mainurl;
    String secondaryurl = null;
    
    @Override
    
    public void onBackPressed(){
    	Log.e ("basic","back pressed");
    	
    if (secondaryurl != null){
    	Log.e ("basic",webView2.getUrl());
    	webView2.setVisibility(View.INVISIBLE );
    	webView.setVisibility(View.VISIBLE);
    	secondaryurl = null;
    	Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

   	}
    else {
    	super.onBackPressed();
    	Log.e ("basic","string is null");
    }
    }
    

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_example);

        webView = (WebView) findViewById(R.id.webview);
        webView2 = (WebView) findViewById(R.id.webview2);

        Button button = (Button) findViewById (R.id.run);
        button.setOnClickListener(new OnClickListener() {           
            @Override
            public void onClick(View v) {
                gotoPage();             
            }
        });
        Button save = (Button) findViewById (R.id.save);
        save.setOnClickListener(new OnClickListener() {           
            @Override
            public void onClick(View v) {
                saveLink();   
            }
        });
        
        Button back = (Button) findViewById (R.id.back);
        back.setOnClickListener(new OnClickListener() {           
            @Override
            public void onClick(View v) {
                secondaryurl=null;
                webView2.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE );
            }
        });
        
        text = (EditText) findViewById(R.id.url);
        text.setMaxLines(1);
    }

 /*   private void restoreview(){
    	
    	webView.loadUrl(mainurl);
   	}*/
    private void gotoPage(){

        mainurl = mub+text.getText().toString()+mue;

        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
        Log.e ("basic",mainurl);
        webView.loadUrl(mainurl);

    }
    
    private void saveLink(){

    	try {
    	  RandomAccessFile outputStream = new  RandomAccessFile("/sdcard/links.txt", "rw");
    	  outputStream.seek(outputStream.length());
    	  outputStream.write(secondaryurl.getBytes());
    	  outputStream.write("\n".getBytes());
    	  outputStream.close();
    	} catch (Exception e) {
    	  e.printStackTrace();
    	}
    	
    	//text.setText(webView.getUrl());
    	webView2.setVisibility(View.INVISIBLE);
    	webView.setVisibility(View.VISIBLE);
    	secondaryurl = null;
    	
  	   	
    }
    
/*    private void discardLink(){
    	webView2.setVisibility(View.GONE);
    	webView.setVisibility(View.VISIBLE);
    	
    }*/
 
    private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	//scrollx=view.getScrollX();
        	//scrolly=view.getScrollY();
        	//Log.e("basic",String.format("getscrollx&y called returned values %d %d ",scrollx,scrolly));
        	secondaryurl=url;
        	webView2.loadUrl(secondaryurl);
        	webView.setVisibility(View.INVISIBLE);
        	webView2.setVisibility(view.VISIBLE);
            return (false);
        }
    /*    public void onPageFinished(WebView view, String url) {
        	Log.e ("basic","OnPageFinished called");
           if (url.equals(mainurl)) {
        	   //while(webView.getProgress()!=100){
        	   //}
    	       //webView.scrollTo(scrollx,scrolly);
        	   //Log.e("basic","scrolled to x,y");
        	   
           }
        }*/

    }

}