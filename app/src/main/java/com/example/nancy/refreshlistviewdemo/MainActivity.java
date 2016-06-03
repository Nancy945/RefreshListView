package com.example.nancy.refreshlistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.refreshlistviewlibrary.RefreshListView;

public class MainActivity extends AppCompatActivity {


    private RefreshListView refreshListView;

    private ArrayAdapter<String> adapter;

    private final String[] strs = new String[50];//用来模拟数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshListView = ((RefreshListView) findViewById(R.id.refreshListView));

        //允许下拉刷新
        refreshListView.setPullToRefreshEnable(true);
        //允许滚动到底部加载更多
        refreshListView.setSwipeToLoadMoreEnable(true);


        //刷新数据和加载更多的回调用法
        refreshListView.setOnRefreshDataListener(new RefreshListView.onRefreshDataListener() {
            @Override
            public void refreshData() {
                new Thread() {
                    @Override
                    public void run() {
                        strs[0] = strs[0] + 1;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshListView.refreshStateFinish();
                                adapter.notifyDataSetChanged();
                            }
                        });

                    }
                }.start();

            }

            @Override
            public void loadingMore() {
                refreshListView.refreshStateFinish();
            }
        });

        //模拟数据
        for (int i = 0; i < strs.length; i++) {
            strs[i] = i + "";
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs);
        refreshListView.setAdapter(adapter);
    }
}
