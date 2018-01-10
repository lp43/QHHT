package com.qhhtofficial.qhht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.qhhtofficial.qhht.fragment.NativeWebFragment1;
import com.qhhtofficial.qhht.fragment.NativeWebFragment2;
import com.qhhtofficial.qhht.module.MessageEvent;
import com.qhhtofficial.qhht.module.ScriptManager;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class FourmActivity extends AppCompatActivity {
    NativeWebFragment1 fragment_1;
    NativeWebFragment2 fragment_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.qhht_forum));

        fragment_1 = (NativeWebFragment1) getSupportFragmentManager().findFragmentById(R.id.webview_1);
        fragment_2 = (NativeWebFragment2) getSupportFragmentManager().findFragmentById(R.id.webview_2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fourm, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:{
                // app icon in action bar clicked; goto parent activity.
                onBackPressed();
                return true;
            }
            case R.id.action_sync:{
                EventBus.getDefault().post(new MessageEvent(Constants.WEB_SCRROLLBAR_SYNC, fragment_1.getScrollbarPosition()[0], fragment_1.getScrollbarPosition()[1]));
                break;
            }

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(fragment_1!=null){
            if(!fragment_1.onBackPressed()){
                super.onBackPressed();
            }
        }
    }
}
