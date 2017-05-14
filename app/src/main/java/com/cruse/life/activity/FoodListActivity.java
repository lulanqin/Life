package com.cruse.life.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cruse.life.R;
import com.cruse.life.adapter.FoodTypeListAdapter;
import com.cruse.life.entity.FoodTypeListBean;
import com.cruse.life.presenter.FoodTypeListPresenter;
import com.cruse.life.view.FoodTypeListView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class FoodListActivity extends Activity {
    ListView lv;
    ImageView iv_back;
    FoodTypeListAdapter adapter;
    FoodTypeListPresenter mPresenter;
    String name;
    String id;


    private FoodTypeListView mFoodTypeListView = new FoodTypeListView() {
        @Override
        public void onSuccess(FoodTypeListBean bean) {
            adapter = new FoodTypeListAdapter(getApplicationContext(), bean.getTngou());
            lv.setAdapter(adapter);

            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
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

        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");

        tv_title.setText(name);

        mPresenter = new FoodTypeListPresenter(this, id);
        mPresenter.onCreate();
        mPresenter.getDataFromNet();
        mPresenter.attachView(mFoodTypeListView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}

