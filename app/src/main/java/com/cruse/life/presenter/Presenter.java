package com.cruse.life.presenter;

import android.content.Intent;

import com.cruse.life.view.View;


/**
 * Created by lulanqin on 2017/4/4.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}

