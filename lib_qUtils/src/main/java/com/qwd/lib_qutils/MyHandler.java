package com.qwd.lib_qutils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


public class MyHandler extends Handler {

    // 回调接口
    private IMyHandlerListener listener;

    // 弱引用实例
    private WeakReference<Activity> mActivityReference;
    // 是否引用的弱引用实例
    private boolean isReference = false;

    public MyHandler(IMyHandlerListener listener) {
        this.listener = listener;
        this.isReference = false;
    }

    /**
     * 初始化弱引用
     */
    public MyHandler(Activity activity, IMyHandlerListener listener) {
        mActivityReference= new WeakReference<Activity>(activity);
        this.listener = listener;
        this.isReference = true;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (isReference){
            if (mActivityReference.get() != null && listener != null) {
                if (msg.what == 999999999){
                    listener.onWhat(-1, null, msg.getData().getString("str"));
                }else {
                    listener.onWhat(msg.what, msg.getData(), null);
                }
            }
        }else {
            if (msg.what == 999999999){
                listener.onWhat(-1, null, msg.getData().getString("str"));
            }else {
                listener.onWhat(msg.what, msg.getData(), null);
            }
        }


    }

    /**
     * 移除 messageQueue 中相同What 的消息
     */
    public void removeWhat(int what){
        removeMessages(what);
    }

    /**
     *  传递标签
     */
    public synchronized void setWhat(int what){
        sendMessage(what, null, false);
    }

    /**
     * 传递标签及传值
     */
    public synchronized void setMessage(int what, Bundle bundle){
        sendMessage(what, bundle, true);
    }

    public synchronized void setStringMessage(int what, String key, String value){
        if (value == null) value = "";
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        sendMessage(what, bundle, true);
    }

    public synchronized void setIntMessage(int what, String key, int value){
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        sendMessage(what, bundle, true);
    }

    public void setString(String str){
        Bundle bundle = new Bundle();
        bundle.putString("str", str);
        setMessage(999999999, bundle);
    }

    public Bundle getBundleString(String key, String value){
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }

    public Bundle getBundleInt(String key, int value){
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        return bundle;
    }


    /**
     * @param isObject      是否包含对象 true = 有对象，  false = 无对象
     */
    private synchronized void sendMessage(int what, Bundle bundle, boolean isObject){
        if (isObject){
            Message message = new Message();
            message.what = what;
            message.setData(bundle);
            sendMessage(message);
        }else {
            sendEmptyMessage(what);
        }
    }

    /**
     * 延时执行
     * @param what          code
     * @param delayMillis   毫秒
     */
    public void postDelayed(int what, long delayMillis){
        this.postDelayed(() -> {
            if (listener != null)listener.onWhat(what, null, null);
        }, delayMillis);
    }

    /**
     * Handler 接口
     */
    public interface IMyHandlerListener{
        void onWhat(int what, Bundle bundle, String str);
    }
}
