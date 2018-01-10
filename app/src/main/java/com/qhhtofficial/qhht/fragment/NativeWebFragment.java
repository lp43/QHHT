package com.qhhtofficial.qhht.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.module.ObservableWebView;

/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class NativeWebFragment extends Fragment implements ObservableWebView.OnScrollChangedCallback {
    private static final String TAG = "NativeWebFragment";

    private RelativeLayout mRelative;
    ObservableWebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        mRelative = view.findViewById(R.id.rl);

        mWebView = new ObservableWebView(getActivity());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebviewClient());
        mWebView.setOnScrollChangedCallback(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRelative.addView(mWebView, layoutParams);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWebView.loadUrl("https://www.qhhtofficial.com/forums");
//        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
//        mWebView.loadData(customHtml, "text/html", "UTF-8");
    }

    @Override
    public void onScroll(int l, int t) {
        String testString = Integer.toString(t);
        Log.d("Testing", testString);
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "shouldOverrideUrlLoading: ");
            return true;
        }


    }
}
