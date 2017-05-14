package com.cruse.life.view;

import com.cruse.life.entity.BookDetailBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface BookDetailView extends View{

    void onSuccess(BookDetailBean bean);

    void onError(String result);
}
