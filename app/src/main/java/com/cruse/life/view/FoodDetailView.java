package com.cruse.life.view;

import com.cruse.life.entity.FoodDetailBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FoodDetailView extends View {

    void onSuccess(FoodDetailBean mFoodBean);

    void onError(String result);
}
