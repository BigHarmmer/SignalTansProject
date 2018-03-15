package com.meitu.signaltransmissionproject.message;

import com.meitu.signaltransmissionproject.base.AbsPresenter;
import com.meitu.signaltransmissionproject.common.SignalStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyd
 */
public class MessagePresenter extends AbsPresenter<IMessageView> implements MessageReceiver.MessageCallback {

    //存放BPK，SPK，BOLL，DT列表
    private SignalStorage mStorage;

    public MessagePresenter(IMessageView iMessageView) {
        super(iMessageView);
        mStorage = new SignalStorage();
    }


    @Override
    public void onReceiveMessage(String[] message) {
        mStorage.update(message);
        //通知更新
        mMvpView.onListUpdate();
    }

    public void destroy() {
        mStorage.save();
    }

    public List<String[]> getBPKs() {
        return mStorage.getTotalList().get(0);
    }

    public List<String[]> getSPKs() {
        return mStorage.getTotalList().get(1);
    }

    public List<String[]> getBOLLs() {
        return mStorage.getTotalList().get(2);
    }

    public List<String[]> getDTs() {
        return mStorage.getTotalList().get(3);
    }

    public List<List<String[]>> getTotalList() {
        return mStorage.getTotalList();
    }
}
