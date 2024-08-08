package com.saveetha.g_evolve;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
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

        replaceFragment(new homeFragment(), false);


        setupTabLayout();

//        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
//        viewPager.setAdapter(adapter);
//        viewPager.setUserInputEnabled(false);
//
//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
//            switch (position) {
//                case 0:
//                    tab.setText("Home");
//                    tab.setIcon(R.drawable.home);
//                    break;
//                case 1:
//                    tab.setText("Location");
//                    tab.setIcon(R.drawable.location);
//                    break;
//                case 2:
//                    tab.setText("Recycle");
//                    tab.setIcon(R.drawable.recycle_icon);
//                    break;
//                case 3:
//                    tab.setText("Edu");
//                    tab.setIcon(R.drawable.book_icon);
//                    break;
//                case 4:
//                    tab.setText("Settings");
//                    tab.setIcon(R.drawable.settings_icon);
//                    break;
//            }
//        }).attach();
//
//    }
//
//    private static class ViewPagerAdapter extends FragmentStateAdapter {
//
//        public ViewPagerAdapter(@NonNull AppCompatActivity fragmentActivity) {
//            super(fragmentActivity);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            switch (position) {
//                case 0:
//                    return new homeFragment();
//                case 1:
//                    return new LocateFacilityFragment();
//                case 2:
//                    return new RecycleCenterFragment();
//                case 3:
//                    return new EwasteEducationFragment();
//                case 4:
//                    return new SettingsFragment();
//                default:
//                    return new homeFragment();
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return 5; // Number of tabs
//        }
    }

    private void setupTabLayout() {


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new homeFragment();
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
