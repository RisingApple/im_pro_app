package com.personalpro.im_app.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse<T> {

    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("response_msg")
    private String responseMessage;

    List<T> data;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
