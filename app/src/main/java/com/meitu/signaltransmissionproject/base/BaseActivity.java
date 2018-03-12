package com.meitu.signaltransmissionproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;

/**
 * @author lyd
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener, IMvpView {

    private FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity);
        init();
    }

    private void init() {
        mContainer = findViewById(R.id.container);
        ImageView mBackIv = findViewById(R.id.back);
        mBackIv.setOnClickListener(this);
        TextView mTitleTv = findViewById(R.id.title);
        mTitleTv.setText(getTopTitle());
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        mContainer.addView(view);
    }

    /**
     * 子类继承实现，以写入标题
     *
     * @return 显示的标题
     */
    public abstract String getTopTitle();

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            onBackPressed();
        }
    }
}
