package com.meitu.signaltransmissionproject.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseActivity;
import com.meitu.signaltransmissionproject.common.DeviceIdUtil;

/**
 * @author lyd
 */
public class MessageActivity extends BaseActivity {
    @Override
    public String getTopTitle() {
        return "IMEI信息";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_message);
        ((TextView) findViewById(R.id.help_imei_tv)).setText(DeviceIdUtil.getIMEI(this));
//        ((TextView) findViewById(R.id.help_meid_tv)).setText(DeviceIdUtil.getIMEI(this));
    }
}
