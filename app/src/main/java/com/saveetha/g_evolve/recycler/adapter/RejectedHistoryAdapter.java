package com.saveetha.g_evolve.recycler.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.recycler.module.HistoryModule;

import java.util.ArrayList;

public class RejectedHistoryAdapter extends RecyclerView.Adapter<RejectedHistoryAdapter.ViewHolder> {

    ArrayList<HistoryModule> historyModuleArrayList;
    Context context;
    View view;

    public RejectedHistoryAdapter(ArrayList<HistoryModule> historyModuleArrayList, Context context) {
        this.historyModuleArrayList = historyModuleArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RejectedHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {




        View rejectedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_rejectedrecyclerview_cell, parent, false);


        return new ViewHolder(rejectedView);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RejectedHistoryAdapter.ViewHolder holder, int position) {

        HistoryModule item = historyModuleArrayList.get(position);

        holder.brandTv.setText(item.getBrand());
        holder.modelTV.setText(item.getModel());
        holder.priceTV.setText(item.getPrice());
        holder.dateTV.setText(item.getDate());
        holder.timeTV.setText(item.getTime());
        holder.locationTV.setText(item.getLocation());
        holder.recyclerTV.setText(item.getRecycler());
        holder.contactTV.setText(item.getPhone());


    }

    @Override
    public int getItemCount() {
        if (historyModuleArrayList == null) {
            return 0;
        }
        return historyModuleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView brandTv, modelTV, priceTV, dateTV, timeTV, locationTV, recyclerTV, contactTV;


        @SuppressLint("ResourceType")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brandTv = itemView.findViewById(R.id.brandTV);
            modelTV = itemView.findViewById(R.id.modelTV);
            priceTV = itemView.findViewById(R.id.priceTV);
            dateTV = itemView.findViewById(R.id.dateTV);
            timeTV = itemView.findViewById(R.id.timeTV);
            locationTV = itemView.findViewById(R.id.locationTV);
            recyclerTV = itemView.findViewById(R.id.recyclerTV);
            contactTV = itemView.findViewById(R.id.contactTV);

        }
    }
}
