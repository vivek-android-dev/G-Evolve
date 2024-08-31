package com.saveetha.g_evolve.common;

import static android.content.Context.MODE_PRIVATE;
import static com.saveetha.g_evolve.api.RetroClient.BASE_URL;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.saveetha.g_evolve.user.ContactUsActivity;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.SettingsFragmentBinding;
import com.saveetha.g_evolve.responses.GetProfileResponse;
import com.google.android.material.imageview.ShapeableImageView;
import com.saveetha.g_evolve.user.AboutAppActivity;
import com.saveetha.g_evolve.user.PrivacyActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SettingsFragment extends Fragment {


    ShapeableImageView img;

    FragmentActivity activity;
    Context context;
    String userid, adminid;

    SettingsFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inflate the layout for this fragment
        SharedPreferences sf = requireActivity().getSharedPreferences("usersf", MODE_PRIVATE);
        userid = sf.getString("userid", null);

        SharedPreferences adminsf = requireActivity().getSharedPreferences("adminsf", MODE_PRIVATE);
        adminid = adminsf.getString("userid", null);

        SharedPreferences recyclersf = requireActivity().getSharedPreferences("recyclersf", MODE_PRIVATE);
        String recyclerid = recyclersf.getString("userid", null);

        if (adminid != null) {
            showProfile(adminid);
            binding.textView25.setText("Admin Profile");
            binding.Settings.setVisibility(View.GONE);
            binding.aboutCV.setVisibility(View.GONE);
            binding.privacyCV.setVisibility(View.GONE);
            binding.termsCV.setVisibility(View.GONE);
            binding.contactCV.setVisibility(View.GONE);
            binding.historyCV.setVisibility(View.GONE);
        } else if (userid != null) {
            showProfile(userid);
            binding.historyCV.setVisibility(View.GONE);
        } else if (recyclerid != null) {
            binding.historyCV.setVisibility(View.GONE);
            binding.profileCV.setVisibility(View.GONE);
        }

        try {
            activity = getActivity();
            context = getContext();

        } catch (Exception e) {
            e.printStackTrace();
        }

        img = view.findViewById(R.id.profileImage);


        binding.profileCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            Intent intent = new Intent(getActivity(), UserProfileActivity.class);
            startActivity(intent);
        });
        binding.aboutCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            binding.aboutCV.setCardElevation(20);
            Intent intent = new Intent(getActivity(), AboutAppActivity.class);
            startActivity(intent);
        });
        binding.privacyCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            binding.privacyCV.setCardElevation(20);
            Intent intent = new Intent(getActivity(), PrivacyActivity.class);
            startActivity(intent);
        });
        binding.termsCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            binding.termsCV.setCardElevation(20);
            Intent intent = new Intent(getActivity(), TermsOfServicesActivity.class);
            startActivity(intent);
        });

        binding.contactCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            binding.contactCV.setCardElevation(20);
            Intent intent = new Intent(getActivity(), ContactUsActivity.class);
            startActivity(intent);
        });

        binding.historyCV.setOnClickListener(v -> {
            // Start the activity that hosts the RecycleCenterFragment
            binding.historyCV.setCardElevation(20);
//            Intent intent = new Intent(getActivity(), HistoryActivity.class);
//            startActivity(intent);
        });

        binding.signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.signOutBtn.setElevation(20);
                SharedPreferences sf = requireActivity().getSharedPreferences("signsf", MODE_PRIVATE);
                sf.edit().putString("issignedin", null).commit();

                SharedPreferences sf1 = requireActivity().getSharedPreferences("usersf", MODE_PRIVATE);
                sf1.edit().putString("userid", null).commit();

                SharedPreferences sf2 = requireActivity().getSharedPreferences("adminsf", MODE_PRIVATE);
                sf2.edit().putString("userid", null).commit();

                // Start the activity that hosts the RecycleCenterFragment
                Intent intent = new Intent(getActivity(), LoginPageActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        return view;
    }

    private void showProfile(String id) {

        if (id != null) {

            Call<GetProfileResponse> res = RetroClient.makeApi().profile(id);
            res.enqueue(new Callback<GetProfileResponse>() {
                @Override
                public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {

                            Glide.with(context)
                                    .load(BASE_URL + response.body().getData().getImage())
                                    .placeholder(R.mipmap.placeholder)
                                    .error(R.mipmap.no_image_error)
                                    .into(img);
                        } else if (response.body().getMessage() != null) {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                    Toast.makeText(requireContext(), "Internal error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adminid != null) {
            showProfile(adminid);
            binding.textView25.setText("Admin Profile");
            binding.Settings.setVisibility(View.GONE);
            binding.aboutCV.setVisibility(View.GONE);
            binding.privacyCV.setVisibility(View.GONE);
            binding.termsCV.setVisibility(View.GONE);
            binding.contactCV.setVisibility(View.GONE);
            binding.historyCV.setVisibility(View.GONE);

        } else if (userid != null) {
            showProfile(userid);
        }

        binding.aboutCV.setCardElevation(4);
        binding.privacyCV.setCardElevation(4);
        binding.termsCV.setCardElevation(4);
        binding.contactCV.setCardElevation(4);
        binding.historyCV.setCardElevation(4);
        binding.signOutBtn.setElevation(4);

    }
}