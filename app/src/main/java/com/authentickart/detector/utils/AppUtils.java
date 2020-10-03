package com.authentickart.detector.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by arif on 30/7/17.
 */

public final class AppUtils {

    private static DecimalFormat mNoTrailingZerosFormatter = new DecimalFormat("0.#");

    private AppUtils() {

    }

    public static float dp2px(Context context, float dp) {
        return dp2px(context.getResources(), dp);
    }

    /**
     * This method is used to convert dp to pixel
     *
     * @param resources resources
     * @param dp        dp value
     * @return pixel value of corresponding dp value
     */
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static float getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static boolean isEmpty(Map<?, ?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(List<?> collections) {
        return collections == null || collections.isEmpty();
    }

    public static boolean isEmailValid(CharSequence email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isTextValid(CharSequence text, int minCharThreshold) {
        return text != null && text.length() >= minCharThreshold;
    }

}
