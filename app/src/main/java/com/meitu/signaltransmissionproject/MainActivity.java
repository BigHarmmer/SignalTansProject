package com.meitu.signaltransmissionproject;


import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.meitu.signaltransmissionproject.config.ConfigFragment;
import com.meitu.signaltransmissionproject.help.HelpFragment;
import com.meitu.signaltransmissionproject.message.MessageFragment;
import com.meitu.signaltransmissionproject.message.MessageReceiver;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ConfigFragment mConfigFragment;
    private HelpFragment mHelpFragment;
    private MessageFragment mMessageFragment;

    private Fragment mFragmentFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mConfigFragment = new ConfigFragment();
        mHelpFragment = new HelpFragment();
        mMessageFragment = new MessageFragment();
        BottomBar bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab_message:
                        replaceFragment(mMessageFragment);
                        break;
                    case R.id.tab_config:
                        replaceFragment(mConfigFragment);
                        break;
                    case R.id.tab_help:
                        replaceFragment(mHelpFragment);
                        break;
                }
            }
        });

    }


    /**
     * 替换容器中的fragment（实际是隐藏其他fragment）
     *
     * @param to 想要替换的fragment
     */
    private void replaceFragment(Fragment to) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (mFragmentFrom == null) {
            transaction.add(R.id.fragment_container, mMessageFragment);
            transaction.add(R.id.fragment_container, mConfigFragment);
            transaction.add(R.id.fragment_container, mHelpFragment);
            transaction.hide(mMessageFragment);
            transaction.hide(mConfigFragment);
            transaction.hide(mHelpFragment);
            transaction.show(to);
        } else {
            transaction.hide(mFragmentFrom);
            transaction.show(to);
        }
        mFragmentFrom = to;
        transaction.commit();
    }

}
