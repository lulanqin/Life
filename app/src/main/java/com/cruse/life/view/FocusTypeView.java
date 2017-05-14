package com.cruse.life.view;

import com.cruse.life.entity.FocusTypeBean;
import com.cruse.life.entity.FocusUpdateBean;

/**
 * Created by lulanqin on 2017/5/14.
 */
public interface FocusTypeView extends View {

    void getFocusType(FocusTypeBean bean);

    void getFocusUpdate(FocusUpdateBean bean);

    void onError(String result);
}
