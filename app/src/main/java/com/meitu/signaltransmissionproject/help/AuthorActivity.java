package com.meitu.signaltransmissionproject.help;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseActivity;

/**
 * @author lyd
 */
public class AuthorActivity extends BaseActivity {
    @Override
    public String getTopTitle() {
        return "联系作者";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_author);
    }
}
