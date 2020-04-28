package com.personalpro.im_app.bean.websocket;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatResponse implements Parcelable {

    private String eventType;

    private ChatMessage data;

    public ChatResponse(String eventType, ChatMessage data) {
        this.eventType = eventType;
        this.data = data;
    }

    private ChatResponse(Builder builder) {
        setEventType(builder.eventType);
        setData(builder.data);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public ChatMessage getData() {
        return data;
    }

    public void setData(ChatMessage data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.eventType);
        dest.writeParcelable(this.data, flags);
    }

    protected ChatResponse(Parcel in) {
        this.eventType = in.readString();
        this.data = in.readParcelable(ChatMessage.class.getClassLoader());
    }

    public static final Creator<ChatResponse> CREATOR = new Creator<ChatResponse>() {
        @Override
        public ChatResponse createFromParcel(Parcel source) {
            return new ChatResponse(source);
        }

        @Override
        public ChatResponse[] newArray(int size) {
            return new ChatResponse[size];
        }
    };


    public static final class Builder {
        private String eventType;
        private ChatMessage data;

        public Builder() {
            if(data == null){
                data = new ChatMessage();
            }
        }

        public Builder eventType(String val) {
            eventType = val;
            return this;
        }

        public Builder data(ChatMessage val) {
            data = val;
            return this;
        }

        public Builder groupChat(boolean groupChat) {
            data.setGroupChat(groupChat);
            return this;
        }

        public Builder outside(boolean outside) {
            data.setOutside(outside);
            return this;
        }

        public Builder groupChatId(int groupChatId) {
            data.setGroupChatId(groupChatId);
            return this;
        }

        public Builder fromUserName(String fromUserName) {
            data.setFromUserName(fromUserName);
            return this;
        }

        public Builder message(String message) {
            data.setMessage(message);
            return this;
        }

        public Builder toUserName(String toUserName) {
            data.setToUserName(toUserName);
            return this;
        }

        public ChatResponse build() {
            return new ChatResponse(this);
        }
    }
}
