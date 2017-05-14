package com.cruse.life.view;

import com.cruse.life.entity.FoodTypeListBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FoodTypeListView extends View {

    void onSuccess(FoodTypeListBean typeListBean);

    void onError(String result);
}
