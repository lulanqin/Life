package com.cruse.life.view;

import com.cruse.life.entity.FocusDetailBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FocusDetailView extends View{

    void onSuccess(FocusDetailBean bean);

    void onError(String result);
}
