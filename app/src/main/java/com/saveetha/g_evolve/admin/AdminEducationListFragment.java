package com.saveetha.g_evolve.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.adapter.EducationListAdapter;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.FragmentAdminEducationListBinding;
import com.saveetha.g_evolve.modules.EducationListModule;
import com.saveetha.g_evolve.responses.AddEducationResponse;
import com.saveetha.g_evolve.utils.LastItemBottomMarginDecoration;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminEducationListFragment extends Fragment {


    FragmentAdminEducationListBinding binding;
    ArrayList<EducationListModule> educationList;
    FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdminEducationListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        try {
            activity = getActivity();
        }catch (Exception e){
            e.printStackTrace();
        }

        loadAllEducation();

        binding.addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddEducationActivity.class));

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void loadAllEducation() {

        Call<AddEducationResponse> res = RetroClient.makeApi().showAllEducation();

        res.enqueue(new Callback<AddEducationResponse>() {
            @Override
            public void onResponse(Call<AddEducationResponse> call, Response<AddEducationResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {


                        if (response.body().getEducation().size() != 0) {
                            educationList = new ArrayList<>();

                            for (int i = 0; i < response.body().getEducation().size(); i++) {

                                try {
                                    String title = response.body().getEducation().get(i).getTitle();
                                    String description = response.body().getEducation().get(i).getDescription();
                                    String image = response.body().getEducation().get(i).getImage();
                                    String edu_id = response.body().getEducation().get(i).getEdu_id();


                                    educationList.add(new EducationListModule(title, description, image, edu_id));

                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                            }
//
                        }


                        EducationListAdapter adapter = new EducationListAdapter(educationList, activity);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        int bottomMargin = getResources().getDimensionPixelSize(R.dimen.bottom_margin); // Define your margin in dimens.xml
                        binding.recyclerView.addItemDecoration(new LastItemBottomMarginDecoration(bottomMargin));
                        binding.recyclerView.setAdapter(adapter);

//                        Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.errorBody() != null) {
                    try {
                        Toast.makeText(getContext(), "" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<AddEducationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        loadAllEducation();

    }
}