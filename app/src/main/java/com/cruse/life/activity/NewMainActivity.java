package com.cruse.life.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cruse.life.R;
import com.cruse.life.fragment.FragmentBook;
import com.cruse.life.fragment.FragmentFocus;
import com.cruse.life.fragment.FragmentHome;
import com.cruse.life.fragment.FragmentNews;


/**
 * Created by lulanqin on 2017/5/11.
 */
public class NewMainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup group;
    //首页
    private RadioButton main_home;
    //热点
    private RadioButton main_focus;
    //资讯
    private RadioButton main_news;
    //图书
    private RadioButton main_book;

    private FrameLayout main_content;

    private FragmentManager fragmentManager;//管理fragment的类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initUI();
        //初始化FragmentManager
        fragmentManager = getSupportFragmentManager();
        //设置默认选中
        main_home.setChecked(true);
        group.setOnCheckedChangeListener(this);
        //切换不同的fragment
        changeFragment(new FragmentHome(), false);
    }

    private void initUI() {
        group = (RadioGroup) findViewById(R.id.main_bottom_tabs);
        main_home = (RadioButton) findViewById(R.id.main_home);
        main_focus = (RadioButton) findViewById(R.id.main_focus);
        main_news = (RadioButton) findViewById(R.id.main_news);
        main_book = (RadioButton) findViewById(R.id.main_book);
        main_content = (FrameLayout) findViewById(R.id.main_content);
    }

    //切换不同的fragment
    public void changeFragment(Fragment fragment, boolean isInit) {
        //开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        if (!isInit) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {

            case R.id.main_home://首页
                changeFragment(new FragmentHome(), true);
                main_home.setChecked(true);
                break;

            case R.id.main_focus://热点
                changeFragment(new FragmentFocus(), true);
                main_focus.setChecked(true);
                break;

            case R.id.main_news://资讯
                changeFragment(new FragmentNews(), true);
                main_news.setChecked(true);
                break;

            case R.id.main_book://图书
                changeFragment(new FragmentBook(), true);
                main_book.setChecked(true);
                break;

            default:
                changeFragment(new FragmentHome(), true);
        }

    }
}
