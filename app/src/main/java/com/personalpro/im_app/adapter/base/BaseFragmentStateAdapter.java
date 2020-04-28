package com.personalpro.im_app.adapter.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.personalpro.im_app.fragment.BaseFragment;

import java.util.List;

public class BaseFragmentStateAdapter extends FragmentStateAdapter {

    private List<BaseFragment> baseFragments;

    public BaseFragmentStateAdapter(List<BaseFragment> baseFragments, @NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.baseFragments = baseFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return baseFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return baseFragments.size();
    }
}
