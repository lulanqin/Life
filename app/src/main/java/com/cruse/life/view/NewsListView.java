package com.cruse.life.view;

import com.cruse.life.entity.NewsListBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface NewsListView extends View{

    void onSuccess(NewsListBean bean);

    void onError(String result);
}
