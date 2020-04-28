package com.personalpro.im_app.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.base.BaseAdapter;
import com.personalpro.im_app.adapter.base.BaseViewHolder;
import com.personalpro.im_app.bean.websocket.ChatMessage;
import com.personalpro.im_app.databinding.ChatMsgDataBinding;

public class ChatAdapter extends BaseAdapter<ChatMessage, ChatMsgDataBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.item_chat;
    }

    @NonNull
    @Override
    public BaseViewHolder<ChatMsgDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ChatMsgDataBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ChatMessage chatMessage = data.get(position);
        holder.getBinding().setItem(chatMessage);
    }
}
