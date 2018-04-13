package com.meitu.signaltransmissionproject.message;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseFragment;

/**
 * @author lyd
 */
public class MessageFragment extends BaseFragment implements IMessageView {

    private MessagePresenter mPresenter;
    private MessageReceiver mMessageReceiver;


    private ExpandableListView mExpandableListView;
    private SignalExpandableAdapter mSignalExpandableAdapter;

    String[] parentTitle = new String[]{"最新消息","BPK列表", "SPK列表", "BOLL列表", "DT列表"};

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mExpandableListView = view.findViewById(R.id.message_elv);

        updateUi();
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
        updateUi();
    }

    public void updateUi() {
        mSignalExpandableAdapter = new SignalExpandableAdapter(parentTitle, getActivity(), mPresenter.getTotalList());
        mExpandableListView.setAdapter(mSignalExpandableAdapter);
// }
    }


}
