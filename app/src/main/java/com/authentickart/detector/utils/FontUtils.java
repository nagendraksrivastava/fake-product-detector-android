package com.authentickart.detector.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.authentickart.detector.R;

/**
 * Created by arif on 2/8/17.
 */

public final class FontUtils {

    private static final String DEFAULT_FONT = "Quicksand-Regular.otf";

    private FontUtils() {

    }

    public static void applyCustomFont(TextView textview, Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFont);
        String font;
        if (a == null) {
            font = DEFAULT_FONT;
        } else {
            font = a.getString(R.styleable.CustomFont_font);
            if (font == null) {
                font = DEFAULT_FONT;
            }
        }
        setCustomFont(textview, font, context);
        if (a != null) {
            a.recycle();
        }
    }

    /**
     * Sets a font on a textview
     *
     * @param textview
     * @param font
     * @param context
     */
    public static void setCustomFont(TextView textview, String font, Context context) {
        if (font == null) {
            return;
        }
        Typeface tf = FontCache.getTypeface(font, context);
        if (tf != null) {
            textview.setTypeface(tf);
        }
    }
}
