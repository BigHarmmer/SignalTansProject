package com.meitu.signaltransmissionproject.common;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * @author lyd
 *         获取设备信息的工具类
 */
public class DeviceIdUtil {
    /**
     * @return IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = null;
        if (Build.VERSION.SDK_INT >= 26) {
            deviceId = manager.getImei();
        } else {
            deviceId = manager.getDeviceId();
        }
        return !TextUtils.isEmpty(deviceId) ? deviceId : "1";
    }

}
