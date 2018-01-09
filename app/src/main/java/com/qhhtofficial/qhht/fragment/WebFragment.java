package com.qhhtofficial.qhht.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.qhhtofficial.qhht.R;

/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class WebFragment extends Fragment implements ChromeClientCallbackManager.ReceivedTitleCallback{
    private RelativeLayout mRelative;
    AgentWeb mAgentWeb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        mRelative = view.findViewById(R.id.rl);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mRelative, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setReceivedTitleCallback(this)
                .createAgentWeb()
                .ready()
                .go("https://www.qhhtofficial.com/forums");


    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
//        Toast.makeText(getActivity(), "title: "+title, Toast.LENGTH_SHORT).show();
    }
}
