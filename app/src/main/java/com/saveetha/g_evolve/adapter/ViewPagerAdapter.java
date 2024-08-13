package com.saveetha.g_evolve.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.saveetha.g_evolve.admin.AdminRecyclerListFragment;
import com.saveetha.g_evolve.admin.AdminEducationListFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AdminRecyclerListFragment();
            case 1:
                return new AdminEducationListFragment();
            default:
                return new AdminRecyclerListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
