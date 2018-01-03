package com.qhhtofficial.qhht;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.qhhtofficial.qhht.model.induction.Induction;
import com.qhhtofficial.qhht.util.FileUtils;

import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayout linearLayout;
    private ArrayList<String> txtPaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout)findViewById(R.id.ll);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: "+requestCode);
        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE_DOC:
                if(resultCode== Activity.RESULT_OK && data!=null)
                {
                    txtPaths = new ArrayList<>();
                    ArrayList<String> paths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    txtPaths.addAll(paths);
                    for (int i = 0; i < txtPaths.size(); i++) {

                        String path = txtPaths.get(i);
                        Log.i(TAG, "onActivityResult: path: "+ path);
                        try {
                            final String json = FileUtils.getStringFromFile(path);
                            Log.i(TAG, "onActivityResult: json: "+ json);
                            final Induction induction = new Gson().fromJson(json, Induction.class);
                            String header = induction.getHeader();
                            if(!TextUtils.isEmpty(header)){
                                Log.i(TAG, "onActivityResult: header: "+ header);
                                Button button = new Button(this);
                                button.setText(header);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent();
                                        Bundle bundle = new Bundle();
                                        bundle.putString(Consts.CONTENT, json);
                                        intent.setClass(MainActivity.this, ContentActivity.class);
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
                break;
        }

    }

    public void onChooseFolderClick(View view){
        String[] txt = {".txt"};
        FilePickerBuilder.getInstance()
                    .setMaxCount(10)
                    .setSelectedFiles(txtPaths)
                    .addFileSupport("TXT", txt)
                    .enableSelectAll(false)
                    .enableDocSupport(false)
                    .setActivityTheme(R.style.FilePickerTheme)
                    .pickFile(this);
    }

}
