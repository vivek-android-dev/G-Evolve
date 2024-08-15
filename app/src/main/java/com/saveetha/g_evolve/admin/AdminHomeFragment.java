package com.saveetha.g_evolve.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.saveetha.g_evolve.databinding.FragmentAdminHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class AdminHomeFragment extends Fragment {

    private PieChart pieChart;
    private LineChart lineChart;

    FragmentAdminHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAdminHomeBinding.bind(inflater.inflate(R.layout.fragment_admin_home, container, false));


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
}