package com.meitu.signaltransmissionproject.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;


/**
 * 抽象 {@link SharedPreferences} 管理器，我们并不会直接对外暴露基础数据类型的获取和设置方式，
 * 取而代之的是我们会定义具有明确含义的方法公外部调用，而不是直接暴露 {@link #getBoolean(String, boolean)} 方法，
 * 子类必须继承该类实现更多的方法。
 *
 * @author lyd
 */
public abstract class AbsSharedPrefManager {



    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static String mName;

    public static void init(Context context, String name) {
        mContext = context;
        mName = name;
    }

    protected static SharedPreferences.Editor edit() {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE).edit();
    }

    protected static void putBoolean(String key, boolean value) {
        edit().putBoolean(key, value).apply();
    }

    protected static boolean getBoolean(String key, boolean defValue) {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE).getBoolean(key, defValue);
    }

    protected static void putString(String key, String value) {
        edit().putString(key, value).apply();
    }

    protected static String getString(String key, String defValue) {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE).getString(key, defValue);
    }

    protected static void putInt(String key, int value) {
        edit().putInt(key, value).apply();
    }

    protected static int getInt(String key, int defValue) {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE).getInt(key, defValue);
    }

    protected static void putLong(String key, long value) {
        edit().putLong(key, value).apply();
    }

    protected static long getLong(String key, long defValue) {
        return mContext.getSharedPreferences(mName, Context.MODE_PRIVATE).getLong(key, defValue);
    }

}
