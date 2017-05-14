package com.cruse.life.presenter;


import android.content.Context;
import android.content.Intent;


import com.cruse.life.entity.FoodBean;
import com.cruse.life.entity.FoodTypeListBean;
import com.cruse.life.service.DataManager;
import com.cruse.life.view.FoodTypeListView;
import com.cruse.life.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class FoodTypeListPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private FoodTypeListView mFoodListView;
    private FoodTypeListBean mFoodTypeListBean;

    public FoodTypeListPresenter(Context mContext, String id) {
        this.mContext = mContext;
        this.id = id;
    }

    String id;
    FoodBean.TngouBean bean;

    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mFoodListView = (FoodTypeListView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet() {
        mCompositeSubscription.add(manager.getFoodTypeList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodTypeListBean>() {
                    @Override
                    public void onCompleted() {
                        if (mFoodTypeListBean != null) {
                            mFoodListView.onSuccess(mFoodTypeListBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFoodListView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(FoodTypeListBean bean) {
                        mFoodTypeListBean = bean;
                        //mFoodTypeListBean.getTngou().get(0).getName();
                    }
                })

        );
    }
}

