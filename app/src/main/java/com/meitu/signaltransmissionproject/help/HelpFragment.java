package com.meitu.signaltransmissionproject.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.meitu.signaltransmissionproject.R;
import com.meitu.signaltransmissionproject.base.BaseFragment;

/**
 * @author lyd
 */
public class HelpFragment extends BaseFragment implements View.OnClickListener {

    public HelpFragment() {

    }

    @Override
    public String getTopTitle() {
        return "帮助";
    }

    @Override
    public int getContentId() {
        return R.layout.fragment_help;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.help_identify_ll).setOnClickListener(this);
        view.findViewById(R.id.help_author_ll).setOnClickListener(this);
        view.findViewById(R.id.help_message_ll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_identify_ll:
                jumpToActivity(IdentifyActivity.class);
                break;
            case R.id.help_author_ll:
                jumpToActivity(AuthorActivity.class);
                break;
            case R.id.help_message_ll:
                jumpToActivity(MessageActivity.class);
                break;
        }
    }

    private void jumpToActivity(Class<?> clz) {
        Intent intent = new Intent(getActivity(), clz);
        startActivity(intent);
        return;
    }
}
