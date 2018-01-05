package com.qhhtofficial.qhht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qhhtofficial.qhht.adapter.DataAdapter;
import com.qhhtofficial.qhht.model.QhhtObj;
import com.qhhtofficial.qhht.api.Content;
import com.qhhtofficial.qhht.api.Section;
import com.qhhtofficial.qhht.util.AudioPlayer;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.io.File;

import static com.qhhtofficial.qhht.api.DataFactory.makeSections;

/**
 * 導引詞
 * Created by Simon on 2018/1/3.
 */

public class ContentActivity extends AppCompatActivity {
    private static final String TAG = ContentActivity.class.getSimpleName();

    public DataAdapter adapter;
    private QhhtObj mData;
    private String mediaFolderPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent()!=null){
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null){
                String json = bundle.getString(Constants.DATA);
                mediaFolderPath = bundle.getString(Constants.MEDIA_FOLDER_PATH);
//                Log.i(TAG, "onCreate: json: "+json);
                mData = new Gson().fromJson(json, QhhtObj.class);
                if(mData!=null){
                    getSupportActionBar().setTitle(mData.getHeader());
                }

            }

        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new DataAdapter(this, makeSections(mData));
        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onClick(int flatPosition, ExpandableGroup group, int childIndex) {
                final Content content = ((Section) group).getItems().get(childIndex);
                if(content.isHasSound()){
//                    Toast.makeText(ContentActivity.this, "HasSound", Toast.LENGTH_SHORT).show();
                    String mediaPath = mediaFolderPath+ File.separator+content.getTitle()+".mp3";
                    Log.i(TAG, "onClick: mediaPath: "+mediaPath);
                    AudioPlayer.getInstance().forcePlay(mediaPath);
                }else{
                    Toast.makeText(ContentActivity.this, "Has No Sound", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioPlayer.getInstance().release();
    }
}
