package com.meitu.signaltransmissionproject.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;

/**
 * @author lyd
 *         弹框工具栏，封装常用形式的弹框
 */
public class DialogUtil {
    /**
     * 系统弹窗
     *
     * @param context         context
     * @param title           标题头
     * @param message         内容
     * @param positive        确定
     * @param negative        取消
     * @param onClickListener 回调
     */
    public static AlertDialog showSystemDialog(@NonNull Context context, String title, String message, String positive, String neutral, String negative, @NonNull final DialogInterface.OnClickListener onClickListener) {
        AlertDialog dialog = createSystemDialog(context, title, message, positive, neutral, negative, onClickListener);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return null;
            }
            dialog.show();
            return dialog;
        }
        return null;
    }

    /**
     * 创建AlertDialog系统弹窗
     *
     * @param context         context
     * @param title           标题头
     * @param message         内容
     * @param positive        确定
     * @param negative        取消
     * @param onClickListener 回调
     */

    public static AlertDialog createSystemDialog(@NonNull Context context, String title, String
            message, String positive, String neutual, String negative,
                                                 @NonNull final DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        if (message != null) {
            dialog.setMessage(message);
        }
        if (positive != null) {
            dialog.setPositiveButton(positive, onClickListener);
        }
        if (negative != null) {
            dialog.setNegativeButton(negative, onClickListener);
        }
        if (neutual != null) {
            dialog.setNeutralButton(neutual, onClickListener);
        }
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    dialog.dismiss();
                    onClickListener.onClick(dialog, Dialog.BUTTON_NEGATIVE);
                }
                return false;
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onClickListener.onClick(dialog, Dialog.BUTTON_NEGATIVE);
            }
        });
        return dialog.create();
    }
}
