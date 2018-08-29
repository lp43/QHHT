package com.qhhtofficial.qhht;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.qhhtofficial.qhht.model.QhhtObj;
import com.qhhtofficial.qhht.module.ScriptManager;
import com.qhhtofficial.qhht.module.TimeConsumeManager;
import com.qhhtofficial.qhht.util.Clock;
import com.qhhtofficial.qhht.util.FileUtils;
import com.qhhtofficial.qhht.util.Utility;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayout linearLayout;
    FilePickerDialog dialog;
    private Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utility.setAppContext(this);

        findViewById(R.id.btn_stop_watch).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                addText("time total: "+ TimeConsumeManager.getInstance().getTimesTotalInHHMM(), 20, Color.BLACK);

                TimeConsumeManager.getInstance().reset();
                ((Button)view).setText(R.string.qhht_stop_watch);
                return false;
            }
        });
        linearLayout = (LinearLayout)findViewById(R.id.ll);

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_delete:{
                ScriptManager.getInstance().init(MainActivity.this).clearPath().save();
                refresh();
                break;
            }
            case R.id.action_add:{
                Dexter.withActivity(this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                DialogProperties properties = new DialogProperties();
                                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                                properties.selection_type = DialogConfigs.FILE_SELECT;
                                String script_folder = Utility.getStringValueForKey(MainActivity.this, Constants.SCRIPT_FOLDER_PATH);
                                if(TextUtils.isEmpty(script_folder)){
                                    properties.root = new File(DialogConfigs.DEFAULT_DIR);
                                }else{
                                    properties.root = new File(script_folder);
                                }
                                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                                properties.extensions = new String[]{"txt"};
                                dialog = new FilePickerDialog(MainActivity.this,properties);
                                dialog.setTitle(getString(R.string.choose_script));
                                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                                    @Override
                                    public void onSelectedFilePaths(String[] files) {
//                        Log.i(TAG, "onSelectedFilePaths: ");

                                        for (int i = 0; i < files.length; i++) {
//                            Log.i(TAG, "files: "+files[i]);
                                            ScriptManager.getInstance().init(MainActivity.this).addPath(files[i]);
                                        }
                                        ScriptManager.getInstance().init(MainActivity.this).save();

                                        refresh();
                                    }

                                });
                                dialog.show();
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                Toast.makeText(MainActivity.this, "您取消授權", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                new AlertDialog.Builder(MainActivity.this).setTitle("請求權限")
                                        .setMessage("要讀腳本用的")
                                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                            @Override public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .show();
                            }
                        })
                        .withErrorListener(new PermissionRequestErrorListener() {
                            @Override
                            public void onError(DexterError error) {

                            }
                        })
                        .check();


                break;
            }
            case R.id.action_setting:{
                DialogProperties properties = new DialogProperties();
                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.DIR_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = null;
                dialog = new FilePickerDialog(MainActivity.this,properties);
                dialog.setTitle(getString(R.string.choose_setting));
                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        Log.i(TAG, "onSelectedFilePaths: ");

                        if(files!=null && files.length>0){
                            File file = new File(files[0]);
                            Utility.saveStringValueForKey(MainActivity.this, Constants.SCRIPT_FOLDER_PATH, file.getPath());
                        }

                    }

                });
                dialog.show();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    //Add this method to show Dialog when the required permission has been granted to the app.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(dialog!=null) {
                        //Show dialog if the read permission has been granted.
                        dialog.show();
                    }
                }
                else {
                    //Permission has not been granted. Notify the user.
                    Toast.makeText(MainActivity.this,"Permission is Required for getting list of files",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void refresh() {
        if(linearLayout!=null) linearLayout.removeAllViews();

        ArrayList<String> scripts = ScriptManager.getInstance().init(MainActivity.this).loadAllScripts();
        for (int i = 0; i < scripts.size(); i++) {

            final String path = scripts.get(i);
            Log.i(TAG, "refresh: path: "+ path);
            try {
                final String json = FileUtils.getStringFromFile(path);
//                            Log.i(TAG, "refresh: json: "+ json);
                final QhhtObj qhhtObj = new Gson().fromJson(json, QhhtObj.class);
                String header = qhhtObj.getHeader();
                if(!TextUtils.isEmpty(header)){
//                                Log.i(TAG, "refresh: header: "+ header);
                    Button button = new Button(MainActivity.this);
                    button.setText(header);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.DATA, json);
                            File file = new File(path);
                            String parent = file.getParent();
                            Log.i(TAG, "onClick: parent: "+parent);
                            bundle.putString(Constants.MEDIA_FOLDER_PATH, parent);
                            intent.setClass(MainActivity.this, ScriptActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    if(linearLayout!=null) linearLayout.addView(button);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void addText(String text, int size, int color){
        {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(text);
            if(color!=-1)textView.setTextColor(color);
            if(size!=-1)textView.setTextSize(size);
            linearLayout.addView(textView);
        }
    }

    public void onForumClick(View view){
        Intent intent = new Intent();
        intent.setClass(this, FourmActivity.class);
        startActivity(intent);
    }

    public void onStopWatchClick(final View view){

        if(clock==null){
            clock = new Clock(this, Clock.TICK_METHOD.minute);
            clock.addOnClockTickListner(new Clock.OnClockTickListner() {
                @Override
                public void onClockTick(Time currentTime) {
                    TimeConsumeManager.getInstance().addMinute(1);
                    if(view instanceof Button){
                        String timeTotal  = TimeConsumeManager.getInstance().getTimesTotalInHHMM();
                        ((Button)view).setText(timeTotal);
                    }
                }
            });
        }

        TimeConsumeManager.getInstance().addTimeMark();
        // print time diff
        {
            if(TimeConsumeManager.getInstance().getTimeMarks()!=null && TimeConsumeManager.getInstance().getTimeMarks().size()>1){
                int timeNow = TimeConsumeManager.getInstance().getTimeMarks().get(TimeConsumeManager.getInstance().getTimeMarks().size()-1);
                int timeBefore = TimeConsumeManager.getInstance().getTimeMarks().get(TimeConsumeManager.getInstance().getTimeMarks().size()-2);
                int timeDiff = timeNow - timeBefore;
                String strTimeDiff = TimeConsumeManager.getInstance().convertToHHMM(timeDiff);

                addText("+"+strTimeDiff, -1, Color.BLUE);
            }
        }
        // print time mark
        {
            if(TimeConsumeManager.getInstance().getTimeMarks()!=null && TimeConsumeManager.getInstance().getTimeMarks().size()>0){
                int timeNow = TimeConsumeManager.getInstance().getTimeMarks().get(TimeConsumeManager.getInstance().getTimeMarks().size()-1);
                String mark = TimeConsumeManager.getInstance().convertToHHMM(timeNow);

                addText("time mark: "+mark, -1, -1);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if(clock!=null){
            clock.stopTick();
            clock = null;
        }

        System.exit(0);
    }
}
