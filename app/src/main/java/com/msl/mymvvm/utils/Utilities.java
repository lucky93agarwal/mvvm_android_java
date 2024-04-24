package com.msl.mymvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.msl.mymvvm.R;

public class Utilities {
    public static final String TAG = Utilities.class.getSimpleName();
    public static void hideKeyboard(Context ctx) {
        if (ctx != null && ctx.getSystemService(Activity.INPUT_METHOD_SERVICE) != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) ctx
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(((Activity) ctx).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                Logger.e(TAG, e.toString());
            }

        }
    }
    public static boolean nullCheck(EditText editText) {
        return !(editText != null && !editText.getText().toString().isEmpty());
    }
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = null;
        NetworkInfo activeNetworkInfo;
        try {
            connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            // Toast.makeText(ctx, Constants.toastInternetError, Toast.LENGTH_SHORT).show();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
        } catch (NullPointerException e) {
            //Toast.makeText(ctx, Constants.toastInternetError, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static Drawable getDrawable(Context ctx, int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return ctx.getResources().getDrawable(i, null);
        else
            return ctx.getResources().getDrawable(i);
    }

    public static Drawable getDrawable(Context context, String resourceName) {
        try {
            int resourceId = context.getResources().getIdentifier(resourceName,
                    "drawable", context.getPackageName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return context.getResources().getDrawable(resourceId, null);
            } else {
                return context.getResources().getDrawable(resourceId);
            }
        } catch (Exception e) {
            return context.getDrawable(R.drawable.ic_launcher_background);
        }
    }

    public static void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}
