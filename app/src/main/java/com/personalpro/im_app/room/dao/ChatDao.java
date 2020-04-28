package com.personalpro.im_app.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.room.entity.ChatItem;

import java.util.List;

@Dao
public interface ChatDao {

    @Query("SELECT * FROM chat_item WHERE (from_user_name == :fromUsername AND to_user_name == :toUserName) OR from_user_name == :toUserName")
    List<ChatItem> loadChatItemByUsername(String fromUsername,String toUserName);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCharItem(ChatItem ...chatItems);


    @Query("SELECT * FROM chat_cache")
    List<ChatCache> loadChatCaCheByUsername();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetCaCheChat(ChatCache ...cacheChats);


    @Query("SELECT * FROM chat_cache WHERE to_user_name == :toUsername")
    List<ChatCache> getChatCaCheByToUserName(String toUsername);

    @Update()
    void updateChatCache(ChatCache ...cacheChats);

}
