package com.meitu.signaltransmissionproject;

import android.annotation.SuppressLint;
import android.app.Application;

import com.meitu.signaltransmissionproject.common.SharedPreManager;

/**
 * @author lyd
 */
public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreManager.init(this, SharedPreManager.SP_NAME);
        mApplication = this;
    }

    public static Application getInstance() {
        return mApplication;
    }
}
