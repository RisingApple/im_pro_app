package com.personalpro.im_app.http.interceptor;

import android.util.Log;
import android.webkit.WebSettings;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.IMApplication;
import com.personalpro.im_app.util.SharedPrefUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SessionIdInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Log.i("request_url",request.url().url().toString());
        String userAgent = WebSettings.getDefaultUserAgent(IMApplication.getContext());
        String sessionId = SharedPrefUtil.getInstance(IMApplication.getContext()).getValue(Constant.SESSION_KEY,String.class);
        if(sessionId!=null&&sessionId.length()>0){
           request = request.newBuilder().addHeader("Cookie","JSESSIONID="+sessionId).build();
        }
        Response response = chain.proceed(request);
        Log.i("response",response.body().string());
        return chain.proceed(request);
    }

}
