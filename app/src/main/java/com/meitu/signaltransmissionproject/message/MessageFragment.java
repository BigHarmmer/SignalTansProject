package com.meitu.signaltransmissionproject.message;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseFragment;

/**
 * @author lyd
 */
public class MessageFragment extends BaseFragment implements IMessageView {

    private MessagePresenter mPresenter;
    private MessageReceiver mMessageReceiver;

    public MessageFragment() {
        mPresenter = new MessagePresenter(this);
    }

    @Override
    public String getTopTitle() {
        return "消息";
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_message;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessageReceiver = new MessageReceiver(mPresenter);
        IntentFilter filter = new IntentFilter(MessageReceiver.MESSAGE_ACTION);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMessageReceiver != null) {
            getActivity().unregisterReceiver(mMessageReceiver);
            mMessageReceiver = null;
        }
        mPresenter.destroy();
    }

    @Override
    public void onListUpdate() {

    }
}
