package com.personalpro.im_app.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.base.BaseAdapter;
import com.personalpro.im_app.adapter.base.BaseViewHolder;
import com.personalpro.im_app.adapter.base.OnItemClickListener;
import com.personalpro.im_app.databinding.CaCheChatItemDataBinding;
import com.personalpro.im_app.room.entity.ChatCache;

public class CacheChatAdapter extends BaseAdapter<ChatCache, CaCheChatItemDataBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.item_cache_chat;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @NonNull
    @Override
    public BaseViewHolder<CaCheChatItemDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<CaCheChatItemDataBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        ChatCache chatCache = data.get(position);
        holder.getBinding().setItem(chatCache);
    }

}
