package com.cruse.life.activity;


import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.adapter.BookDetailAdapter;
import com.cruse.life.entity.BookDetailBean;
import com.cruse.life.presenter.BookDetailPresenter;
import com.cruse.life.view.BookDetailView;
import com.cruse.life.view.CustomListView;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class BookDetailAtivity extends Activity {

    private ImageView iv;
    private TextView tv_BookDetail_author;
    private TextView tv_BookDetail_name;
    private TextView tv_BookDetail_headName;
    private TextView tv_BookDetail_summary;
    private CustomListView lv_BookDetail_chapter;
    private BookDetailAdapter adapter;
    private ImageView iv_back;

    private String id;
    private BookDetailPresenter mPresenter;


    private BookDetailView mView = new BookDetailView() {
        @Override
        public void onSuccess(BookDetailBean bean) {

            tv_BookDetail_author.setText(bean.getAuthor());
            tv_BookDetail_name.setText(bean.getName());
            tv_BookDetail_headName.setText(bean.getName());


            if (bean.getSummary() != null)
                tv_BookDetail_summary.setText(Html.fromHtml(bean.getSummary()));

            adapter = new BookDetailAdapter(getApplicationContext(), bean.getList());
            lv_BookDetail_chapter.setAdapter(adapter);


            Glide.with(getApplicationContext()).load("http://tnfs.tngou.net/img" + bean.getImg())
                    .override(70, 100)
                    .skipMemoryCache(false)
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
        setContentView(R.layout.act_book_detail);

        initView();
        id = getIntent().getStringExtra("id");

        mPresenter = new BookDetailPresenter(this, id);
        mPresenter.onCreate();
        mPresenter.getDataFromNet();
        mPresenter.attachView(mView);

    }

    private void initView() {
        tv_BookDetail_author = (TextView) findViewById(R.id.tv_BookDetail_author);
        tv_BookDetail_name = (TextView) findViewById(R.id.tv_BookDetail_name);
        tv_BookDetail_headName = (TextView) findViewById(R.id.tv_BookDetail_name1);
        tv_BookDetail_summary = (TextView) findViewById(R.id.tv_BookDetail_summary);
        lv_BookDetail_chapter = (CustomListView) findViewById(R.id.lv_BookDetail_chapter);
        lv_BookDetail_chapter.setFocusable(false);
        iv = (ImageView) findViewById(R.id.iv_BookDetail_img);
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}
