package com.personalpro.im_app.bean;

import com.google.gson.annotations.SerializedName;

public class ItemResponse<T> {
    @SerializedName("response_code")
    private int responseCode;

    @SerializedName("response_msg")
    private String responseMessage;

    T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ItemResponse{" +
                "responseCode=" + responseCode +
                ", responseMessage='" + responseMessage + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
