package org.geek.universalimageloaderexample.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {


    /**
     * show short toast
     *
     * @param context
     * @param text
     */
    public static void showToastShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * show long toast
     *
     * @param context
     * @param text
     */
    public static void showToastLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }





}
