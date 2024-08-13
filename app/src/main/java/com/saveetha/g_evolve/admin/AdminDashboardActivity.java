package com.saveetha.g_evolve.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.saveetha.g_evolve.AdminHomeFragment;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.common.SettingsFragment;
import com.saveetha.g_evolve.databinding.ActivityAdminDashboardBinding;
import com.google.android.material.tabs.TabLayout;

public class AdminDashboardActivity extends AppCompatActivity {

    ActivityAdminDashboardBinding binding;
    TabLayout tabLayout;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tabLayout = binding.adminTabLay;
        fragmentManager = getSupportFragmentManager();

        // Load the default fragment
        replaceFragment(new AdminHomeFragment(), false);

        // Set up tab layout
        setupTabLayout();



    }


    private void setupTabLayout() {


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new AdminHomeFragment();
                        break;
                    case 1:
                        selectedFragment = new AdminDetailsFragment();
                        break;
                    case 2:
                        selectedFragment = new SettingsFragment();
                        break;
                }
                if (selectedFragment != null) {
                    replaceFragment(selectedFragment, true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
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