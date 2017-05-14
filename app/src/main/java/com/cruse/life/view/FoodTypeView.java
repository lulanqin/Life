package com.cruse.life.view;

import com.cruse.life.entity.FoodBean;
import com.cruse.life.entity.FoodTypeListBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FoodTypeView extends View {

    void onSuccess(FoodBean bean);

    void onCurrentFood(FoodTypeListBean bean);

    void onError(String result);
}
