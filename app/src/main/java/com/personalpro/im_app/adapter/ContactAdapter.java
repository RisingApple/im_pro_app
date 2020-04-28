package com.personalpro.im_app.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.personalpro.im_app.R;
import com.personalpro.im_app.adapter.base.BaseAdapter;
import com.personalpro.im_app.adapter.base.BaseViewHolder;
import com.personalpro.im_app.adapter.base.OnItemClickListener;
import com.personalpro.im_app.bean.contact.Contact;
import com.personalpro.im_app.databinding.ContactDataBinding;

public class ContactAdapter extends BaseAdapter<Contact, ContactDataBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.item_contact;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }

    @NonNull
    @Override
    public BaseViewHolder<ContactDataBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<ContactDataBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        Contact contact = data.get(position);
        holder.getBinding().setItem(contact);
    }
}
