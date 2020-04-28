package com.personalpro.im_app.http;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.http.interceptor.SessionIdInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static HttpClient instance;
    private static Retrofit retrofitService;
    private static OkHttpClient okHttpClient;
    private static final int TIME_OUT = 10;
    private static final String BASE_URL ="http://192.168.1.154:8080";

    private HttpClient(){

    }

    public static HttpClient newInstance(){
        if(instance == null){
            synchronized (HttpClient.class){
                if(instance == null){
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    private static OkHttpClient getOkHttpClient(){
        if(okHttpClient == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
            builder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
            builder.connectTimeout(TIME_OUT,TimeUnit.SECONDS);
            builder.addInterceptor(new SessionIdInterceptor());
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    public Retrofit getService(){
        if(retrofitService == null){
            retrofitService = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitService;
    }

}
