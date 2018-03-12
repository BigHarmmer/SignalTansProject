package com.meitu.signaltransmissionproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.meitu.signaltransmissionproject.R;

/**
 * @author lyd
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        ViewStub stub = view.findViewById(R.id.container);
        stub.setLayoutResource(getContentId());
        stub.inflate();
        TextView mTitleTv = view.findViewById(R.id.title);
        mTitleTv.setText(getTopTitle());
    }


    /**
     * 子类继承实现，以写入标题
     *
     * @return 显示的标题
     */
    public abstract String getTopTitle();

    /**
     * 子类实现该方法 传入子布局id
     *
     * @return 子布局id
     */
    public abstract int getContentId();
}
