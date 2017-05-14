package com.cruse.life.presenter;

        import android.content.Context;
        import android.content.Intent;

        import com.cruse.life.entity.BookListBean;
        import com.cruse.life.entity.FoodBean;
        import com.cruse.life.service.DataManager;
        import com.cruse.life.view.BookListView;
        import com.cruse.life.view.View;

        import rx.Observer;
        import rx.android.schedulers.AndroidSchedulers;
        import rx.schedulers.Schedulers;
        import rx.subscriptions.CompositeSubscription;

/**
 * Created by lulanqin on 2017/4/4.
 */
public class BookListPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookListView mBookListView;
    private BookListBean mBookListBean;
    public BookListPresenter(Context mContext, String id ){
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
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mBookListView = (BookListView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getDataFromNet(){
        mCompositeSubscription.add(manager.getBookList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookListBean>() {
                    @Override
                    public void onCompleted() {
                        if (mBookListBean != null){
                            mBookListView.onSuccess(mBookListBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mBookListView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(BookListBean bean) {
                        mBookListBean = bean;
                        //mFoodTypeListBean.getTngou().get(0).getName();
                    }
                })
        );
    }
}

