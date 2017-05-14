package com.cruse.life.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;


import com.cruse.life.R;
import com.cruse.life.adapter.RvCurrFoodAdapter;
import com.cruse.life.adapter.RvFoodTypeAdapter;
import com.cruse.life.entity.FoodBean;
import com.cruse.life.entity.FoodTypeListBean;
import com.cruse.life.presenter.FoodTypePresenter;
import com.cruse.life.view.FoodTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class FragmentHome extends Fragment {
    private View view;
    Context mContent;
    ListView lv;
    RecyclerView rv_food;
    RecyclerView rv_current;
    //FoodTypeAdapter adapter;
    RvFoodTypeAdapter adapter;
    RvCurrFoodAdapter currentAdapter;
    FoodTypePresenter mPresenter;
    List<String> idList = new ArrayList<>();

    String[] idArray = {"1", "10", "15", "52", "62", "212", "218",
            "68", "75", "82", "98", "112", "197", "202", "205", "227",
            "132", "147", "161", "166", "182", "188", "192"};

    private FoodTypeView mFoodTypeView = new FoodTypeView() {
        @Override
        public void onSuccess(FoodBean bean) {
            //adapter = new FoodTypeAdapter(getActivity(),mFoodBean.getTngou());
            //lv.setAdapter(adapter);

            adapter = new RvFoodTypeAdapter(getActivity(), bean);
            rv_food.setAdapter(adapter);
        }

        @Override
        public void onCurrentFood(FoodTypeListBean bean) {
            currentAdapter = new RvCurrFoodAdapter(getActivity(), bean);
            rv_current.setAdapter(currentAdapter);

        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.act_food_type, container, false);
            mContent = getActivity();

            rv_food = (RecyclerView) view.findViewById(R.id.rv_food);
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_food.setLayoutManager(linearLayoutManager);


            rv_current = (RecyclerView) view.findViewById(R.id.rv_current);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            rv_current.setLayoutManager(gridLayoutManager);
            rv_current.addItemDecoration(new GridSpacingItemDecoration(2, 30, true));


            mPresenter = new FoodTypePresenter(mContent);
            mPresenter.onCreate();
            mPresenter.getDataFromNet();

            int id = (int) (Math.random() * (idArray.length));
            String temp = idArray[id];
            mPresenter.getCurrentFood(idArray[id]);
            mPresenter.attachView(mFoodTypeView);
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


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}

