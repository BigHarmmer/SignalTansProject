package com.meitu.signaltransmissionproject.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.meitu.signaltransmissionproject.MyApplication;

/**
 * @author lyd
 */
public class MessageReceiver extends BroadcastReceiver {

    interface MessageCallback {
        void onReceiveMessage(String[] message);
    }

    public static final String MESSAGE_ACTION = "message_action";

    private static final String EXTRA_MESSAGE = "extra_message";

    private MessageCallback mMessageCallback;

    public MessageReceiver(MessageCallback callback) {
        mMessageCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if (!intent.getAction().equals(MESSAGE_ACTION)) {
            return;
        }
        String[] message = intent.getStringArrayExtra(EXTRA_MESSAGE);
        if (message == null || message.length != 4) {
            return;
        }
        mMessageCallback.onReceiveMessage(message);
    }

    /**
     * 通过该方法发送广播
     *
     * @param message 要发送的消息数组
     */
    public static void sendMessage(String[] message) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.setAction(MESSAGE_ACTION);
        MyApplication.getInstance().sendBroadcast(intent);
    }
}
