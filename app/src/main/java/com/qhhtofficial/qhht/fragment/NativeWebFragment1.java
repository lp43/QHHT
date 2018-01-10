package com.qhhtofficial.qhht.fragment;

import android.graphics.Bitmap;
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
import com.qhhtofficial.qhht.Constants;
import com.qhhtofficial.qhht.R;
import com.qhhtofficial.qhht.module.MessageEvent;
import com.qhhtofficial.qhht.module.ObservableWebView;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class NativeWebFragment1 extends BaseFragment implements ObservableWebView.OnScrollChangedCallback{
    private static final String TAG = "NativeWebFragment1";

    private RelativeLayout mRelative;
    public ObservableWebView mWebView;


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

        String url = "https://www.qhhtofficial.com/forums";
        mWebView.loadUrl(url);
    }

    @Override
    public void onScroll(int curX, int curY, int disX, int disY) {
        EventBus.getDefault().post(new MessageEvent(Constants.WEB_SCROLL, curX, curY, disX, disY));
    }

    public int[] getScrollbarPosition(){
        return new int[]{mWebView.curX, mWebView.curY};
    }

    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            EventBus.getDefault().post(new MessageEvent(Constants.WEB_PAGE_START, url));

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadJavaScript(
                            "evt = document.createEvent(\"HTMLEvents\");\n" +
                                    "evt.initEvent(\"change\", false, true);\n" +
                                    "$('.goog-te-combo').val('en')[0].dispatchEvent(evt);"
                    );
                }
            }, 5_000);


            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadJavaScript(
                            "evt = document.createEvent(\"HTMLEvents\");\n" +
                                    "evt.initEvent(\"change\", false, true);\n" +
                                    "$('.goog-te-combo').val('en')[0].dispatchEvent(evt);"
                    );
                }
            }, 8_000);

        }

        private void loadJavaScript(String script){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mWebView.evaluateJavascript(script, null);
            } else {
                mWebView.loadUrl("javascript:"+script);
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onBackPressed();
    }
}
