package com.personalpro.im_app.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T,D extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<D>> {

    protected List<T> data = new ArrayList<>();
    protected OnItemClickListener onItemClickListener;

    public abstract int getLayoutId();

    protected void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder<D> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        D binding = DataBindingUtil.inflate(inflater,getLayoutId(),parent,false);
        return new BaseViewHolder<D>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<D> holder, int position) {
        if(onItemClickListener != null) {
            holder.getBinding().getRoot().setOnClickListener(v -> onItemClickListener.onItemClick(data.get(position),holder.getBinding().getRoot(),position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateList(List<T> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void addItem(T item){
        data.add(item);
        notifyItemInserted(data.size()-1);
    }

    public void addItem(List<T> items){
        for(T item:items){
            data.add(item);
        }
        notifyDataSetChanged();
    }

    public void removeItem(T item){
        if(data.contains(item)){
            data.remove(item);
        }
        notifyDataSetChanged();
    }

    public int getDataSize(){
        return data.size();
    }

    public List<T> getData(){
        return data;
    }

}
