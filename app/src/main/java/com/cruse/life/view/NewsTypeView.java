package com.cruse.life.view;

import com.cruse.life.entity.NewsTypeBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface NewsTypeView extends View {
    void onSuccess(NewsTypeBean bean);

    void onError(String result);
}
