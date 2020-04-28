package com.personalpro.im_app.util;

import com.google.gson.Gson;

public class GSONUtil {

    private static final String TAG = GSONUtil.class.getSimpleName();

    private static GSONUtil instance;
    private static Gson gson;

    private GSONUtil(){
        gson = new Gson();
    }

    public static GSONUtil newInstance(){
        if(instance == null){
            synchronized (GSONUtil.class){
                if(instance == null){
                    instance = new GSONUtil();
                }
            }
        }
        return instance;
    }

    public <T> T fromJson(String json,Class<T> clz){
        try {
            return gson.fromJson(json,clz);
        }catch (Exception e){
            LogUtil.i(TAG,"Data not standardized");
            return null;
        }

    }

    public <T> String toJson(Object object){
        return gson.toJson(object);
    }



}
