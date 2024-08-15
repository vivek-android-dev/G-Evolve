package com.saveetha.g_evolve.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.AcceptedFragmentBinding;
import com.saveetha.g_evolve.databinding.RejectedFragmentBinding;
import com.saveetha.g_evolve.recycler.adapter.AcceptdHistoryAdapter;
import com.saveetha.g_evolve.recycler.adapter.RejectedHistoryAdapter;
import com.saveetha.g_evolve.recycler.module.HistoryModule;
import com.saveetha.g_evolve.responses.AddProductResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RejectedFragment extends Fragment {

    RejectedFragmentBinding binding;
    ArrayList<HistoryModule> list;
    Context context;

    String rcy_id, rcy_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = RejectedFragmentBinding.bind(inflater.inflate(R.layout.rejected_fragment, container, false));
        View view = binding.getRoot();

        SharedPreferences SharedPreferences = getActivity().getSharedPreferences("recyclersf", Context.MODE_PRIVATE);
        rcy_id = SharedPreferences.getString("userid", null);
        rcy_name = SharedPreferences.getString("username", null);

        try {
            context = getContext();
            list = new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
        }

        loadHistory();
        return view;
    }

    private void loadHistory() {

        Call<AddProductResponse> res = RetroClient.makeApi().showProduct(rcy_name);
        res.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {

                        try {
                            for (int i = 0; i < response.body().getProduct().size(); i++) {
                                if (response.body().getProduct().get(i).getStatus().equals("2")) {

                                    String product_id, brand, model, price, date, time, location, phone, recycler, status, user_id, created_at, updated_at, user_name, user_email;

                                    product_id = response.body().getProduct().get(i).getProduct_id();
                                    brand = response.body().getProduct().get(i).getBrand();
                                    model = response.body().getProduct().get(i).getModel();
                                    price = response.body().getProduct().get(i).getPrice();
                                    date = response.body().getProduct().get(i).getDate();
                                    time = response.body().getProduct().get(i).getTime();
                                    location = response.body().getProduct().get(i).getLocation();
                                    phone = response.body().getProduct().get(i).getPhone();
                                    recycler = response.body().getProduct().get(i).getRecycler();
                                    user_id = response.body().getProduct().get(i).getUser_id();
                                    created_at = response.body().getProduct().get(i).getCreated_at();
                                    updated_at = response.body().getProduct().get(i).getUpdated_at();
                                    user_name = response.body().getProduct().get(i).getUser_name();
                                    user_email = response.body().getProduct().get(i).getUser_email();
                                    status = response.body().getProduct().get(i).getStatus();

                                    list.add(new HistoryModule(product_id, brand, model, price, date, time, location, phone, recycler, status, user_id, created_at, updated_at, user_name, user_email));

                                }
                            }

                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerView.setAdapter(new RejectedHistoryAdapter(list, context));

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.errorBody() != null) {
                    try {
                        Toast.makeText(context, "" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {

                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}