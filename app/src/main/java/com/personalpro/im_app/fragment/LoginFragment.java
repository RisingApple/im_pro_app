package com.personalpro.im_app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.R;
import com.personalpro.im_app.databinding.LoginDataBinding;
import com.personalpro.im_app.util.SharedPrefUtil;
import com.personalpro.im_app.viewmodel.UserViewModel;

import java.util.Objects;

public class LoginFragment extends BaseFragment<LoginDataBinding> {

    private UserViewModel userViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        dataBinding.doLogin.setOnClickListener(v -> userViewModel.doLogin(dataBinding.username.getText().toString(),dataBinding.password.getText().toString()).observe(this, loginResponseItemResponse -> {

            if(loginResponseItemResponse == null){
                Toast.makeText(getContext(),"登录失败",Toast.LENGTH_LONG).show();
                return;
            }
            SharedPrefUtil.getInstance(getContext()).putValue(Constant.SESSION_KEY,loginResponseItemResponse.getData().getSessionId());
            SharedPrefUtil.getInstance(getContext()).putValue(Constant.USER_NAME_KEY,loginResponseItemResponse.getData().getUsername());
            userViewModel.getStart().call();
            getNavController().navigate(R.id.action_loginFragment_to_mainFragment);
        }));
    }
}
