package com.saveetha.g_evolve.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.PendingFragmentBinding;
import com.saveetha.g_evolve.recycler.adapter.PendingHistoryAdapter;
import com.saveetha.g_evolve.recycler.module.HistoryModule;
import com.saveetha.g_evolve.responses.AddProductResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PendingFragment extends Fragment {

    PendingFragmentBinding binding;
    String recycler_id, recycler_name;

    Context context;

    ArrayList<HistoryModule> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = PendingFragmentBinding.bind(inflater.inflate(R.layout.pending_fragment, container, false));

        SharedPreferences SharedPreferences = getActivity().getSharedPreferences("recyclersf", Context.MODE_PRIVATE);
        recycler_id = SharedPreferences.getString("userid", null);
        recycler_name = SharedPreferences.getString("username", null);

//        SharedPreferences SharedPreferences1 = getActivity().getSharedPreferences("historyVsf", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = SharedPreferences1.edit();
//        editor.putString("historyType", "pendingView");
//        editor.apply();

        try {
            context = getContext();



        } catch (Exception e) {
            e.printStackTrace();
        }

        LoadData();
        return binding.getRoot();
    }

    private void LoadData() {

        Call<AddProductResponse> res = RetroClient.makeApi().showProduct(recycler_name);

        res.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {
                        try {
                            list = new ArrayList<>();
                            Log.d("pendinglist", "onResponse: " + response.body().getProduct().size());
                            for (int i = 0; i < response.body().getProduct().size(); i++) {
                                if (response.body().getProduct().get(i).getStatus().equals("0")) {

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

//                                    Log.d("pendinglist", "onResponse: " + list.get(i).getBrand() + " " + list.get(i).getModel() + " " + list.get(i).getPrice() + " " + list.get(i).getDate() + " " + list.get(i).getTime() + list.get(i).getStatus());

                                }


                            }
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerView.setAdapter(new PendingHistoryAdapter(list, context));


                        } catch (Exception e) {
                            throw new RuntimeException(e);
//                            e.printStackTrace();
//                            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            Log.d("pendinglist", "onResponse: on try list" + e.getMessage());
                        }


                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("pendinglist", "onResponse: on else list" + response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {

                    try {
                        Toast.makeText(context, "" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.d("pendinglist", "onResponse: on else if list" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("pendinglist", "onResponse: on else if list catch" + e.getMessage());

                    }
                }
            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("pendinglist", "onResponse: on failure list" + t.getMessage());

            }
        });
    }
}