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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.adapter.RecyclerListAdapter;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.FragmentAdminHomeBinding;
import com.saveetha.g_evolve.modules.RecyclerListModule;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;
import com.saveetha.g_evolve.utils.LastItemBottomMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminHomeFragment extends Fragment {

    private PieChart pieChart;
    private LineChart lineChart;

    FragmentAdminHomeBinding binding;
    ArrayList<RecyclerListModule> recyclerList;
    RecyclerListAdapter adapter;
    FragmentActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdminHomeBinding.bind(inflater.inflate(R.layout.fragment_admin_home, container, false));

        try {
            activity = getActivity();
        }catch (Exception e){
            e.printStackTrace();
        }

        pieChart = binding.pieChart;
        lineChart = binding.lineChart;

        setupPieChart();
        setupLineChart();

        binding.queriesIV.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AdminQueriesActivity.class));
        });

        return binding.getRoot();
    }

    private void setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(35f, "Smartphone Recycling"));
        pieEntries.add(new PieEntry(21f, "Laptop Recycling"));
        pieEntries.add(new PieEntry(15f, "Accessories Recycling"));
        pieEntries.add(new PieEntry(29f, "Television Recycling"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Recycling Data");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
    }

    private void setupLineChart() {
        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 10));
        entries1.add(new Entry(1, 20));
        entries1.add(new Entry(2, 30));
        entries1.add(new Entry(3, 40));
        entries1.add(new Entry(4, 50));
        entries1.add(new Entry(5, 40));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 30));
        entries2.add(new Entry(1, 20));
        entries2.add(new Entry(2, 10));
        entries2.add(new Entry(3, 50));
        entries2.add(new Entry(4, 40));
        entries2.add(new Entry(5, 30));

        LineDataSet dataSet1 = new LineDataSet(entries1, "DataSet 1");
        LineDataSet dataSet2 = new LineDataSet(entries2, "DataSet 2");

        dataSet1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        dataSet2.setColor(ColorTemplate.COLORFUL_COLORS[1]);

        LineData lineData = new LineData(dataSet1, dataSet2);
        lineChart.setData(lineData);
        lineChart.invalidate(); // refresh

        Legend legend = lineChart.getLegend();
        legend.setEnabled(true);
    }


    public void showAllRecycler() {

        // show all recycler using api

        Call<ShowAllRecyclerResponse> res = RetroClient.makeApi().showAllRecycler();

        res.enqueue(new Callback<ShowAllRecyclerResponse>() {
            @Override
            public void onResponse(Call<ShowAllRecyclerResponse> call, Response<ShowAllRecyclerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().status == 200) {

                        recyclerList = new ArrayList<>();
                        for (int i = 0; i < response.body().getRecycler().size(); i++) {
                            String recycler_id = String.valueOf(response.body().getRecycler().get(i).getRecycler_id());
                            String companyName = response.body().getRecycler().get(i).getCompany_name();
                            String capacity = response.body().getRecycler().get(i).getCapacity();
                            String email = response.body().getRecycler().get(i).getEmail();
                            String address = response.body().getRecycler().get(i).getAddress();
                            String contact = response.body().getRecycler().get(i).getContact();
                            String time = response.body().getRecycler().get(i).getOpen_time() + " - " + response.body().getRecycler().get(i).getClose_time();
                            String location = response.body().getRecycler().get(i).getAddress();
                            String latitude = response.body().getRecycler().get(i).getLatitude();
                            String longitude = response.body().getRecycler().get(i).getLongitude();
                            String open_time = response.body().getRecycler().get(i).getOpen_time();
                            String close_time = response.body().getRecycler().get(i).getClose_time();

                            recyclerList.add(new RecyclerListModule(recycler_id, companyName, capacity, email, contact, address, time, location, open_time, close_time, latitude, longitude));
                        }


                        adapter = new RecyclerListAdapter(recyclerList,activity);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        int bottomMargin = getResources().getDimensionPixelSize(R.dimen.bottom_margin); // Define your margin in dimens.xml
                        binding.recyclerView.addItemDecoration(new LastItemBottomMarginDecoration(bottomMargin));
                        binding.recyclerView.setAdapter(adapter);

                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowAllRecyclerResponse> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showAllRecycler();
    }
}