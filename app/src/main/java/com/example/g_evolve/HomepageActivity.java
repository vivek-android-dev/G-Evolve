package com.example.g_evolve;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        TabLayout tabLayout = findViewById(R.id.homepage_tab);
        ViewPager2 viewPager = findViewById(R.id.homepageView);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.home);
                    break;
                case 1:
                    tab.setText("Location");
                    tab.setIcon(R.drawable.location);
                    break;
                case 2:
                    tab.setText("Recycle");
                    tab.setIcon(R.drawable.recycle_icon);
                    break;
                case 3:
                    tab.setText("Edu");
                    tab.setIcon(R.drawable.book_icon);
                    break;
                case 4:
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
                    return new homeFragment();
                case 1:
                    return new LocateFacilityFragment();
                case 2:
                    return new RecycleCenterFragment();
                case 3:
                    return new EwasteEducationFragment();
                case 4:
                    return new SettingsFragment();
                default:
                    return new homeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 5; // Number of tabs
        }
    }
}
