package com.personalpro.im_app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.personalpro.im_app.service.MessageHandleService;
import com.personalpro.im_app.service.SocketService;
import com.personalpro.im_app.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    private static final String TAG = MainActivity.class.getSimpleName();
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        initListener();
    }


    private void initListener(){
        userViewModel.getStart().observe(this, aBoolean -> initService());
    }

    private void initService() {
        Intent intentWS = new Intent(MainActivity.this, SocketService.class);
        bindService(intentWS, MainActivity.this, Context.BIND_AUTO_CREATE);

        Intent intentMH = new Intent(MainActivity.this, MessageHandleService.class);
        bindService(intentMH,MainActivity.this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i(TAG,"Service is Connect");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG,"Service is disConnect");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }
}
