package com.meitu.signaltransmissionproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author lyd
 */
public class MultiFocusEditText extends EditText {
    public MultiFocusEditText(Context context) {
        this(context, null);
    }

    public MultiFocusEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiFocusEditText(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFocused()) {
                    clearFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) context
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                } else {
                    requestFocus();
                }
            }
        });
    }

}
