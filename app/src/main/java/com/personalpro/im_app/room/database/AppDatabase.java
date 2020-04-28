package com.personalpro.im_app.room.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.personalpro.im_app.IMApplication;
import com.personalpro.im_app.room.dao.ChatDao;
import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.room.entity.ChatItem;

@Database(entities = {ChatItem.class, ChatCache.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;

    public AppDatabase(){}

    public abstract ChatDao chatDao();

    public static AppDatabase getInstance(){
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = buildAppDataBase();
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildAppDataBase(){
        return Room.databaseBuilder(IMApplication.getContext(),AppDatabase.class,"im_app.db")
                .allowMainThreadQueries()
                .build();
    }


}
