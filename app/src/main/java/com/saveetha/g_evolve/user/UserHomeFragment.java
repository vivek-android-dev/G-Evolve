package com.saveetha.g_evolve.user;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saveetha.g_evolve.R;

public class UserHomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
//
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
