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

    private TextView mBpkTimeTv;
    private TextView mSpkTimeTv;
    private TextView mBollTimeTv;
    private TextView mDtTimeTv;

    private TextView mBpkDetailTv;
    private TextView mSpkDetailTv;
    private TextView mBollDetailTv;
    private TextView mDtDetailTv;

    private ExpandableListView mExpandableListView;

    String[] parentTitle = new String[]{"BPK列表", "SPK列表", "BOLL列表", "DT列表"};

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
        LinearLayout bpk = view.findViewById(R.id.BPK_layout);
        mBpkTimeTv = bpk.findViewById(R.id.item_time_tv);
        mBpkDetailTv = bpk.findViewById(R.id.item_detail_tv);
        LinearLayout spk = view.findViewById(R.id.SPK_layout);
        mSpkTimeTv = spk.findViewById(R.id.item_time_tv);
        mSpkDetailTv = spk.findViewById(R.id.item_detail_tv);
        LinearLayout boll = view.findViewById(R.id.BOLL_layout);
        mBollTimeTv = boll.findViewById(R.id.item_time_tv);
        mBollDetailTv = boll.findViewById(R.id.item_detail_tv);
        LinearLayout dt = view.findViewById(R.id.DT_layout);
        mDtTimeTv = dt.findViewById(R.id.item_time_tv);
        mDtDetailTv = dt.findViewById(R.id.item_detail_tv);
        mExpandableListView = view.findViewById(R.id.message_elv);
        mExpandableListView.setAdapter(new SignalExpandableAdapter(parentTitle, getActivity(), mPresenter.getTotalList()));
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
        mBpkTimeTv.setText(mPresenter.getBPKs().get(0)[0]);
        mBpkDetailTv.setText(mPresenter.getBPKs().get(0)[1]);
        mSpkTimeTv.setText(mPresenter.getSPKs().get(0)[0]);
        mSpkDetailTv.setText(mPresenter.getSPKs().get(0)[1]);
        mBollTimeTv.setText(mPresenter.getBOLLs().get(0)[0]);
        mBollDetailTv.setText(mPresenter.getBOLLs().get(0)[1]);
        mDtTimeTv.setText(mPresenter.getDTs().get(0)[0]);
        mDtDetailTv.setText(mPresenter.getDTs().get(0)[1]);
    }


}
