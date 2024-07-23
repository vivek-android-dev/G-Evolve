package com.example.g_evolve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.g_evolve.databinding.AdminDashboardBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AdminDashboardActivity extends AppCompatActivity {

    AdminDashboardBinding binding;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ViewPagerAdapter(this);
        binding.viewPager2.setAdapter(adapter);
        tabLayout = binding.adminTabLay;
        viewPager = binding.viewPager2;

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                     tab.setIcon(R.drawable.home);
                    break;
                case 1:
                    tab.setText("Recycle");
                     tab.setIcon(R.drawable.recycle_icon);
                    break;
                case 2:
                    tab.setText("Settings");
                     tab.setIcon(R.drawable.settings_icon);
                    break;
            }
        }).attach();

    }

    private static class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull AppCompatActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new AdminHomeFragment();
                case 1:
                    return new RecycleCenterFragment();
                case 2:
                    return new SettingsFragment();
                default:
                    return new AdminHomeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3; // Number of tabs
        }
    }
}