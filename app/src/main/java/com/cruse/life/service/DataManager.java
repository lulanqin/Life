package com.cruse.life.service;


import android.content.Context;


import com.cruse.life.entity.BookDetailBean;
import com.cruse.life.entity.BookListBean;
import com.cruse.life.entity.BookTypeBean;
import com.cruse.life.entity.FocusDetailBean;
import com.cruse.life.entity.FocusListBean;
import com.cruse.life.entity.FocusTypeBean;
import com.cruse.life.entity.FocusUpdateBean;
import com.cruse.life.entity.FoodBean;
import com.cruse.life.entity.FoodDetailBean;
import com.cruse.life.entity.FoodTypeListBean;
import com.cruse.life.entity.NewsDetailBean;
import com.cruse.life.entity.NewsListBean;
import com.cruse.life.entity.NewsTypeBean;
import com.cruse.life.service.RetrofitHelper;
import com.cruse.life.service.RetrofitService;


import rx.Observable;

/**
 * Created by lulanqin on 2017/4/4.
 */

public class DataManager {

    String id;

    private RetrofitService mFoodTypeService;
    private RetrofitService mFoodTypeListService;
    private RetrofitService mFoodDetailService;


    private RetrofitService mBookTypeService;
    private RetrofitService mBookListService;
    private RetrofitService mBookDetailService;

    private RetrofitService mFocusTypeService;
    private RetrofitService mFocusUpdateService;
    private RetrofitService mFocusListService;
    private RetrofitService mFocusDetailService;

    private RetrofitService mNewsTypeService;
    private RetrofitService mNewsListService;
    private RetrofitService mNewsDetailService;

    public DataManager(Context context) {

        this.mFoodTypeService = RetrofitHelper.getInstance(context).getFoodType();

        this.mFoodTypeListService = RetrofitHelper.getInstance(context).getFoodTypeList(id);

        this.mFoodDetailService = RetrofitHelper.getInstance(context).getFoodDetail(id);

        this.mBookTypeService = RetrofitHelper.getInstance(context).getBookType();

        this.mBookListService = RetrofitHelper.getInstance(context).getBookList(id);

        this.mBookDetailService = RetrofitHelper.getInstance(context).getBookDetail(id);

        this.mFocusTypeService = RetrofitHelper.getInstance(context).getFocusType();

        this.mFocusUpdateService = RetrofitHelper.getInstance(context).getFocusUpdate();

        this.mFocusListService = RetrofitHelper.getInstance(context).getFocusList(id);

        this.mFocusDetailService = RetrofitHelper.getInstance(context).getFocusDetail(id);

        this.mNewsTypeService = RetrofitHelper.getInstance(context).getNewsType();

        this.mNewsListService = RetrofitHelper.getInstance(context).getNewsList(id);

        this.mNewsDetailService = RetrofitHelper.getInstance(context).getNewsDetail(id);
    }

    public Observable<FoodBean> getFoodType() {
        return mFoodTypeService.getFoodType();
    }

    public Observable<FoodTypeListBean> getFoodTypeList(String id) {
        return mFoodTypeListService.getFoodTypeList(id);
    }

    public Observable<FoodDetailBean> getFoodDetail(String id) {
        return mFoodDetailService.getFoodDetail(id);
    }

    public Observable<BookTypeBean> getBookType() {
        return mBookTypeService.getBookType();
    }

    public Observable<BookListBean> getBookList(String id) {
        return mBookListService.getBookList(id);
    }

    public Observable<BookDetailBean> getBookDetail(String id) {
        return mBookDetailService.getBookDeatail(id);
    }

    public Observable<FocusTypeBean> getFocusType() {
        return mFocusTypeService.getFocusType();
    }

    public Observable<FocusUpdateBean> getFocusUpdate(String id) {
        return mFocusUpdateService.getFocusUpdate(id);
    }

/*    public Observable<FocusUpdateBean> getFocusUpdate(String key,String page){
        return mFocusUpdateService.getFocusUpdate(key,page);
    }*/

    public Observable<FocusListBean> getFocusList(String id) {
        return mFocusListService.getFocusList(id);
    }

    public Observable<FocusDetailBean> getFocusDetail(String id) {
        return mFocusDetailService.getFocusDeatail(id);
    }

    public Observable<NewsTypeBean> getNewsType() {
        return mNewsTypeService.getNewsType();
    }

    public Observable<NewsListBean> getNewsList(String id) {
        return mNewsListService.getNewsList(id);
    }

    public Observable<NewsDetailBean> getNewsDetail(String id) {
        return mNewsDetailService.getNewsDeatail(id);
    }

}

