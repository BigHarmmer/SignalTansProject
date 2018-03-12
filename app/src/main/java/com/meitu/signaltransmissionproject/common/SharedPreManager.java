package com.meitu.signaltransmissionproject.common;

import java.util.List;

/**
 * @author lyd
 */
public class SharedPreManager extends AbsSharedPrefManager {
    public static final String SP_NAME = "history_sp";

    private static final String KEY_IP_HISTORY = "KEY_IP_HISTORY";
    private static final String KEY_PORT_HISTORY = "key_port_history";
    private static final String KEY_IDENTIFY_HISTORY = "key_identify_history";

    private static final String KEY_MESSAGE_BPK = "key_message_bpk";
    private static final String KEY_MESSAGE_SPK = "key_message_spk";
    private static final String KEY_MESSAGE_BOLL = "key_message_boll";
    private static final String KEY_MESSAGE_DT = "key_message_dt";

    public static void setIpHistory(String ip) {
        putString(KEY_IP_HISTORY, ip);
    }

    public static String getIpHistory() {
        return getString(KEY_IP_HISTORY, "");
    }

    public static void setPortHistory(String port) {
        putString(KEY_PORT_HISTORY, port);
    }

    public static String getPortHistory() {
        return getString(KEY_PORT_HISTORY, "");
    }

    public static void setIdentifyHistory(String identify) {
        putString(KEY_IDENTIFY_HISTORY, identify);
    }

    public static String getIdentifyHistory() {
        return getString(KEY_IDENTIFY_HISTORY, "");
    }

    public static String getBPK() {
        return getString(KEY_MESSAGE_BPK, "");
    }

    public static String getSPK() {
        return getString(KEY_MESSAGE_SPK, "");
    }

    public static String getBOLL() {
        return getString(KEY_MESSAGE_BOLL, "");
    }

    public static String getDT() {
        return getString(KEY_MESSAGE_DT, "");
    }

    public static void setBPK(String str) {
        putString(KEY_MESSAGE_BPK, str);
    }

    public static void setSPK(String str) {
        putString(KEY_MESSAGE_SPK, str);
    }

    public static void setBOLL(String str) {
        putString(KEY_MESSAGE_BOLL, str);
    }

    public static void setDT(String str) {
        putString(KEY_MESSAGE_DT, str);
    }


}
