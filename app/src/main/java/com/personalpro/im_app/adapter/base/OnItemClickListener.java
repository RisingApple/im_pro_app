package com.personalpro.im_app.adapter.base;

import android.view.View;

public interface OnItemClickListener<T> {

    void onItemClick(T t, View v,int position);

}
