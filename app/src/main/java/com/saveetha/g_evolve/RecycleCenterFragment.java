package com.saveetha.g_evolve;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RecycleCenterFragment extends Fragment {

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycle_center_fragment, container, false);

        sharedPreferences = getActivity().getSharedPreferences("selected_facility", Context.MODE_PRIVATE);


        // Find the button and set an onClickListener on it
        Button button = view.findViewById(R.id.button18);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                sharedPreferences.edit().putString("facility_id", "Smartphone Recycling").apply();
                sharedPreferences.edit().putInt("category", 1).apply();
                Intent intent = new Intent(getActivity(), AddProductRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button19 = view.findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity

                sharedPreferences.edit().putString("facility_id", "Laptop Recycling").apply();
                sharedPreferences.edit().putInt("category", 2).apply();
                Intent intent = new Intent(getActivity(), AddProductRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button20 = view.findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                sharedPreferences.edit().putString("facility_id", "Accessories Recycling").apply();
                sharedPreferences.edit().putInt("category", 3).apply();
                Intent intent = new Intent(getActivity(), AddProductRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button21 = view.findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                sharedPreferences.edit().putString("facility_id", "Television Recycling").apply();
                sharedPreferences.edit().putInt("category", 4).apply();
                Intent intent = new Intent(getActivity(), AddProductRecyclingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
