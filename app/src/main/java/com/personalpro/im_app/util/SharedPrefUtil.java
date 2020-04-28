package com.personalpro.im_app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SharedPrefUtil {

    private static final String FILE_NAME = "data.cfg";

    private static Context context;
    private static SharedPrefUtil instance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPrefUtil() {
        init();
    }

    public static SharedPrefUtil getInstance(Context ctx) {
        if(instance == null){
            synchronized(SharedPrefUtil.class){
                if(instance == null){
                    context = ctx;
                    instance = new SharedPrefUtil();
                }
            }
        }
        return instance;
    }

    private void init() {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public <T> void putValue(String key, T value) {
        if (TextUtils.isEmpty(key) || value == null) {
            throw new IllegalArgumentException("Class:" + this.getClass().getSimpleName() + " key or value could't be null!");
        }
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, (String) value);
        }
        editor.commit();
    }

    public <T> T getValue(String key, Class<T> clazz) {
        if (TextUtils.isEmpty(key) || clazz == null) {
            throw new IllegalArgumentException("Class:" + this.getClass().getSimpleName() + " key or class type could't be null!");
        }
        Object obj;
        if (clazz == Integer.class) {
            obj = sharedPreferences.getInt(key, 0);
        } else if (clazz == Long.class) {
            obj = sharedPreferences.getLong(key, 0);
        } else if (clazz == Float.class) {
            obj = sharedPreferences.getFloat(key, 0);
        } else if (clazz == Boolean.class) {
            obj = sharedPreferences.getBoolean(key, false);
        } else {
            obj = sharedPreferences.getString(key, "");
        }
        return (T)obj;
    }
}
