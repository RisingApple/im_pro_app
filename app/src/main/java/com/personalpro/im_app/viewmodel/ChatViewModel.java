package com.personalpro.im_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.personalpro.im_app.repository.ChatRepository;
import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.room.entity.ChatItem;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private ChatRepository chatRepository;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatRepository = ChatRepository.newInstance();
    }

    public MutableLiveData<List<ChatItem>> getChatList(String fromUsername,String toUserName){
        return chatRepository.getChatItems(fromUsername,toUserName);
    }

    public void insetChatItem(ChatItem chatItem){
        chatRepository.insertChatCache(chatItem);
    }

    public MutableLiveData<List<ChatCache>> getCacheChat(String fromUsername){
        return chatRepository.getChatCaches(fromUsername);
    }

    public void insetChatCache(ChatCache chatCache){
        chatRepository.insertChatCache(chatCache);
    }

}
