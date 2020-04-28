package com.personalpro.im_app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.base.BaseFragmentStateAdapter;
import com.personalpro.im_app.databinding.MainDataBinding;
import com.personalpro.im_app.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends BaseFragment<MainDataBinding>{

    private static final String TAG = MainFragment.class.getSimpleName();
    private UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        userViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(UserViewModel.class);
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
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new CacheChatFragment());
        fragments.add(new FriendsListFragment());
        BaseFragmentStateAdapter adapter = new BaseFragmentStateAdapter(fragments, Objects.requireNonNull(getActivity()));
        dataBinding.pager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        userViewModel.getOpenChat().observe(this, aBoolean -> jumpToChat());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void jumpToChat(){
        Bundle bundle = new Bundle();
        bundle.putString(Constant.USER_NAME_KEY,userViewModel.getChatName().getValue());
        getNavController().navigate(R.id.action_mainFragment_to_chatFragment,bundle);
    }

}

