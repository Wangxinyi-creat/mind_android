package com.pj.mind.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.pj.mind.ui.fragment.WenzhangFragment;

import java.util.List;


/**
 * author : likai
 * time   : 2022/9/1 10
 * desc   :
 */
public class AdapterTabFragmentPage2 extends FragmentStateAdapter{
    private List<WenzhangFragment> fragments;


    public AdapterTabFragmentPage2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<WenzhangFragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public List<WenzhangFragment> getFragments() {
        return fragments;
    }
}
