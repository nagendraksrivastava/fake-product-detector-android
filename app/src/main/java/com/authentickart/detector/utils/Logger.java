package com.authentickart.detector.utils;

import android.text.TextUtils;
import android.util.Log;

import com.authentickart.detector.BuildConfig;

/**
 * Created by arif on 23/7/17.
 */

public class Logger {
    //A tag for stacktraces
    private static final String STACK_TRACE_TAG = "stack_trace_log";
    private static boolean isLoggingEnabled;

    public static void init() {
        isLoggingEnabled = BuildConfig.DEBUG;
    }

    public static void d(String tag, String msg) {
        if (isLoggingEnabled && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLoggingEnabled && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLoggingEnabled && !TextUtils.isEmpty(msg)) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLoggingEnabled && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void e(final String tag, String msg, Throwable cause) {
        if (isLoggingEnabled && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg, cause);
        }
    }

    public static void printStackTrace(Throwable t) {
        if (isLoggingEnabled && t != null) {
            Log.e(STACK_TRACE_TAG, Log.getStackTraceString(t) != null ? Log.getStackTraceString(t) : "Null stack trace!");
        }
    }
}
