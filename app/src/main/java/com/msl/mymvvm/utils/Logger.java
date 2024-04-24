package com.msl.mymvvm.utils;
import com.msl.mymvvm.BuildConfig;
import android.util.Log;

public class Logger {

    public static void d(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.d("HDFC :: " + TAG, "null");
            } else {
                Log.d("HDFC :: " + TAG, message);
            }
        }
    }

    public static void v(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.v("HDFC :: " + TAG, "null");
            } else {
                Log.v("HDFC :: " + TAG, message);
            }
        }
    }

    public static void e(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.e("HDFC :: " + TAG, "null");
            } else {
                Log.e("HDFC :: " + TAG, message);
            }
        }
    }

    public static void e(String TAG, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            if (throwable == null) {
                Log.e("HDFC :: " + TAG, "null");
            } else {
                Log.e("HDFC :: " + TAG, "Throwable : ", throwable);
            }
        }
    }

    public static void w(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.w("HDFC :: " + TAG, "null");
            } else {
                Log.w("HDFC :: " + TAG, message);
            }
        }
    }

    public static void i(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.i("HDFC :: " + TAG, "null");
            } else {
                Log.i("HDFC :: " + TAG, message);
            }
        }
    }

    public static void wtf(String TAG, String message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.wtf("HDFC :: " + TAG, "null");
            } else {
                Log.wtf("HDFC :: " + TAG, message);
            }
        }
    }

    public static void wtf(String TAG, Throwable message) {
        if (BuildConfig.DEBUG) {
            if (message == null) {
                Log.wtf("HDFC :: " + TAG, "null");
            } else {
                Log.wtf("HDFC :: " + TAG, message);
            }
        }
    }
}
