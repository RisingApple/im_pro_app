package com.personalpro.im_app.api;

import com.personalpro.im_app.bean.ListResponse;
import com.personalpro.im_app.bean.ItemResponse;
import com.personalpro.im_app.bean.contact.Contact;
import com.personalpro.im_app.bean.login.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @FormUrlEncoded
    @POST("/login")
    Call<ItemResponse<LoginResponse>> doLogin(@FieldMap Map<String,String> map);

    @GET("user/getAllContact")
    Call<ListResponse<Contact>> getContacts(@Query("username")String username);



}
