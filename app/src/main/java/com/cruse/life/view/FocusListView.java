package com.cruse.life.view;

import com.cruse.life.entity.FocusListBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FocusListView extends View{

    void onSuccess(FocusListBean bean);

    void onError(String result);
}
