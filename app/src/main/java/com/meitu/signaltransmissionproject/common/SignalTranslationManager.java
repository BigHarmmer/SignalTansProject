package com.meitu.signaltransmissionproject.common;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;

import com.meitu.signaltransmissionproject.base.ConnectCallback;
import com.meitu.signaltransmissionproject.message.MessageReceiver;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author lyd
 *         信号传输中心管理，单例
 */
public class SignalTranslationManager {

    private static final String TAG = "SignalTranslationManage";

    private static final String REPSTATUS_OK = "[REPSTATUS:OK]";
    private static final String REPTYPE_AUTH_0 = "[REPTYPE:AUTH:0]";
    private String[] request_list = new String[]{"[REQTYPE:DATA:BPK]", "[REQTYPE:DATA:SPK]", "[REQTYPE:DATA:BOLL]", "[REQTYPE:DATA:DT]"};

    private static final SignalTranslationManager sTranslationManager = new SignalTranslationManager();

    private Thread mThread;
    private String mUrl;
    private String mId;
    private String mIMEI;
    private String mMEID;

    private SignalTranslationManager() {

    }

    public static SignalTranslationManager getInstance() {
        return sTranslationManager;
    }

    /**
     * 设置请求参数
     */
    public void setRequestMessage(String baseUrl, String port, String id, String IMEI, String MEID) {
        mUrl = "tcp://" + baseUrl + ":" + port;
        mId = id;
        mIMEI = IMEI;
        mMEID = MEID;
    }

    /**
     * 开始连接
     */
    public void startConnect(ConnectCallback callback) {
        if (mThread == null || !mThread.isAlive()) {
            createThread(callback);
        }
        if (isParamsLegal()) {
            mThread.start();
        }
    }

    /**
     * 停止连接
     */
    public void stopConnect() {
        if (mThread != null) {
            mThread.interrupt();
        }
    }


    private void createThread(final ConnectCallback callback) {
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ZMQ.Socket socket = getSocketInstance();
                String request = MD5Util.getMD5("[IMEI:" + mIMEI + "_MEID:" + mMEID + "_USERID:" + mId + "]");
                if (request == null) {
                    return;
                }
                socket.send(request, ZMQ.SNDMORE);
                socket.send("[REQTYPE:AUTH]", 0);
                String first = recv(socket);
                String second = recv(socket);
                Log.i(TAG, first + "   " + second);
                if (first != null && second != null && first.equals(REPSTATUS_OK) && second.equals(REPTYPE_AUTH_0)) {
                    if (callback != null) {
                        callback.onConnect();
                    }
                    pollMessage(socket, request, callback);
                } else {
                    if (callback != null) {
                        callback.onDisconnect();
                    }
                }
            }
        });
    }

    /**
     * 轮询请求数据
     */
    @WorkerThread
    private void pollMessage(ZMQ.Socket socket, String request, ConnectCallback callback) {
        while (!mThread.isInterrupted()) {
            try {
                String[] message = new String[request_list.length];
                for (int i = 0; i < request_list.length; i++) {
                    String req_str = request_list[i];
                    socket.send(request, ZMQ.SNDMORE);
                    socket.send(req_str, 0);
                    String first = recv(socket);
                    if (TextUtils.isEmpty(first) || !first.equals(REPSTATUS_OK)) {
                        continue;
                    }
                    String second = recv(socket);
                    message[i] = second;
                    Log.i(TAG, second);
                }
                MessageReceiver.sendMessage(message);
                Thread.sleep(5000);
            } catch (Exception e) {
                mThread.interrupt();
                e.printStackTrace();
            }
        }
        if (callback != null) {
            callback.onDisconnect();
        }
    }

    /**
     * 获取socket对象
     */
    @NonNull
    private ZMQ.Socket getSocketInstance() {
        ZMQ.Socket socket;
        ZContext zContext = new ZContext();
        socket = zContext.createSocket(ZMQ.REQ);
        socket.setReceiveTimeOut(5000);
        socket.connect(mUrl);
        return socket;
    }

    /**
     * 判断请求参数是否符合要求
     *
     * @return
     */
    private boolean isParamsLegal() {
        if (TextUtils.isEmpty(mUrl) || TextUtils.isEmpty(mIMEI) || TextUtils.isEmpty(mMEID) || TextUtils.isEmpty(mId)) {
            return false;
        }
        String regString;
        //检查id格式
        regString = "[a-f0-9A-F]{16}";
        if (!Pattern.matches(regString, mId)) {
            return false;
        }
        //检查imei格式
        regString = "[0-9]{15}";
        if (!Pattern.matches(regString, mIMEI)) {
            return false;
        }
        //检查MEID
        regString = "[a-f0-9A-F]{14}";
        if (!Pattern.matches(regString, mMEID)) {
            return false;
        }
        return true;
    }

    public String recv(ZMQ.Socket socket) {
        return dropLastChar(socket.recvStr());
    }

    /**
     * 去掉字符串最后一位
     *
     * @param str 要截取的字符串
     * @return 截取后的字符串
     */
    private String dropLastChar(String str) {
        return str == null ? null : str.substring(0, str.length() - 1);
    }

}
