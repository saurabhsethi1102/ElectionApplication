package com.myapp.electionapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PollingStation extends AppCompatActivity {
    private WebView webView;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_polling_station);

        if (Main2Activity.lang==1){
            updateViews("hi");
        }
        else {
            updateViews("en");
        }

        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.webViewSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadURL();
            }
        });
        loadURL();

        progress= new ProgressDialog(PollingStation.this);
        progress.setMessage("Loading Page...");
        progress.show();
        fab = (FloatingActionButton)findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PollingStation.this,BoothActivity2.class));
            }
        });
    }

    public void updateViews(String languageCode){
        Context context=LocaleHelper.setLocale(this, languageCode);
        Resources resources=context.getResources();

        getSupportActionBar().setTitle(resources.getString(R.string.know_your_polling_station));
    }
    private void loadURL() {
        webView = (WebView)findViewById(R.id.webview);
        swipeRefreshLayout.setRefreshing(true);
        //spinner.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient(){
            private String address = "https://electoralsearch.in/##resultArea";
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                //                super.onLoadResource(view, url);
//                if(url.equals("https://electoralsearch.in/##resultArea")){
//                    Toast.makeText(MainActivity.this, "Result Page Opened", Toast.LENGTH_SHORT).show();
//                }
//            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                if(url.equals(address)){
                    Toast.makeText(PollingStation.this, "Result Obtained", Toast.LENGTH_SHORT).show();
                    return  true;
                }
                else{
                    Toast.makeText(PollingStation.this, "Result Not Obtained", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//                if(url.equals(address)){
//                    Toast.makeText(MainActivity.this, "Result Obtained", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Result Not Obtained", Toast.LENGTH_SHORT).show();
//                }
//                return super.shouldInterceptRequest(view, url);
//
//            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progress.isShowing()){
                    progress.dismiss();
                    //AlertDailog
                    AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(PollingStation.this);
                    alertDialogBuilder.setTitle("Instructions");
                    alertDialogBuilder.setMessage("Enter EPIC Number or your details to get the name of your polling station. \nClick on the Button at the bottom right corner to know more.");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog=alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webView.loadUrl("https://electoralsearch.in/");
        swipeRefreshLayout.setRefreshing(false);
        //spinner.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
}
