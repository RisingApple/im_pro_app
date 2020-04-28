package com.personalpro.im_app.bean.websocket;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ChatMessage implements Parcelable {

    @SerializedName("isgroupchat")
    private boolean isGroupChat;

    @SerializedName("groupchatid")
    private int groupChatId;

    @SerializedName("fromusername")
    private String fromUserName;

    @SerializedName("message")
    private String message;

    @SerializedName("tousername")
    private String toUserName;

    private boolean outside = false;


    public ChatMessage(boolean isGroupChat, int groupChatId, String fromUserName, String message, String toUserName) {
        this.isGroupChat = isGroupChat;
        this.groupChatId = groupChatId;
        this.fromUserName = fromUserName;
        this.message = message;
        this.toUserName = toUserName;
    }

    private ChatMessage(Builder builder) {
        this.isGroupChat = builder.isGroupChat;
        this.groupChatId = builder.groupChatId;
        this.fromUserName = builder.fromUserName;
        this.message = builder.message;
        this.toUserName = builder.toUserName;
        this.outside = builder.outside;
    }

    public static Builder newChatMessage() {
        return new Builder();
    }

    public boolean isOutside() {
        return outside;
    }

    public void setOutside(boolean outside) {
        this.outside = outside;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        isGroupChat = groupChat;
    }

    public int getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(int groupChatId) {
        this.groupChatId = groupChatId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isGroupChat ? (byte) 1 : (byte) 0);
        dest.writeInt(this.groupChatId);
        dest.writeString(this.fromUserName);
        dest.writeString(this.message);
        dest.writeString(this.toUserName);
    }

    public ChatMessage() {

    }

    protected ChatMessage(Parcel in) {
        this.isGroupChat = in.readByte() != 0;
        this.groupChatId = in.readInt();
        this.fromUserName = in.readString();
        this.message = in.readString();
        this.toUserName = in.readString();
    }

    public static final Parcelable.Creator<ChatMessage> CREATOR = new Parcelable.Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel source) {
            return new ChatMessage(source);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };


    public static final class Builder {
        private boolean isGroupChat;
        private int groupChatId;
        private String fromUserName;
        private String message;
        private String toUserName;
        private boolean outside;

        public Builder() {
        }

        public ChatMessage build() {
            return new ChatMessage(this);
        }

        public Builder isGroupChat(boolean isGroupChat) {
            this.isGroupChat = isGroupChat;
            return this;
        }

        public Builder groupChatId(int groupChatId) {
            this.groupChatId = groupChatId;
            return this;
        }

        public Builder fromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder toUserName(String toUserName) {
            this.toUserName = toUserName;
            return this;
        }

        public Builder outside(boolean outside) {
            this.outside = outside;
            return this;
        }
    }
}
