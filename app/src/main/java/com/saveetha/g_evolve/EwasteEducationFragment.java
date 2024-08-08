package com.saveetha.g_evolve;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class EwasteEducationFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ewaste_education_fragment, container, false);
        Button button6 = view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutEwasteActivity.class);
                startActivity(intent);
            }
        });
        Button button7 = view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutEwasteRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button8 = view.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutEwaste3Activity.class);
                startActivity(intent);
            }
        });
        Button button10 = view.findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutEwaste4Activity.class);
                startActivity(intent);
            }
        });
        Button button11 = view.findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutEwaste5Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}