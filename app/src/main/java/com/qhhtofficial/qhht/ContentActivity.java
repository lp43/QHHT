package com.qhhtofficial.qhht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.qhhtofficial.qhht.adapter.DataAdapter;
import com.qhhtofficial.qhht.model.QhhtObj;

import static com.qhhtofficial.qhht.module.GenreDataFactory.makeGroups;

/**
 * 導引詞
 * Created by ehs_app_1 on 2018/1/3.
 */

public class ContentActivity extends AppCompatActivity {
    private static final String TAG = ContentActivity.class.getSimpleName();

    public DataAdapter adapter;
    private QhhtObj mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent()!=null){
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null){
                String json = bundle.getString(Constants.DATA);
                Log.i(TAG, "onCreate: json: "+json);
                mData = new Gson().fromJson(json, QhhtObj.class);
            }

        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new DataAdapter(this, makeGroups());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
