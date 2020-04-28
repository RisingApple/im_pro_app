package com.personalpro.im_app;

import android.app.Application;
import android.content.Context;

import com.personalpro.im_app.util.ImageLoadUtils;

public class IMApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ImageLoadUtils.INSTANCE.init(this);
       // Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getContext(){
        return context;
    }
}
