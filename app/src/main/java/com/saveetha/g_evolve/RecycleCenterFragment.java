package com.saveetha.g_evolve;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RecycleCenterFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecycleCenterFragment() {
        // Required empty public constructor
    }

    public static RecycleCenterFragment newInstance(String param1, String param2) {
        RecycleCenterFragment fragment = new RecycleCenterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycle_center_fragment, container, false);

        // Find the button and set an onClickListener on it
        Button button = view.findViewById(R.id.button18);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                Intent intent = new Intent(getActivity(), SmartphoneRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button19 = view.findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                Intent intent = new Intent(getActivity(), LaptopRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button20 = view.findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                Intent intent = new Intent(getActivity(), AccessoriesRecyclingActivity.class);
                startActivity(intent);
            }
        });
        Button button21 = view.findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the RecycleCenterActivity
                Intent intent = new Intent(getActivity(), TelevisionRecyclingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
