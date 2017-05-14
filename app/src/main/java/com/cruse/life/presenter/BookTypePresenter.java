package com.cruse.life.presenter;


        import android.content.Context;
        import android.content.Intent;

        import com.cruse.life.entity.BookTypeBean;
        import com.cruse.life.service.DataManager;
        import com.cruse.life.view.BookTypeView;
        import com.cruse.life.view.View;

        import rx.Observer;
        import rx.android.schedulers.AndroidSchedulers;
        import rx.schedulers.Schedulers;
        import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class BookTypePresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookTypeView mBookView;
    private BookTypeBean mBookBean;
    public BookTypePresenter(Context mContext){
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
        mBookView = (BookTypeView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet(){
        mCompositeSubscription.add(manager.getBookType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookTypeBean>() {
                    @Override
                    public void onCompleted() {
                        if (mBookBean != null){
                            mBookView.onSuccess(mBookBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(BookTypeBean bean) {
                        mBookBean =bean;
                        mBookBean.getTngou().get(0).getName();
                    }
                })

        );
    }

}

