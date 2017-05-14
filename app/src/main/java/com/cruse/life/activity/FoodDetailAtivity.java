package com.cruse.life.activity;


import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.entity.FoodDetailBean;
import com.cruse.life.presenter.FoodDetailPresenter;
import com.cruse.life.view.FoodDetailView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class FoodDetailAtivity extends Activity {

    TextView tv_FoodDetail_name;
    TextView tv_FoodDetail_description;
    TextView tv_FoodDetail_summary;
    TextView tv_FoodDetail_message;
    ImageView iv;
    ImageView iv_back;

    String id;

    FoodDetailPresenter mPresenter;


    private FoodDetailView mFoodTypeView = new FoodDetailView() {
        @Override
        public void onSuccess(FoodDetailBean bean) {
            tv_FoodDetail_name.setText(bean.getName());
            //tv_FoodDetail_description.setText(bean.getDescription());

            if (bean.getSummary() != null)
                tv_FoodDetail_summary.setText(Html.fromHtml(bean.getSummary()));

            if (bean.getMessage() != null)
                tv_FoodDetail_message.setText(Html.fromHtml(bean.getMessage()));

            Glide.with(getApplicationContext()).load("http://tnfs.tngou.net/img" + bean.getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv);

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
        setContentView(R.layout.act_food_detail);

        initView();
        id = getIntent().getStringExtra("id");

        mPresenter = new FoodDetailPresenter(this);
        mPresenter.onCreate();
        mPresenter.getDataFromNet(id);
        mPresenter.attachView(mFoodTypeView);

    }

    private void initView() {
        tv_FoodDetail_name = (TextView) findViewById(R.id.tv_FoodDetail_name);
        //tv_FoodDetail_description = (TextView) findViewById(R.id.tv_FoodDetail_description);
        tv_FoodDetail_summary = (TextView) findViewById(R.id.tv_FoodDetail_summary);
        tv_FoodDetail_message = (TextView) findViewById(R.id.tv_FoodDetail_message);
        iv = (ImageView) findViewById(R.id.iv_FoodDetail_img);
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}

