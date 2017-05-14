package com.cruse.life.service;

import android.content.Context;

import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lulanqin on 2017/4/4.
 */

public class RetrofitHelper {

    private Context mCntext;
    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;

    private Retrofit mFoodType = null;
    private Retrofit mFoodTypeList = null;
    private Retrofit mFoodDetail = null;
    private String cookUrl = "http://www.tngou.net/api/cook/";

    private Retrofit mBookType = null;
    private Retrofit mBookList = null;
    private Retrofit mBookDetail = null;
    private String bookUrl = "http://www.tngou.net/api/book/";

    private Retrofit mFocusType = null;
    private Retrofit mFocusUpdate = null;
    private Retrofit mFocusList = null;
    private Retrofit mFocusDetail = null;
    private String focusUrl = "http://www.tngou.net/api/top/";

    private Retrofit mNewsType = null;
    private Retrofit mNewsList = null;
    private Retrofit mNewsDetail = null;
    private String newsUrl = "http://www.tngou.net/api/lore/";


    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {

        mFoodType = new Retrofit.Builder()
                .baseUrl(cookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFoodTypeList = new Retrofit.Builder()
                .baseUrl(cookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFoodDetail = new Retrofit.Builder()
                .baseUrl(cookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        mBookType = new Retrofit.Builder()
                .baseUrl(bookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mBookList = new Retrofit.Builder()
                .baseUrl(bookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mBookDetail = new Retrofit.Builder()
                .baseUrl(bookUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFocusType = new Retrofit.Builder()
                .baseUrl(focusUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //http://www.tngou.net/api/info/
        mFocusUpdate = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net/api/info/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFocusList = new Retrofit.Builder()
                .baseUrl(focusUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mFocusDetail = new Retrofit.Builder()
                .baseUrl(focusUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mNewsType = new Retrofit.Builder()
                .baseUrl(newsUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mNewsList = new Retrofit.Builder()
                .baseUrl(newsUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mNewsDetail = new Retrofit.Builder()
                .baseUrl(newsUrl)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitService getFoodType(){
        return mFoodType.create(RetrofitService.class);
    }

    public RetrofitService getFoodTypeList(String id){
        return mFoodTypeList.create(RetrofitService.class);
    }

    public RetrofitService getFoodDetail(String id){
        return mFoodDetail.create(RetrofitService.class);
    }

    public RetrofitService getBookType(){
        return mBookType.create(RetrofitService.class);
    }

    public RetrofitService getBookList(String id){
        return mBookList.create(RetrofitService.class);
    }

    public RetrofitService getBookDetail(String id){
        return mBookDetail.create(RetrofitService.class);
    }

    public RetrofitService getFocusType(){
        return mFocusType.create(RetrofitService.class);
    }

    public RetrofitService getFocusUpdate(){
        return mFocusUpdate.create(RetrofitService.class);
    }

    public RetrofitService getFocusList(String id){
        return mFocusList.create(RetrofitService.class);
    }

    public RetrofitService getFocusDetail(String id){
        return mFocusDetail.create(RetrofitService.class);
    }

    public RetrofitService getNewsType(){
        return mNewsType.create(RetrofitService.class);
    }

    public RetrofitService getNewsList(String id){
        return mNewsList.create(RetrofitService.class);
    }

    public RetrofitService getNewsDetail(String id){
        return mNewsDetail.create(RetrofitService.class);
    }
}

