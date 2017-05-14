package com.cruse.life.presenter;


import android.content.Context;
import android.content.Intent;
import com.cruse.life.entity.BookDetailBean;
import com.cruse.life.entity.FoodBean;
import com.cruse.life.service.DataManager;
import com.cruse.life.view.BookDetailView;
import com.cruse.life.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class BookDetailPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookDetailView mView;
    private BookDetailBean mBean;

    public BookDetailPresenter(Context mContext, String id) {
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
        mView = (BookDetailView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet() {
        mCompositeSubscription.add(manager.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetailBean>() {
                    @Override
                    public void onCompleted() {
                        if (mBean != null) {
                            mView.onSuccess(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(BookDetailBean bean) {
                        mBean = bean;
                        //mFoodTypeListBean.getTngou().get(0).getName();
                    }
                })

        );
    }
}
