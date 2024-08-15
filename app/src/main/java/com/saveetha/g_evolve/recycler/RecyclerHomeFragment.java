package com.saveetha.g_evolve.recycler;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.databinding.FragmentRecyclerHomeBinding;

import java.util.ArrayList;

public class RecyclerHomeFragment extends Fragment {


    BarChart barChart;

    FragmentRecyclerHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRecyclerHomeBinding.inflate(inflater, container, false);


        barChart = binding.barChart;

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

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}