package com.framgia.lam.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int SLEEP = 3500;
    private final int MAX =50;
    private final int MIN =1;
    private ProgressBar mPrbLoad;
    private ListView mLvData;
    private ArrayList<String> mListData = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrbLoad = findViewById(R.id.pb_loading);
        mLvData = findViewById(R.id.lv_data);
        mLoad.start();
       mAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, mListData);
        mLvData.setAdapter(mAdapter);
    }
    private Thread mLoad = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(SLEEP);
                for (int i = MIN; i < MAX; i++) {
                    mListData.add(getString(R.string.title_mess) + " "+i);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPrbLoad.setVisibility(View.GONE);
                        mAdapter.notifyDataSetChanged();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
