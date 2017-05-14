package com.cruse.life.presenter;


import android.content.Context;
import android.content.Intent;

import com.cruse.life.entity.FoodBean;
import com.cruse.life.entity.FoodDetailBean;
import com.cruse.life.service.DataManager;
import com.cruse.life.view.FoodDetailView;
import com.cruse.life.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class FoodDetailPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private FoodDetailView mFoodDetailView;
    private FoodDetailBean mFoodDetailBean;

    public FoodDetailPresenter(Context mContext) {
        this.mContext = mContext;
    }

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
        mFoodDetailView = (FoodDetailView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet(String id) {
        mCompositeSubscription.add(manager.getFoodDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodDetailBean>() {
                    @Override
                    public void onCompleted() {
                        if (mFoodDetailBean != null) {
                            mFoodDetailView.onSuccess(mFoodDetailBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFoodDetailView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(FoodDetailBean bean) {
                        mFoodDetailBean = bean;
                        //mFoodTypeListBean.getTngou().get(0).getName();
                    }
                })

        );
    }
}

