package com.saveetha.g_evolve.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.databinding.FragmentRecyclerHistoryBinding;


public class RecyclerHistoryFragment extends Fragment {

    FragmentRecyclerHistoryBinding binding;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentRecyclerHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, new PendingFragment()).commit();

        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
//                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("historyVsf", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("historyType", "pendingView");
//                        editor.apply();
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, new PendingFragment()).commit();
                        break;
                    case 1:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, new AcceptedFragment()).commit();
//                        SharedPreferences sharedPreferences1 = requireActivity().getSharedPreferences("historyVsf", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
//                        editor1.putString("historyType", "acceptedView");
//                        editor1.apply();
                        break;
                    case 2:
//                        SharedPreferences sharedPreferences2 = requireActivity().getSharedPreferences("historyVsf", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//                        editor2.putString("historyType", "rejectedView");
//                        editor2.apply();
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, new RejectedFragment()).commit();
                        break;
                    default:
//                        SharedPreferences sharedPreferences3 = requireActivity().getSharedPreferences("historyVsf", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
//                        editor3.putString("historyType", "pendingView");
//                        editor3.apply();
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.historyContainer, new PendingFragment()).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;


    }


//    @Override
//    public void onBackPressed() {
//        if (fragmentManager.getBackStackEntryCount() > 0) {
//            fragmentManager.popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }
}