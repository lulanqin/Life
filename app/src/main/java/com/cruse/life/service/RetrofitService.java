package com.cruse.life.service;


import com.cruse.life.entity.FoodTypeListBean;
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

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lulanqin on 2017/4/4.
 */

public interface RetrofitService {

    //http://www.tngou.net/api/cook/classify
    @GET("classify")
    Observable<FoodBean> getFoodType();

    //http://www.tngou.net/api/cook/list?id=2
    @GET("list")
    Observable<FoodTypeListBean> getFoodTypeList(@Query("id") String id);

    //http://www.tngou.net/api/cook/show
    @GET("show")
    Observable<FoodDetailBean> getFoodDetail(@Query("id") String id);

    //http://www.tngou.net/api/book/classify
    @GET("classify")
    Observable<BookTypeBean> getBookType();

    //http://www.tngou.net/api/book/list?id=2
    @GET("list")
    Observable<BookListBean> getBookList(@Query("id") String id);

    //http://www.tngou.net/api/book/show?id=1
    @GET("show")
    Observable<BookDetailBean> getBookDeatail(@Query("id") String id);

    //http://www.tngou.net/api/top/classify
    @GET("classify")
    Observable<FocusTypeBean> getFocusType();

    @GET("list")
    Observable<FocusListBean> getFocusList(@Query("id") String id);

    //http://www.tngou.net/api/top/show?id=10
    @GET("show")
    Observable<FocusDetailBean> getFocusDeatail(@Query("id") String id);

    //http://www.tngou.net/api/info/classify
    @GET("classify")
    Observable<NewsTypeBean> getNewsType();

    //http://www.tngou.net/api/info/list?id=2
    @GET("list")
    Observable<NewsListBean> getNewsList(@Query("id") String id);

    //http://www.tngou.net/api/info/show?id=10
    @GET("show")
    Observable<NewsDetailBean> getNewsDeatail(@Query("id") String id);

    //http://www.tngou.net/api/info/news?id=0
    @GET("news")
    Observable<FocusUpdateBean> getFocusUpdate(@Query("id") String id);


}
