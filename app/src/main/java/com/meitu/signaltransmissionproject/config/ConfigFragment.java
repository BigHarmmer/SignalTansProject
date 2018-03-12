package com.meitu.signaltransmissionproject.config;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseFragment;
import com.meitu.signaltransmissionproject.common.DialogUtil;
import com.meitu.signaltransmissionproject.common.SharedPreManager;

/**
 * @author lyd
 */
public class ConfigFragment extends BaseFragment implements IConfigView {

    private ConfigPresenter mPresenter;
    private static final String TAG = "ConfigFragment";
    private EditText mIpEt;
    private EditText mPortEt;
    private EditText mIdentifyEt;
    private Switch mConnectSwitch;
    private TextView mStatusTv;
    private AlertDialog mDialog;

    public ConfigFragment() {
        mPresenter = new ConfigPresenter(this);
    }

    @Override
    public String getTopTitle() {
        return "配置";
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_config;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIpEt = view.findViewById(R.id.config_ip_et);
        mPortEt = view.findViewById(R.id.config_port_et);
        mIdentifyEt = view.findViewById(R.id.config_identify_et);
        mConnectSwitch = view.findViewById(R.id.config_connect_sw);
        mConnectSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPresenter.connect(mIpEt.getText().toString(), mPortEt.getText().toString(), mIdentifyEt.getText().toString(),
                            "866413037510201", "99001008875510");
                } else {
                    mPresenter.toggleDisconnect();
                }
            }
        });
        mStatusTv = view.findViewById(R.id.config_status_tv);
        mIpEt.setText(SharedPreManager.getIpHistory());
        mPortEt.setText(SharedPreManager.getPortHistory());
        mIdentifyEt.setText(SharedPreManager.getIdentifyHistory());
        mConnectSwitch.setChecked(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreManager.setIpHistory(mIpEt.getText().toString());
        SharedPreManager.setPortHistory(mPortEt.getText().toString());
        SharedPreManager.setIdentifyHistory(mIdentifyEt.getText().toString());
    }

    @Override
    public void showDialog() {
        Context context = getActivity();
        mDialog = DialogUtil.showSystemDialog(context,
                context.getString(R.string.dialog_disconnect_title),
                context.getString(R.string.dialog_disconnect_content),
                context.getString(R.string.dialog_disconnect_positive),
                null,
                context.getString(R.string.dialog_disconnect_negative),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                mPresenter.disconnect();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                mConnectSwitch.setChecked(true);
                                break;
                        }
                    }
                });
    }

    @Override
    public void onConnect() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStatusTv.setText(R.string.config_status_success);
            }
        });
    }

    @Override
    public void onDisconnect() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStatusTv.setText(R.string.config_status_fail);
            }
        });
    }

}
