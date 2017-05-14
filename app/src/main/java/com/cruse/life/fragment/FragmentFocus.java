package com.cruse.life.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.cruse.life.R;
import com.cruse.life.adapter.FocusTypeAdapter;
import com.cruse.life.adapter.RvUpdateAdapter;
import com.cruse.life.entity.FocusTypeBean;
import com.cruse.life.entity.FocusUpdateBean;
import com.cruse.life.presenter.FocusTypePresenter;
import com.cruse.life.view.FocusTypeView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class FragmentFocus extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View view;
    private Context mContent;
    private ListView lv;
    private PullLoadMoreRecyclerView rv_focus;
    private RecyclerView mRecyclerView;
    private GridView gv;
    private FocusTypeAdapter adapter;
    private RvUpdateAdapter mRvUpdateAdapter;
    private FocusTypePresenter mPresenter;

    int id = 1;

    private FocusTypeView mView = new FocusTypeView() {

        @Override
        public void getFocusType(FocusTypeBean bean) {
            adapter = new FocusTypeAdapter(getActivity(), bean.getTngou());
            gv.setAdapter(adapter);
        }

        @Override
        public void getFocusUpdate(FocusUpdateBean bean) {
           /* mUpdateAdapter = new FocusUpdateAdapter(getActivity(),bean.getTngou());
            lv.setAdapter(mUpdateAdapter);*/
            mRvUpdateAdapter.addAllData(bean);
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.act_focus_type, container, false);
            mContent = getActivity();
            //lv = (ListView)view.findViewById(R.id.lv_focus);
            gv = (GridView) view.findViewById(R.id.gv_focus);

            rv_focus = (PullLoadMoreRecyclerView) view.findViewById(R.id.rv_focus);
            mRecyclerView = rv_focus.getRecyclerView();

            rv_focus.setLinearLayout();
            rv_focus.setFooterViewText("loading");
            rv_focus.setOnPullLoadMoreListener(this);

            mRvUpdateAdapter = new RvUpdateAdapter(getActivity());
            rv_focus.setAdapter(mRvUpdateAdapter);

            rv_focus.setRefreshing(true);
            //禁止下拉刷新
            rv_focus.setPullRefreshEnable(false);

            //rv_focus.setPushRefreshEnable(false);

            mPresenter = new FocusTypePresenter(mContent);
            mPresenter.onCreate();
            mPresenter.getDataFromNet();
            mPresenter.getDataFocusUpdate(id + "");
            mPresenter.attachView(mView);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onStop();
    }


    @Override
    public void onRefresh() {
        mRvUpdateAdapter.clearData();
        rv_focus.setPullRefreshEnable(false);
        mPresenter.getDataFocusUpdate(id + "");
        rv_focus.setPullLoadMoreCompleted();
        mPresenter.attachView(mView);

        rv_focus.setPushRefreshEnable(true);
        rv_focus.setFooterViewText("loading");
    }

    @Override
    public void onLoadMore() {

        rv_focus.setPullLoadMoreCompleted();

        if (id < 3) {
            id++;
            mPresenter.getDataFocusUpdate(id + "");
            mPresenter.attachView(mView);

        } else {
            id = 0;
            //上拉加载禁止
            rv_focus.setPushRefreshEnable(false);
            //下拉刷新使能
            rv_focus.setPullRefreshEnable(true);
        }

    }
}

