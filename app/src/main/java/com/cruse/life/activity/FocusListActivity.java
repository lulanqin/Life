package com.cruse.life.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cruse.life.R;
import com.cruse.life.adapter.FocusListAdapter;
import com.cruse.life.entity.FocusListBean;
import com.cruse.life.presenter.FocusListPresenter;
import com.cruse.life.view.FocusListView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class FocusListActivity extends Activity {
    ListView lv;
    ImageView iv_back;
    FocusListAdapter adapter;
    FocusListPresenter mPresenter;
    String name;
    String id;


    private FocusListView mView = new FocusListView() {
        @Override
        public void onSuccess(FocusListBean bean) {
            adapter = new FocusListAdapter(getApplicationContext(), bean.getTngou());
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

        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");

        tv_title.setText(name);

        iv_back = (ImageView) findViewById(R.id.iv_back);


        mPresenter = new FocusListPresenter(this, id);
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
