package com.luosu.utils;

import android.util.Log;

/**
 * Created by User on 2017/6/12.
 */

public class LogUtils {
    private static final boolean LOGFLAG = true;

    public static void i(String tag, String msg) {
        if (LOGFLAG) {
            Log.i(tag, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (LOGFLAG) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LOGFLAG) {
            Log.d(tag, msg);
        }
    }
}
