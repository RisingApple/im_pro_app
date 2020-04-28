package com.personalpro.im_app.websocket;

import com.personalpro.im_app.bean.websocket.ChatMessage;
import com.personalpro.im_app.bean.websocket.ChatResponse;

public interface WebSocketListener {

    void onOpen();

    void onMessage(ChatResponse chatResponse);

    void onClose();

    void onError();

}
