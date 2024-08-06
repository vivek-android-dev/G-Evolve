package com.saveetha.g_evolve;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saveetha.g_evolve.adapter.RecyclerListAdapter;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.FragmentAdminRecyclerListBinding;
import com.saveetha.g_evolve.modules.RecyclerListModule;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminRecyclerListFragment extends Fragment {

    FragmentAdminRecyclerListBinding binding;

    ArrayList<RecyclerListModule> recyclerList;
    RecyclerListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentAdminRecyclerListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        showAllRecycler();

        binding.addRecyclerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), AddRecyclerActivity.class));

            }
        });

        return view;
    }

    private void showAllRecycler() {

        // show all recycler using api

        Call<ShowAllRecyclerResponse> res = RetroClient.makeApi().showAllRecycler();

        res.enqueue(new Callback<ShowAllRecyclerResponse>() {
            @Override
            public void onResponse(Call<ShowAllRecyclerResponse> call, Response<ShowAllRecyclerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().status == 200) {

                        recyclerList = new ArrayList<>();
                        for (int i = 0; i < response.body().getRecycler().size(); i++) {
                            String companyName = response.body().getRecycler().get(i).getCompany_name();
                            String email = response.body().getRecycler().get(i).getEmail();
                            String address = response.body().getRecycler().get(i).getAddress();
                            String contact = response.body().getRecycler().get(i).getContact();
                            String time = response.body().getRecycler().get(i).getOpen_time() + " - " + response.body().getRecycler().get(i).getClose_time();
                            String location = response.body().getRecycler().get(i).getAddress();

                            recyclerList.add(new RecyclerListModule(companyName, email, contact, address, time, location));
                        }


                        adapter = new RecyclerListAdapter(recyclerList, getContext());
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowAllRecyclerResponse> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}