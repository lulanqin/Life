package com.cruse.life.presenter;


        import android.content.Context;
        import android.content.Intent;


        import com.cruse.life.entity.FoodBean;
        import com.cruse.life.entity.FoodTypeListBean;
        import com.cruse.life.service.DataManager;
        import com.cruse.life.view.FoodTypeView;
        import com.cruse.life.view.View;

        import rx.Observer;
        import rx.android.schedulers.AndroidSchedulers;
        import rx.schedulers.Schedulers;
        import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class FoodTypePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private FoodTypeView mFoodView;
    private FoodBean mFoodBean;
    private FoodTypeListBean mFoodTypeListBean;
    public FoodTypePresenter (Context mContext){
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
        mFoodView = (FoodTypeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet(){
        mCompositeSubscription.add(manager.getFoodType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onCompleted() {
                        if (mFoodBean != null){
                            mFoodView.onSuccess(mFoodBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFoodView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(FoodBean foodBean) {
                        mFoodBean =foodBean;
                        mFoodBean.getTngou().get(0).getName();
                    }
                })
        );
    }

    public void getCurrentFood(String id){
        mCompositeSubscription.add(manager.getFoodTypeList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodTypeListBean>() {
                    @Override
                    public void onCompleted() {
                        if (mFoodTypeListBean != null){
                            mFoodView.onCurrentFood(mFoodTypeListBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mFoodView.onError("请求失败！！");
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

