package com.qhhtofficial.qhht.module;

import android.content.Context;
import android.util.Log;

import com.qhhtofficial.qhht.Constants;
import com.qhhtofficial.qhht.MainActivity;
import com.qhhtofficial.qhht.util.Utility;

import java.util.ArrayList;

/**
 * 腳本管理器
 * Created by ehs_app_1 on 2018/1/5.
 */

public class ScriptManager {
    private static final String TAG = ScriptManager.class.getSimpleName();

    private ArrayList<String> paths = new ArrayList<>();
    private Context mContext;
    private static ScriptManager mScriptMgr;


    public static ScriptManager getInstance(){
        if(mScriptMgr==null){
            mScriptMgr = new ScriptManager();
        }
        return mScriptMgr;
    }

    public ScriptManager init(Context cnx){
        mContext = cnx;
        return mScriptMgr;
    }

    public ScriptManager addPath(String path){
        paths.add(path);
        return mScriptMgr;
    }

    public ScriptManager clearPath(){
        paths.clear();
        return mScriptMgr;
    }

    public ArrayList<String> loadAllScripts() {
        paths.clear();

        String history = Utility.getStringValueForKey(mContext, Constants.SCRIPT_FOLDER_PATH_HISTORY);
        Log.i(TAG, "loadAllScripts: "+history);

        String[] split = history.split(",");
        for (int i = 0; i < split.length; i++) {
            paths.add(split[i]);
        }
        return paths;
    }

    public void save(){
        Utility.saveStringValueForKey(mContext, Constants.SCRIPT_FOLDER_PATH_HISTORY, "");

        if(paths!=null && paths.size()>0){
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size(); i++) {
                String path = paths.get(i);
                sb.append(path);
                sb.append(",");
            }
            Utility.saveStringValueForKey(mContext, Constants.SCRIPT_FOLDER_PATH_HISTORY, sb.toString());
        }
    }
}
