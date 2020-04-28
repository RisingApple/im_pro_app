package com.personalpro.im_app.adapter.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T binding;

    public BaseViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding(){
        return binding;
    }

}
