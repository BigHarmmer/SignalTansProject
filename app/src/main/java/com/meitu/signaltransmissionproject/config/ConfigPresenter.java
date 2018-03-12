package com.meitu.signaltransmissionproject.config;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.WorkerThread;

import com.meitu.signaltransmissionproject.base.AbsPresenter;
import com.meitu.signaltransmissionproject.base.ConnectCallback;
import com.meitu.signaltransmissionproject.common.DialogUtil;
import com.meitu.signaltransmissionproject.common.SignalTranslationManager;

/**
 * @author lyd
 *         处理config界面的所有逻辑，让activity只负责显示UI
 */
public class ConfigPresenter extends AbsPresenter<IConfigView> implements ConnectCallback {

    public static final int CONNECTED = 0;
    public static final int DISCONNECTED = 1;


    @IntDef({CONNECTED, DISCONNECTED})
    public @interface ConnectStatus {

    }

    @ConnectStatus
    private int mStatus;


    public ConfigPresenter(IConfigView iConfigView) {
        super(iConfigView);
        mStatus = DISCONNECTED;
    }

    /**
     * 关闭开关
     */
    public void toggleDisconnect() {
        if (mStatus == DISCONNECTED) {
            //TODO:切断连接
            disconnect();
        } else {
            mMvpView.showDialog();
        }
    }

    /**
     * 开始连接
     */
    public void connect(String baseUrl, String port, String id, String imei, String meid) {
        if (mStatus == CONNECTED) {
            return;
        }
        //TODO：请求网络
        SignalTranslationManager manager = SignalTranslationManager.getInstance();
        manager.setRequestMessage(baseUrl, port, id, imei, meid);
        manager.startConnect(this);
    }

    /**
     * 切断连接
     */
    public void disconnect() {
        //TODO:断开网络
        SignalTranslationManager manager = SignalTranslationManager.getInstance();
        manager.stopConnect();
    }

    @Override
    public void onConnect() {
        mStatus = CONNECTED;
        mMvpView.onConnect();
    }

    @Override
    public void onDisconnect() {
        mStatus = DISCONNECTED;
        mMvpView.onDisconnect();
    }


}
