package com.saveetha.g_evolve.user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.RecycleCenterFragment;
import com.saveetha.g_evolve.databinding.FragmentUserHomeBinding;

public class UserHomeFragment extends Fragment {


    FragmentUserHomeBinding binding;
    FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        try {
            activity = getActivity();
        }catch (Exception e){
            e.printStackTrace();
        }

        binding.recycleBtn.setOnClickListener(v -> {
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RecycleCenterFragment()).commit();
        });

        binding.locateBtn.setOnClickListener(v -> {
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LocateFacilityFragment()).commit();
        });

        // Inflate the layout for this fragment
//        Button button = view.findViewById(R.id.button3);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Switch to the second tab
//                ViewPager2 viewPager = getActivity().findViewById(R.id.fragmentContainer);
//                viewPager.setCurrentItem(2); // Index of the tab to switch to
//            }
//        });
//        Button button4 = view.findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Switch to the second tab
//                ViewPager2 viewPager = getActivity().findViewById(R.id.homepageView);
//                viewPager.setCurrentItem(1); // Index of the tab to switch to
//            }
//        });

        return view;
    }
}
