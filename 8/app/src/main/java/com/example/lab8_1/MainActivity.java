package com.example.lab8_1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button bNext;
    Button bBack;
    EditText ed1;
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNext = (Button) findViewById(R.id.nButton);
        bBack = (Button) findViewById(R.id.bButton);
        ed1 = (EditText) findViewById(R.id.editText);
        bNext.setEnabled(false);
        bBack.setEnabled(false);
        wv1 = (WebView) findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
    }

    public void getBackForwardList(View view){
        WebBackForwardList currentList = wv1.copyBackForwardList();
        int currentSize = currentList.getSize();
        StringBuilder History = new StringBuilder();
        History.append("История:");
        for(int i = 0; i < currentSize; ++i)
        {
            WebHistoryItem item = currentList.getItemAtIndex(i);
            if (item.getUrl() != null) History.append("\n").append(item.getUrl());
        }
        Toast.makeText(this, History.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onClickEnter(View view) {
        String url = ed1.getText().toString();
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        url = "http://www." + url;
        wv1.loadUrl(url);
    }

    public void onClickNext(View view) {
        if (wv1.canGoForward()) wv1.goForward ();
        else MessageBox("Нельзя перейти вперёд");
    }

    public void onClickBack(View view) {
        if (wv1.canGoBack()) wv1.goBack();
        else MessageBox("Нельзя перейти назад");
    }

    public void onClickClear(View view) {
        wv1.clearHistory();
        bNext.setEnabled(false);
        bBack.setEnabled(false);
    }
    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        public void onLoadResource (WebView view, String url){
            if (wv1.canGoBack()) {
                bBack.setEnabled(true);
            } else{
                bBack.setEnabled(false);
            }
            if (wv1.canGoForward()) {
                bNext.setEnabled(true);
            } else{
                bNext.setEnabled(false);
            }
        }
    }
}