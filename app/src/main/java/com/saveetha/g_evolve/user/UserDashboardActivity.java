package com.saveetha.g_evolve.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.RecycleCenterFragment;
import com.saveetha.g_evolve.common.SettingsFragment;
import com.saveetha.g_evolve.databinding.ActivityUserDashboardBinding;

public class UserDashboardActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    TabLayout tabLayout;
    ActivityUserDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tabLayout = binding.userTab;
        fragmentManager = getSupportFragmentManager();

        replaceFragment(new UserHomeFragment(), false);

        setupTabLayout();

    }

    private void setupTabLayout() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new UserHomeFragment();
                        break;
                    case 1:
                        selectedFragment = new LocateFacilityFragment();
                        break;
                    case 2:
                        selectedFragment = new RecycleCenterFragment();
                        break;
                    case 3:
                        selectedFragment = new EwasteEducationFragment();
                        break;
                    case 4:
                        selectedFragment = new SettingsFragment();
                        break;
                }
                if (selectedFragment != null) {
                    replaceFragment(selectedFragment, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new UserHomeFragment();
                        break;
                    case 1:
                        selectedFragment = new LocateFacilityFragment();
                        break;
                    case 2:
                        selectedFragment = new RecycleCenterFragment();
                        break;
                    case 3:
                        selectedFragment = new EwasteEducationFragment();
                        break;
                    case 4:
                        selectedFragment = new SettingsFragment();
                        break;
                }
                if (selectedFragment != null) {
                    replaceFragment(selectedFragment, true);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
