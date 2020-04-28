package com.personalpro.im_app.room.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat_cache")
public class ChatCache implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "from_user_name")
    private String fromUserName;

    @ColumnInfo(name = "to_user_name")
    private String toUserName;

    private String avatar;

    private String user;

    private int count;

    @ColumnInfo(name = "last_time")
    private String lastTime;

    @ColumnInfo(name = "last_string")
    private String lastString;

    private ChatCache(Builder builder) {
        this.id = builder.id;
        this.fromUserName = builder.fromUserName;
        this.toUserName = builder.toUserName;
        this.avatar = builder.avatar;
        this.user = builder.username;
        this.count = builder.count;
        this.lastString = builder.lastString;
    }

    public static Builder newChatCache() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLastString() {
        return lastString;
    }

    public void setLastString(String lastString) {
        this.lastString = lastString;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.fromUserName);
        dest.writeString(this.toUserName);
        dest.writeString(this.avatar);
        dest.writeString(this.user);
        dest.writeInt(this.count);
        dest.writeString(this.lastString);
    }

    public ChatCache() {
    }

    protected ChatCache(Parcel in) {
        this.id = in.readInt();
        this.fromUserName = in.readString();
        this.toUserName = in.readString();
        this.avatar = in.readString();
        this.user = in.readString();
        this.count = in.readInt();
        this.lastString = in.readString();
    }

    public static final Parcelable.Creator<ChatCache> CREATOR = new Parcelable.Creator<ChatCache>() {
        @Override
        public ChatCache createFromParcel(Parcel source) {
            return new ChatCache(source);
        }

        @Override
        public ChatCache[] newArray(int size) {
            return new ChatCache[size];
        }
    };


    public static final class Builder {
        private int id;
        private String fromUserName;
        private String toUserName;
        private String avatar;
        private String username;
        private int count;
        private String lastString;

        private Builder() {
        }

        public ChatCache build() {
            return new ChatCache(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder fromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
            return this;
        }

        public Builder toUserName(String toUserName) {
            this.toUserName = toUserName;
            return this;
        }

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
        }

        public Builder lastString(String lastString) {
            this.lastString = lastString;
            return this;
        }
    }
}
