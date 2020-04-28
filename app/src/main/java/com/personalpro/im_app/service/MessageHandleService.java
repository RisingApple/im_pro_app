package com.personalpro.im_app.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaCasException;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.bean.websocket.ChatMessage;
import com.personalpro.im_app.bean.websocket.ChatResponse;
import com.personalpro.im_app.room.database.AppDatabase;
import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.room.entity.ChatItem;
import com.personalpro.im_app.util.DateUtil;
import com.personalpro.im_app.util.GSONUtil;

import java.util.List;
import java.util.Objects;

public class MessageHandleService extends Service {

    private static final String TAG = MessageHandleService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BROAD_CAST_SOCKET_MESSAGE);
        intentFilter.addAction(Constant.BROAD_CAST_SEND_MESSAGE);
        registerReceiver(broadcastReceiver, intentFilter);
        Log.i(TAG, "onBind");
        return new MessageHandleBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unregisterReceiver(broadcastReceiver);
        Log.i(TAG, "onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MessageHandleBinder extends Binder {
        public MessageHandleService getService() {
            return MessageHandleService.this;
        }
    }

    private void insertChatItemToDataBase(String fromUserName, String toUserName, String message) {
        ChatItem chatItem = new ChatItem(DateUtil.currentTime(DateUtil.PATTERN_DATETIME), false, "0", fromUserName, toUserName, message);
        AppDatabase.getInstance().chatDao().insertCharItem(chatItem);
    }

    private void notifyCacheMessageOn(String fromUserName, String toUserName, String message) {
        List<ChatCache> cacheList = AppDatabase.getInstance().chatDao().getChatCaCheByToUserName(toUserName);
        if (cacheList != null && cacheList.size() > 0) {
            ChatCache chatCache = cacheList.get(0);
            chatCache.setLastString(message);
            chatCache.setCount(chatCache.getCount() + 1);
            chatCache.setLastTime(DateUtil.currentTime(DateUtil.PATTERN_DATETIME));
            AppDatabase.getInstance().chatDao().updateChatCache(chatCache);
        } else {
            ChatCache chatCache = new ChatCache();
            chatCache.setFromUserName(fromUserName);
            chatCache.setToUserName(toUserName);
            chatCache.setLastTime(DateUtil.currentTime(DateUtil.PATTERN_DATETIME));
            chatCache.setLastString(message);
            chatCache.setUser(toUserName);
            chatCache.setAvatar(fromUserName);
            chatCache.setCount(1);
            AppDatabase.getInstance().chatDao().insetCaCheChat(chatCache);
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (Objects.requireNonNull(intent.getAction())) {
                case Constant.BROAD_CAST_SOCKET_MESSAGE:
                    ChatMessage msg = intent.getParcelableExtra(Constant.CHAT_MESSAGE_KEY);
                    if (msg != null) {
                        insertChatItemToDataBase(msg.getFromUserName(), msg.getToUserName(), msg.getMessage());
                        notifyCacheMessageOn(msg.getFromUserName(), msg.getToUserName(), msg.getMessage());
                    }
                    break;

                case Constant.BROAD_CAST_SEND_MESSAGE:
                    ChatResponse chatResponse = GSONUtil.newInstance().fromJson(intent.getStringExtra(Constant.SEND_MESSAGE_KEY), ChatResponse.class);
                    notifyCacheMessageOn(chatResponse.getData().getFromUserName(), chatResponse.getData().getToUserName(), chatResponse.getData().getMessage());
                    break;

                default:
                    break;
            }
        }
    };

}
