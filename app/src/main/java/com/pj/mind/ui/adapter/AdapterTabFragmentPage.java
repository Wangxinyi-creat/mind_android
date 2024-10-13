package com.pj.mind.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;


/**
 * author : likai
 * time   : 2022/9/1 10
 * desc   :
 */
public class AdapterTabFragmentPage extends FragmentStateAdapter{
    private List<Fragment> fragments;


    public AdapterTabFragmentPage(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
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

    public List<Fragment> getFragments() {
        return fragments;
    }
}
