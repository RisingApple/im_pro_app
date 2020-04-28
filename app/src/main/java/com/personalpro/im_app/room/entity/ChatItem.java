package com.personalpro.im_app.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_item",indices = {@Index(value =
        {"id"}, unique = true)})
public class ChatItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String time;

    @ColumnInfo(name = "group_chat")
    private boolean groupChat;

    @ColumnInfo(name = "group_chat_id")
    private String groupChatId;

    @ColumnInfo(name = "from_user_name")
    private String fromUserName;

    @ColumnInfo(name = "to_user_name")
    private String toUserName;

    private String message;

    public ChatItem(String time, boolean groupChat, String groupChatId, String fromUserName,String toUserName,String message) {
        this.time = time;
        this.groupChat = groupChat;
        this.groupChatId = groupChatId;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isGroupChat() {
        return groupChat;
    }

    public void setGroupChat(boolean groupChat) {
        this.groupChat = groupChat;
    }

    public String getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(String groupChatId) {
        this.groupChatId = groupChatId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatItem{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", groupChat=" + groupChat +
                ", groupChatId='" + groupChatId + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
