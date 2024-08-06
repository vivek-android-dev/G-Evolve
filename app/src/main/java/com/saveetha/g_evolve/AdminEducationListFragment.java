package com.saveetha.g_evolve;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saveetha.g_evolve.databinding.FragmentAdminEducationListBinding;


public class AdminEducationListFragment extends Fragment {


    FragmentAdminEducationListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdminEducationListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddEducationActivity.class));

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}