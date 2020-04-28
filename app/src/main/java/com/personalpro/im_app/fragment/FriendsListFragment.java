package com.personalpro.im_app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.ContactAdapter;
import com.personalpro.im_app.adapter.base.OnItemClickListener;
import com.personalpro.im_app.bean.contact.Contact;
import com.personalpro.im_app.databinding.FriendsListDataBinding;
import com.personalpro.im_app.util.SharedPrefUtil;
import com.personalpro.im_app.viewmodel.UserViewModel;

import java.util.Objects;

public class FriendsListFragment extends BaseFragment<FriendsListDataBinding> implements OnItemClickListener {

    private ContactAdapter adapter;
    private UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        userViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        adapter = new ContactAdapter();
        dataBinding.list.setAdapter(adapter);
        dataBinding.list.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        String username = SharedPrefUtil.getInstance(getContext()).getValue(Constant.USER_NAME_KEY,String.class);
        userViewModel.getContact(username).observe(this, contactListResponse -> adapter.updateList(contactListResponse.getData()));
    }


    @Override
    public void onItemClick(Object o, View v, int position) {
        Contact contact = (Contact) o;
        userViewModel.setChatName(contact.getUsername());
        userViewModel.getOpenChat().call();
    }
}
