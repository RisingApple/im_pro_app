package com.personalpro.im_app.repository;

import androidx.lifecycle.MutableLiveData;

import com.personalpro.im_app.room.database.AppDatabase;
import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.room.entity.ChatItem;
import com.personalpro.im_app.util.ThreadTask;

import java.util.List;

public class ChatRepository {

    private static ChatRepository instance;

    private ChatRepository(){

    }

    public static ChatRepository newInstance(){
        if(instance == null){
            synchronized (ChatRepository.class){
                if(instance == null){
                    instance = new ChatRepository();
                }
            }
        }
        return instance;
    }

    public MutableLiveData<List<ChatItem>> getChatItems(String fromUsername,String toUserName){
        MutableLiveData<List<ChatItem>> data = new MutableLiveData<>();
        ThreadTask.getInstance().executorDBThread(() -> data.postValue(AppDatabase.getInstance().chatDao().loadChatItemByUsername(fromUsername,toUserName)),1);
        return data;
    }

    public void insertChatCache(ChatItem chatItem){
        ThreadTask.getInstance().executorDBThread(() -> AppDatabase.getInstance().chatDao().insertCharItem(chatItem),1);
    }

    public MutableLiveData<List<ChatCache>> getChatCaches(String fromUsername){
        MutableLiveData<List<ChatCache>> data = new MutableLiveData<>();
        ThreadTask.getInstance().executorDBThread(() -> data.postValue(AppDatabase.getInstance().chatDao().loadChatCaCheByUsername()),1);
        return data;
    }

    public void insertChatCache(ChatCache chatCache){
        ThreadTask.getInstance().executorDBThread(() -> AppDatabase.getInstance().chatDao().insetCaCheChat(chatCache),1);
    }

}
