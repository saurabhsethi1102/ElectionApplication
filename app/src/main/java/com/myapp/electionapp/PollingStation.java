package com.myapp.electionapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.http.SslError;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PollingStation extends AppCompatActivity {
    WebView wv1;
    TextView textView;
    Handler mHandler = new Handler();
    public static String psname,p_name,f_name,ac_name,gender,epic_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling_station);
        final WebView webview = (WebView) findViewById(R.id.webview);
        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.webViewSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.loadUrl("https://electoralsearch.in");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        final ProgressDialog progress=new ProgressDialog(PollingStation.this);
        progress.setMessage("Loading Page...");
        progress.show();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        swipeRefreshLayout.setRefreshing(false);
        webview.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");
        webview.loadUrl("https://electoralsearch.in");
        webview.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         if (progress.isShowing()){
                                             progress.dismiss();
                                         }
                                         webview.loadUrl("javascript:window.HtmlViewer.showHTML" +"('&lt;html&gt;'+document.getElementsByTagName('form')[2].innerHTML+'&lt;/html&gt;');");
                                     }
                                 }
        );
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String webUrl = webview.getUrl();
                if (webUrl == "https://electoralsearch.in/##resultArea") {

                }
                //Toast.makeText(getApplicationContext(), webUrl, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed(); // Ignore SSL certificate errors
    }

    class MyJavaScriptInterface {
        private Context ctx;

        MyJavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public void showHTML(final String html) {
            final String html_ = html;
            mHandler.post(new Runnable() {
                              @Override
                              public void run() {
                                  //Toast.makeText(ctx, html_, Toast.LENGTH_LONG).show();
                                  String[] b = html_.split("<input type=\"hidden\" id=\"acno\" name=\"ac_no\" value=\"");
                                  String[] ac_no = b[1].split("\">");
                                  String[] a = html_.split("<input type=\"hidden\" name=\"ps_name\" value=\"");
                                  String[] ps_name = a[1].split("\">");
                                  psname = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"name\" value=\"");
                                  ps_name = a[1].split("\">");
                                  p_name = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"gender\" value=\"");
                                  ps_name = a[1].split("\">");
                                  gender = ps_name[0];
                                  a = html_.split("<input type=\"hidden\" name=\"ac_name\" value=\"");
                                  ps_name = a[1].split("\">");
                                  ac_name = ps_name[0];
                                  //Toast.makeText(ctx, ac_no[0], Toast.LENGTH_LONG).show();
                                  //Toast.makeText(ctx, ps_name[0], Toast.LENGTH_SHORT).show();
                                  if (ac_no[0].equals("4")||ac_no[0].equals("14")||ac_no[0].equals("15")||ac_no[0].equals("16")||ac_no[0].equals("17")||ac_no[0].equals("18")||ac_no[0].equals("19")||ac_no[0].equals("20")||ac_no[0].equals("21")||ac_no[0].equals("22")){
                                      if (ps_name[0] != null) {

                                          AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(PollingStation.this);
                                          alertDialogBuilder.setTitle("Polling Station Details");
                                          alertDialogBuilder.setMessage(ps_name[0]);
                                          alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  startActivity(new Intent(PollingStation.this, BoothActivity2.class));
                                              }
                                          });
                                          AlertDialog alertDialog=alertDialogBuilder.create();
                                          alertDialog.show();
                                      }
                                  }
                                  else{
                                      startActivity(new Intent(PollingStation.this, Main2Activity.class));
                                      finish();

                                  }

                              }
                          }
            );
        }

    }
}
