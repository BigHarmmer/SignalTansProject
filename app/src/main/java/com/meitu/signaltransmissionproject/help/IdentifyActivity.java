package com.meitu.signaltransmissionproject.help;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseActivity;

/**
 * @author lyd
 */
public class IdentifyActivity extends BaseActivity {
    @Override
    public String getTopTitle() {
        return "关于授权号";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_identify);
    }
}
