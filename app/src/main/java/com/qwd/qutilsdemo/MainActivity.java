package com.qwd.qutilsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qwd.lib_qutils.MyHandler;

public class MainActivity extends AppCompatActivity implements MyHandler.IMyHandlerListener{
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler(this);
    }


    @Override
    public void onWhat(int what, Bundle bundle, String str) {

    }
}