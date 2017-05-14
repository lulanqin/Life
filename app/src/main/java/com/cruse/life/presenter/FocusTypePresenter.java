package com.cruse.life.presenter;



        import android.content.Context;
        import android.content.Intent;


        import com.cruse.life.entity.FocusTypeBean;
        import com.cruse.life.entity.FocusUpdateBean;
        import com.cruse.life.service.DataManager;
        import com.cruse.life.view.FocusTypeView;
        import com.cruse.life.view.View;

        import rx.Observer;
        import rx.android.schedulers.AndroidSchedulers;
        import rx.schedulers.Schedulers;
        import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class FocusTypePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private FocusTypeView mView;
    private FocusTypeBean mBean;
    private FocusUpdateBean mUpdateBean;
    public FocusTypePresenter(Context mContext){
        this.mContext = mContext;
    }

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
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mView = (FocusTypeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet(){
        mCompositeSubscription.add(manager.getFocusType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FocusTypeBean>() {
                    @Override
                    public void onCompleted() {
                        if (mBean != null){
                            mView.getFocusType(mBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(FocusTypeBean bean) {
                        mBean =bean;
                        mBean.getTngou().get(0).getName();
                    }
                })

        );
    }

    public void getDataFocusUpdate(String id){
        String tid = id;
        mCompositeSubscription.add(manager.getFocusUpdate(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FocusUpdateBean>() {
                    @Override
                    public void onCompleted() {
                        if (mUpdateBean != null){
                            mView.getFocusUpdate(mUpdateBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(FocusUpdateBean bean) {
                        mUpdateBean =bean;
                        mUpdateBean.getTngou().get(0).getKeywords();
                    }
                })

        );
    }

}

