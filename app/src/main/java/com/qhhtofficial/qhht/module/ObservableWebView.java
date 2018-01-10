package com.qhhtofficial.qhht.module;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by ehs_app_1 on 2018/1/10.
 */

public class ObservableWebView extends WebView {
    private static final String TAG = "ObservableWebView";

    private OnScrollChangedCallback mOnScrollChangedCallback;

    public int curX, curY;

    public ObservableWebView(Context context) {
        super(context);
    }

    public ObservableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        this.curX = l;
        this.curY = t;
        Log.i(TAG, "onScrollChanged: curX: "+curX+", curY: "+curY);
        if(mOnScrollChangedCallback != null) mOnScrollChangedCallback.onScroll(l, t, l-oldl, t-oldt);
    }

    public OnScrollChangedCallback getOnScrollChangedCallback()
    {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback)
    {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback
    {
        public void onScroll(int curX, int curY, int disX, int disY);
    }
}
