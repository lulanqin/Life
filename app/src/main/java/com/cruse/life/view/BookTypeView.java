package com.cruse.life.view;

import com.cruse.life.entity.BookTypeBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface BookTypeView extends View {

    void onSuccess(BookTypeBean bean);

    void onError(String result);
}
