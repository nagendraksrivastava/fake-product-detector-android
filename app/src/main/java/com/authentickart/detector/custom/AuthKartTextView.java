package com.authentickart.detector.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.authentickart.detector.utils.FontUtils;

/**
 * Created by arif on 2/8/17.
 */

public class AuthKartTextView extends AppCompatTextView {

    public AuthKartTextView(Context context) {
        super(context);
        FontUtils.applyCustomFont(this, context, null);
    }

    public AuthKartTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        FontUtils.applyCustomFont(this, context, attrs);
    }

    public AuthKartTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FontUtils.applyCustomFont(this, context, attrs);
    }
}
