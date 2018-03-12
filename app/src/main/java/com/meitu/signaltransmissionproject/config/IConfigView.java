package com.meitu.signaltransmissionproject.config;

import com.meitu.signaltransmissionproject.base.IMvpView;

/**
 * @author lyd
 */
public interface IConfigView extends IMvpView {

    void showDialog();

    void onConnect();

    void onDisconnect();
}
