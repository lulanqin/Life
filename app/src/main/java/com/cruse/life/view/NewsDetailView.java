package com.cruse.life.view;

import com.cruse.life.entity.NewsDetailBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface NewsDetailView extends View{

    void onSuccess(NewsDetailBean bean);

    void onError(String result);
}
