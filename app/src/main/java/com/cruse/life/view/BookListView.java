package com.cruse.life.view;

import com.cruse.life.entity.BookListBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface BookListView extends View{

    void onSuccess(BookListBean bean);

    void onError(String result);
}
