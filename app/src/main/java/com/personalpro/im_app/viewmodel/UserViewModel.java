package com.personalpro.im_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.personalpro.im_app.repository.UserRepository;
import com.personalpro.im_app.bean.ItemResponse;
import com.personalpro.im_app.bean.ListResponse;
import com.personalpro.im_app.bean.contact.Contact;
import com.personalpro.im_app.bean.login.LoginResponse;
import com.personalpro.im_app.viewmodel.event.SingleLiveEvent;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private SingleLiveEvent<Boolean> start;
    private SingleLiveEvent<Boolean> openChat;
    private MutableLiveData<ListResponse<LoginResponse>> loginResponse;
    private MutableLiveData<String> chatName;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.newInstance();
    }

    public MutableLiveData<ItemResponse<LoginResponse>> doLogin(String username, String password){
        return userRepository.doLogin(username,password);
    }

    public MutableLiveData<ListResponse<Contact>> getContact(String username){
        return userRepository.getContacts(username);
    }

    public MutableLiveData<String> getChatName(){
        if(chatName == null){
            chatName = new MutableLiveData<>();
        }
        return chatName;
    }

    public void setChatName(String username){
        getChatName().setValue(username);
    }

    public SingleLiveEvent<Boolean> getStart(){
        if(start == null){
            start = new SingleLiveEvent<>();
        }
        return start;
    }

    public SingleLiveEvent<Boolean> getOpenChat(){
        if(openChat == null){
            openChat = new SingleLiveEvent<>();
        }
        return openChat;
    }

}
