package com.personalpro.im_app.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.IMApplication;
import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.ChatAdapter;
import com.personalpro.im_app.adapter.RecycleViewItemDecoration;
import com.personalpro.im_app.bean.websocket.ChatMessage;
import com.personalpro.im_app.bean.websocket.ChatResponse;
import com.personalpro.im_app.databinding.ChatDataBinding;
import com.personalpro.im_app.room.entity.ChatItem;
import com.personalpro.im_app.util.DateUtil;
import com.personalpro.im_app.util.GSONUtil;
import com.personalpro.im_app.util.LogUtil;
import com.personalpro.im_app.util.SharedPrefUtil;
import com.personalpro.im_app.viewmodel.ChatViewModel;

import java.util.Date;
import java.util.Objects;

public class ChatFragment extends BaseFragment<ChatDataBinding> {

    private static final String TAG = ChatFragment.class.getSimpleName();

    private Intent intent = new Intent();
    private String fromUserName;
    private String toUserName;
    private ChatAdapter chatAdapter;

    private ChatViewModel chatViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        fromUserName = SharedPrefUtil.getInstance(IMApplication.getContext()).getValue(Constant.USER_NAME_KEY,String.class);
        assert getArguments() != null;
        toUserName = getArguments().getString(Constant.USER_NAME_KEY);

        chatAdapter = new ChatAdapter();
        dataBinding.msgLayout.addItemDecoration(new RecycleViewItemDecoration(30));
        dataBinding.msgLayout.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.msgLayout.setAdapter(chatAdapter);

        getChatRecord();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(getContext()).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void initListener() {
        initBroadcast();
        dataBinding.send.setOnClickListener(v -> send());
    }

    private void send(){
        if(dataBinding.edit.getText().length()==0) return;

        ChatResponse chatResponse = builderResponse();
        chatAdapter.addItem(chatResponse.getData());

        insertToDataBase(fromUserName,toUserName,dataBinding.edit.getText().toString());
        dataBinding.edit.setText("");
        dataBinding.msgLayout.scrollToPosition(chatAdapter.getData().size()-1);

        intent.setAction(Constant.BROAD_CAST_SEND_MESSAGE);
        intent.putExtra(Constant.SEND_MESSAGE_KEY, GSONUtil.newInstance().toJson(chatResponse));
        sendBroadCast(intent);
        LogUtil.i("message",GSONUtil.newInstance().toJson(chatResponse)+"");
    }

    private void receive(ChatMessage chatMessage){
        if(chatMessage == null) return;
        if(!chatMessage.getFromUserName().equals(toUserName)) return;
        chatMessage.setOutside(true);
        chatAdapter.addItem(chatMessage);
        insertToDataBase(toUserName,fromUserName,chatMessage.getMessage());
        dataBinding.msgLayout.scrollToPosition(chatAdapter.getData().size()-1);
        LogUtil.i(TAG,chatMessage.getMessage());
    }

    private ChatResponse builderResponse(){
        return new ChatResponse.Builder()
                .eventType("chatMsg")
                .groupChat(false)
                .groupChatId(0)
                .fromUserName(fromUserName)
                .message(dataBinding.edit.getText().toString())
                .toUserName(toUserName)
                .outside(false)
                .build();
    }

    private void insertToDataBase(String fromUserName,String toUserName,String message){
        String date = DateUtil.formatDate(new Date(),DateUtil.PATTERN_DATETIME);
        ChatItem chatItem = new ChatItem(date,false,"0",fromUserName,toUserName,message);
        chatViewModel.insetChatItem(chatItem);
    }

    private void getChatRecord(){
        chatViewModel.getChatList(fromUserName,toUserName).observe(this, chatItems -> {
            for(ChatItem item:chatItems){
                ChatMessage chatMessage = new ChatMessage.Builder()
                        .fromUserName(item.getFromUserName())
                        .toUserName(item.getToUserName())
                        .message(item.getMessage())
                        .isGroupChat(false)
                        .groupChatId(0)
                        .outside(item.getFromUserName().equals(toUserName))
                        .build();

                chatAdapter.addItem(chatMessage);
            }
        });
    }


    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BROAD_CAST_SOCKET_OPEN);
        intentFilter.addAction(Constant.BROAD_CAST_SOCKET_MESSAGE);
        intentFilter.addAction(Constant.BROAD_CAST_SOCKET_CLOSE);
        intentFilter.addAction(Constant.BROAD_CAST_SOCKET_ERROR);
        Objects.requireNonNull(getContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constant.BROAD_CAST_SOCKET_MESSAGE.equals(Objects.requireNonNull(intent.getAction()))) {
                ChatMessage msg = intent.getParcelableExtra(Constant.CHAT_MESSAGE_KEY);
                if(msg.getFromUserName().equals(toUserName)){
                    receive(msg);
                }
            }
        }
    };

}
