package com.fs.mydemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView swipeTarget;
    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        List<String> list=new ArrayList<String>();
        for (int i=0;i<20;i++){
            list.add("aaaa");
        }

        swipeTarget = (ListView) findViewById(R.id.swipe_target);
        swipeTarget.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list));

        swipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);

        //控制是否使用刷新和加载
//        swipeToLoadLayout.setRefreshEnabled(false);
//        swipeToLoadLayout.setLoadMoreEnabled(false);

        //设置刷新和加载动作
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        swipeToLoadLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setLoadingMore(false);
                            }
                        });
                    }
                }).start();
            }
        });
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        swipeToLoadLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
