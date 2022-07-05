package com.qwd.lib_qutils;

import android.content.Context;

/**
 * 请在 application 中初始化
 */
public class QUtilsMain {
    public static Context context;

    public static void init(Context mContext){
        context = mContext;
    }
}
