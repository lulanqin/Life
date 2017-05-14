package com.cruse.life.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cruse.life.R;
import com.cruse.life.adapter.NewsListAdapter;
import com.cruse.life.entity.NewsListBean;
import com.cruse.life.presenter.NewsListPresenter;
import com.cruse.life.view.NewsListView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class NewsListActivity extends Activity {
    ListView lv;
    ImageView iv_back;
    NewsListAdapter adapter;
    NewsListPresenter mPresenter;
    String name;
    String id;


    private NewsListView mView = new NewsListView() {
        @Override
        public void onSuccess(NewsListBean bean) {
            adapter = new NewsListAdapter(getApplicationContext(), bean.getTngou());
            lv.setAdapter(adapter);
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_food_list);

        lv = (ListView) findViewById(R.id.lv_FoodTypeList);
        TextView tv_title = (TextView) findViewById(R.id.tv_FoodType_title);

        iv_back = (ImageView) findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");

        tv_title.setText(name);

        mPresenter = new NewsListPresenter(this, id);
        mPresenter.onCreate();
        mPresenter.getDataFromNet();
        mPresenter.attachView(mView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}

