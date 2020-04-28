package com.personalpro.im_app.repository;

import androidx.lifecycle.MutableLiveData;

import com.personalpro.im_app.api.UserApi;
import com.personalpro.im_app.bean.ItemResponse;
import com.personalpro.im_app.bean.ListResponse;
import com.personalpro.im_app.bean.contact.Contact;
import com.personalpro.im_app.bean.login.LoginResponse;
import com.personalpro.im_app.http.HttpClient;
import com.personalpro.im_app.util.ThreadTask;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository instance;
    private UserApi userApi;

    private UserRepository(){
        userApi = HttpClient.newInstance().getService().create(UserApi.class);
    }

    public static UserRepository newInstance(){
        if(instance == null){
            synchronized (UserRepository.class){
                if(instance == null){
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public MutableLiveData<ItemResponse<LoginResponse>> doLogin(String username, String password){
        MutableLiveData<ItemResponse<LoginResponse>> data = new MutableLiveData<>();
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        ThreadTask.getInstance().executorNetThread(() -> userApi.doLogin(params).enqueue(new Callback<ItemResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<ItemResponse<LoginResponse>> call, Response<ItemResponse<LoginResponse>> response) {
                if(response.isSuccessful()){
                    data.postValue(response.body());
                }else{
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse<LoginResponse>> call, Throwable t) {
                data.postValue(null);
            }
        }),1);
        return data;
    }

    public MutableLiveData<ListResponse<Contact>> getContacts(String username){
        MutableLiveData<ListResponse<Contact>> data = new MutableLiveData<>();
        ThreadTask.getInstance().executorNetThread(() -> userApi.getContacts(username).enqueue(new Callback<ListResponse<Contact>>() {
            @Override
            public void onResponse(Call<ListResponse<Contact>> call, Response<ListResponse<Contact>> response) {
                if(response.isSuccessful()){
                    data.postValue(response.body());
                }else{
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ListResponse<Contact>> call, Throwable t) {
                data.postValue(null);
            }
        }),1);
        return data;
    }

}
