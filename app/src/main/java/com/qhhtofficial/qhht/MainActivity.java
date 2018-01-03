package com.qhhtofficial.qhht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onInductionClick(View view){
        Intent intent = new Intent();
        intent.setClass(this, InductionActivity.class);
        startActivity(intent);
    }

    public void onSubconsciousClick(View view){
        Toast.makeText(this, "onSubconsciousClick", Toast.LENGTH_SHORT).show();
    }

}
