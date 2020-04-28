package com.personalpro.im_app.fragment;

import android.content.Context;
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
import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.CacheChatAdapter;
import com.personalpro.im_app.adapter.RecycleViewItemDecoration;
import com.personalpro.im_app.databinding.MessageDataBinding;
import com.personalpro.im_app.room.entity.ChatCache;
import com.personalpro.im_app.util.LogUtil;
import com.personalpro.im_app.util.SharedPrefUtil;
import com.personalpro.im_app.viewmodel.ChatViewModel;

public class CacheChatFragment extends BaseFragment<MessageDataBinding> {

    private static final String TAG = CacheChatFragment.class.getSimpleName();

    private String user;
    private ChatViewModel chatViewModel;
    private CacheChatAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cache_chat;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView() {
        adapter = new CacheChatAdapter();
        dataBinding.list.setAdapter(adapter);
        dataBinding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.list.addItemDecoration(new RecycleViewItemDecoration());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        user = SharedPrefUtil.getInstance(getContext()).getValue(Constant.USER_NAME_KEY,String.class);

        chatViewModel.getCacheChat(user).observe(this, chatCaches ->{
            for(ChatCache chat:chatCaches){
                LogUtil.i(TAG,chat.getUser());
            }

            adapter.updateList(chatCaches);
        } );
    }
}
