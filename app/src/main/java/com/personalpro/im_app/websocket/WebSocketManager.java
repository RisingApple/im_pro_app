package com.personalpro.im_app.websocket;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.bean.websocket.ChatResponse;
import com.personalpro.im_app.util.GSONUtil;
import com.personalpro.im_app.util.LogUtil;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class WebSocketManager {

    private static final String TAG = WebSocketManager.class.getSimpleName();

    private static WebSocketManager instance = null;
    private static WebSocketClient client = null;
    private WebSocketListener webSocketListener = null;

    private static URI uri;

    private WebSocketManager() {
        getWebSocketClient();
    }

    public void setWebSocketListener(WebSocketListener webSocketListener) {
        this.webSocketListener = webSocketListener;
    }

    public static WebSocketManager newInstance(String username) {
        if (instance == null) {
            synchronized (WebSocketManager.class) {
                if (instance == null) {
                    LogUtil.i(TAG,"uri is "+Constant.WEB_SOCKET_ADDRESS+username);
                    uri = URI.create(Constant.WEB_SOCKET_ADDRESS+username);
                    instance = new WebSocketManager();
                }
            }
        }
        return instance;
    }

    public WebSocketClient getWebSocketClient() {

        if (client == null) {
            client = new WebSocketClient(uri) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    if (webSocketListener != null) {
                        webSocketListener.onOpen();
                    }
                }

                @Override
                public void onMessage(String message) {
                    LogUtil.i(TAG,message);
                    if (webSocketListener != null) {
                        webSocketListener.onMessage(GSONUtil.newInstance().fromJson(message, ChatResponse.class));
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    if (webSocketListener != null) {
                        webSocketListener.onClose();
                    }
                }

                @Override
                public void onError(Exception ex) {
                    if (webSocketListener != null) {
                        webSocketListener.onError();
                    }
                }
            };
        }
        return client;
    }

    public void connect() {
        try {
            client.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }

    public void reconnect() {
        try {
            client.reconnectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String s) {
        if (client != null && client.isOpen()) {
            client.send(s);
        }
    }

}
