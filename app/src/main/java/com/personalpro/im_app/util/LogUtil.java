package com.personalpro.im_app.util;

import android.util.Log;

public class LogUtil {

    public static boolean log = true;

    public void init(boolean isLog){
        log = isLog;
    }

    public static void i(String tagName,String message){
        if(!log) return;
        Log.i(tagName,message);
    }

    public static void e(String tagName,String message){
        if(!log) return;
        Log.e(tagName,message);
    }

    public static void d(String tagName,String message){
        if(!log) return;
        Log.d(tagName,message);
    }

    public static void v(String tagName,String message){
        if(!log) return;
        Log.v(tagName,message);
    }

    public static void w(String tagName,String message){
        if(!log) return;
        Log.w(tagName,message);
    }

}
