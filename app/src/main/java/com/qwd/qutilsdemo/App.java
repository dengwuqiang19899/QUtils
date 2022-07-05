package com.qwd.qutilsdemo;

import android.app.Application;

import com.qwd.lib_qutils.QUtilsMain;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        QUtilsMain.init(this);
    }
}
