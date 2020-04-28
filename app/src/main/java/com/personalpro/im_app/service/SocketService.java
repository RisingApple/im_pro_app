package com.personalpro.im_app.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.IMApplication;
import com.personalpro.im_app.bean.websocket.ChatMessage;
import com.personalpro.im_app.bean.websocket.ChatResponse;
import com.personalpro.im_app.util.LogUtil;
import com.personalpro.im_app.util.SharedPrefUtil;
import com.personalpro.im_app.websocket.WebSocketListener;
import com.personalpro.im_app.websocket.WebSocketManager;

public class SocketService extends Service implements WebSocketListener {

    private static final String TAG = SocketService.class.getSimpleName();
    private WebSocketManager webSocketManager;
    private Handler handler = new Handler();
    private Intent intent;

    private static final long HEART_BEAT_RATE = 10 * 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent();
        String username = SharedPrefUtil.getInstance(IMApplication.getContext()).getValue(Constant.USER_NAME_KEY,String.class);
        webSocketManager = WebSocketManager.newInstance(username);
        webSocketManager.setWebSocketListener(this);
        initBroadcast();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        new Thread(() -> {
            webSocketManager.connect();
            handler.postDelayed(heartBeatRunnable,HEART_BEAT_RATE);
        }).start();
        return new SocketBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnBind");
        unregisterReceiver(broadcastReceiver);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webSocketManager.disconnect();
    }

    @Override
    public void onOpen() {
        intent.setAction(Constant.BROAD_CAST_SOCKET_OPEN);
        sendBroadcast(intent);
        LogUtil.i(TAG,"web socket service is open");
    }

    @Override
    public void onMessage(ChatResponse chatResponse) {
        ChatMessage chatMessage = chatResponse.getData();
        chatMessage.setOutside(true);
        intent.setAction(Constant.BROAD_CAST_SOCKET_MESSAGE);
        intent.putExtra(Constant.CHAT_MESSAGE_KEY,chatMessage);
        sendBroadcast(intent);
        LogUtil.i(TAG,"web socket service is message");
    }

    @Override
    public void onClose() {
        intent.setAction(Constant.BROAD_CAST_SOCKET_CLOSE);
        sendBroadcast(intent);
        LogUtil.i(TAG,"web socket service is close");
    }

    @Override
    public void onError() {
        intent.setAction(Constant.BROAD_CAST_SOCKET_ERROR);
        sendBroadcast(intent);
        LogUtil.i(TAG,"web socket service is error");
    }

    private void sendMessage(String message){
        webSocketManager.sendMessage(message);
    }

    public class SocketBinder extends Binder{
        public SocketService getService(){
            return SocketService.this;
        }
    }


    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BROAD_CAST_SEND_MESSAGE);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void reconnect(){
        handler.removeCallbacks(heartBeatRunnable);
        new Thread(){
            @Override
            public void run() {
                try {
                    webSocketManager.reconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 心跳判断是否断连
     */
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if(webSocketManager.getWebSocketClient()!=null){
                if(webSocketManager.getWebSocketClient().isClosed()){
                    reconnect();
                }
            }else{
                webSocketManager.getWebSocketClient();
            }
            handler.postDelayed(this,HEART_BEAT_RATE);
        }
    };

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constant.BROAD_CAST_SEND_MESSAGE)){
                String message = intent.getStringExtra(Constant.SEND_MESSAGE_KEY);
                sendMessage(message);
            }
        }
    };

}
