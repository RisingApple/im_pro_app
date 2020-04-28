package com.personalpro.im_app.fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

public abstract class BaseFragment<D extends ViewDataBinding> extends Fragment {

    protected D dataBinding;

    protected abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        initData();
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected void initData(){}

    protected NavController getNavController(){
        return NavHostFragment.findNavController(this);
    }

    protected void sendBroadCast(Intent intent){
        Objects.requireNonNull(getContext()).sendBroadcast(intent);
    }

    protected void registerReceiver(BroadcastReceiver broadcastReceiver){

    }

    protected void unRegisterReceiver(BroadcastReceiver broadcastReceiver){

    }

    protected void jumpToLogin(){

    }

}
