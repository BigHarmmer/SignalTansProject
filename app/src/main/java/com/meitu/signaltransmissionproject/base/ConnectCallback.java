package com.meitu.signaltransmissionproject.base;

import android.support.annotation.WorkerThread;

/**
 * @author lyd
 */
public interface ConnectCallback {
    /**
     * 连接状态变更监听
     */
    @WorkerThread
    void onConnect();

    @WorkerThread
    void onDisconnect();


}
