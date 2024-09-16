package com.saveetha.g_evolve.recycler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.FragmentRecyclerHomeBinding;
import com.saveetha.g_evolve.recycler.adapter.PendingHistoryAdapter;
import com.saveetha.g_evolve.recycler.module.HistoryModule;
import com.saveetha.g_evolve.responses.AddProductResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerHomeFragment extends Fragment {

    String recycler_id, recycler_name;

    Context context;
    FragmentActivity activity;

    ArrayList<HistoryModule> list;
    BarChart barChart;

    FragmentRecyclerHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRecyclerHomeBinding.inflate(inflater, container, false);

        try {
            context = getContext();
            activity = getActivity();

        } catch (Exception e) {
            e.printStackTrace();
        }

        barChart = binding.barChart;



        SharedPreferences SharedPreferences = getActivity().getSharedPreferences("recyclersf", Context.MODE_PRIVATE);
        recycler_id = SharedPreferences.getString("userid", null);
        recycler_name = SharedPreferences.getString("username", null);

        LoadData();
        loadchart();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void loadchart() {

        // Create a list of BarEntry objects for each month and each category
        ArrayList<BarEntry> smartphoneEntries = new ArrayList<>();
        smartphoneEntries.add(new BarEntry(0, 50));
        smartphoneEntries.add(new BarEntry(1, 30));
        smartphoneEntries.add(new BarEntry(2, 60));
        smartphoneEntries.add(new BarEntry(3, 40));
        smartphoneEntries.add(new BarEntry(4, 70));
        smartphoneEntries.add(new BarEntry(5, 90));
        smartphoneEntries.add(new BarEntry(6, 80));

        ArrayList<BarEntry> laptopEntries = new ArrayList<>();
        laptopEntries.add(new BarEntry(0, 30));
        laptopEntries.add(new BarEntry(1, 20));
        laptopEntries.add(new BarEntry(2, 50));
        laptopEntries.add(new BarEntry(3, 30));
        laptopEntries.add(new BarEntry(4, 60));
        laptopEntries.add(new BarEntry(5, 85));
        laptopEntries.add(new BarEntry(6, 70));

        ArrayList<BarEntry> accessoriesEntries = new ArrayList<>();
        accessoriesEntries.add(new BarEntry(0, 20));
        accessoriesEntries.add(new BarEntry(1, 10));
        accessoriesEntries.add(new BarEntry(2, 40));
        accessoriesEntries.add(new BarEntry(3, 25));
        accessoriesEntries.add(new BarEntry(4, 50));
        accessoriesEntries.add(new BarEntry(5, 75));
        accessoriesEntries.add(new BarEntry(6, 65));

        ArrayList<BarEntry> televisionEntries = new ArrayList<>();
        televisionEntries.add(new BarEntry(0, 15));
        televisionEntries.add(new BarEntry(1, 20));
        televisionEntries.add(new BarEntry(2, 30));
        televisionEntries.add(new BarEntry(3, 35));
        televisionEntries.add(new BarEntry(4, 55));
        televisionEntries.add(new BarEntry(5, 80));
        televisionEntries.add(new BarEntry(6, 90));

        // Create a BarDataSet for each category
        BarDataSet smartphoneSet = new BarDataSet(smartphoneEntries, "Smartphone");
        smartphoneSet.setColor(Color.BLUE);

        BarDataSet laptopSet = new BarDataSet(laptopEntries, "Laptop");
        laptopSet.setColor(Color.GREEN);

        BarDataSet accessoriesSet = new BarDataSet(accessoriesEntries, "Accessories");
        accessoriesSet.setColor(Color.MAGENTA);

        BarDataSet televisionSet = new BarDataSet(televisionEntries, "Television");
        televisionSet.setColor(Color.YELLOW);

        // Add all datasets to the barData
        BarData data = new BarData(smartphoneSet, laptopSet, accessoriesSet, televisionSet);

        // Set data to the chart
        barChart.setData(data);

        // Set X-axis labels
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July"};
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(months));

        // Configure chart settings
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);

        // Legend configuration
        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        barChart.animateY(1000);
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


                                }


                            }
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            binding.recyclerView.setAdapter(new PendingHistoryAdapter(list, context));


                        } catch (Exception e) {
//                            throw new RuntimeException(e);
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
                        Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}