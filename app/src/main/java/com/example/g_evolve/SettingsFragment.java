package com.example.g_evolve;

import static android.content.Context.MODE_PRIVATE;
import static com.example.g_evolve.api.RetroClient.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.g_evolve.api.RetroClient;
import com.example.g_evolve.responses.GetProfileResponse;
import com.google.android.material.imageview.ShapeableImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingsFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_fragment, container, false);



        // Inflate the layout for this fragment
        SharedPreferences sf = requireActivity().getSharedPreferences("usersf",MODE_PRIVATE);
        String userid = sf.getString("userid",null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ShapeableImageView img = view.findViewById(R.id.profileImage);

        if(userid != null){
            Call<GetProfileResponse> res = RetroClient.makeApi().profile(userid);
            res.enqueue(new Callback<GetProfileResponse>() {
                @Override
                public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body().getStatus() == 200){
                            Glide.with(requireContext())
                                    .load(BASE_URL+response.body().getData().getImage())
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(200)))
                                    .placeholder(R.drawable.circle)
                                    .into(img);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                    Toast.makeText(requireContext(), "Internal error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        ImageButton button = view.findViewById(R.id.imageButton4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });
        View view1 = view.findViewById(R.id.view);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), AboutAppActivity.class);
                startActivity(intent);
            }
        });
        View view2 = view.findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), PrivacyActivity.class);
                startActivity(intent);
            }
        });
        View view3 = view.findViewById(R.id.view3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), TermsOfServicesActivity.class);
                startActivity(intent);
            }
        });
        View view4 = view.findViewById(R.id.view4);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
        View view5 = view.findViewById(R.id.view5);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        Button button9 = view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), LoginPageActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}