package com.qhhtofficial.qhht.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.qhhtofficial.qhht.Constants;
import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.module.MessageEvent;
import com.qhhtofficial.qhht.module.ObservableWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class NativeWebFragment2 extends BaseFragment{
    private static final String TAG = "NativeWebFragment1";

    private RelativeLayout mRelative;
    ObservableWebView mWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        mRelative = view.findViewById(R.id.rl);

        mWebView = new ObservableWebView(getActivity());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebviewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRelative.addView(mWebView, layoutParams);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i(TAG, "onPageFinished: "+url);

            loadJavaScript(
                    "evt = document.createEvent(\"HTMLEvents\");\n" +
                    "evt.initEvent(\"change\", false, true);\n" +
                    "$('.goog-te-combo').val('zh-TW')[0].dispatchEvent(evt);"
            );
        }


        private void loadJavaScript(String script){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mWebView.evaluateJavascript(script, null);
            } else {
                mWebView.loadUrl("javascript:"+script);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        int action = (int) event.getMessage(0);
        switch (action){
            case Constants.WEB_SCROLL:{
                Log.i(TAG, "onMessageEvent: "+(int)event.getMessage(4));
                mWebView.scrollBy((int)event.getMessage(3), (int)event.getMessage(4));
                break;
            }
            case Constants.WEB_SCRROLLBAR_SYNC:{
                mWebView.scrollTo((int)event.getMessage(1), (int)event.getMessage(2));
                break;
            }
            case Constants.WEB_PAGE_START:{
                String url = (String) event.getMessage(1);
                mWebView.loadUrl(url);
                break;
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }
    }
}
