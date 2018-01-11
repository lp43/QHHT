package com.qhhtofficial.qhht;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.qhhtofficial.qhht.fragment.NativeWebMainFragment;
import com.qhhtofficial.qhht.fragment.NativeWebFollowFragment;
import com.qhhtofficial.qhht.module.MessageEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by ehs_app_1 on 2018/1/9.
 */

public class FourmActivity extends AppCompatActivity {
    NativeWebMainFragment fragment_main;
    NativeWebFollowFragment fragment_follow;
    Mode mode = Mode.ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.qhht_forum));

        fragment_main = (NativeWebMainFragment) getSupportFragmentManager().findFragmentById(R.id.webview_main);
        fragment_follow = (NativeWebFollowFragment) getSupportFragmentManager().findFragmentById(R.id.webview_follow);

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
            case R.id.action_expand:{

                if(mode == Mode.ALL){
                    mode = Mode.MAIN;
                }else if(mode == Mode.MAIN){
                    mode = Mode.FOLLOW;
                }else if(mode == Mode.FOLLOW){
                    mode = Mode.ALL;
                }

                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if(mode==Mode.MAIN){
                    Toast.makeText(this, "英文模式", Toast.LENGTH_SHORT).show();
                    ft.hide(fragment_follow);
                    ft.show(fragment_main);
                }else if(mode == Mode.FOLLOW){
                    Toast.makeText(this, "中文模式", Toast.LENGTH_SHORT).show();
                    ft.hide(fragment_main);
                    ft.show(fragment_follow);
                }else if(mode == Mode.ALL){
                    Toast.makeText(this, "雙版模式", Toast.LENGTH_SHORT).show();
                    ft.show(fragment_main);
                    ft.show(fragment_follow);
                }
                ft.commit();

                break;
            }
            case R.id.action_sync:{
                EventBus.getDefault().post(new MessageEvent(Constants.WEB_SCRROLLBAR_SYNC, fragment_main.getScrollbarPosition()[0], fragment_main.getScrollbarPosition()[1]));

                break;
            }


        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(fragment_main !=null){
            if(!fragment_main.onBackPressed()){
                super.onBackPressed();
            }
        }
    }

    enum Mode{
        MAIN, FOLLOW, ALL
    }
}
