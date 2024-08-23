package com.saveetha.g_evolve.recycler.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.recycler.module.HistoryModule;
import com.saveetha.g_evolve.responses.AddProductResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingHistoryAdapter extends RecyclerView.Adapter<PendingHistoryAdapter.ViewHolder> {

    ArrayList<HistoryModule> historyModuleArrayList;
    Context context;
    View view;

    public PendingHistoryAdapter(ArrayList<HistoryModule> historyModuleArrayList, Context context) {
        this.historyModuleArrayList = historyModuleArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View pendingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_pendingrecyclerview_cell, parent, false);


        return new ViewHolder(pendingView);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull PendingHistoryAdapter.ViewHolder holder, int position) {

        HistoryModule item = historyModuleArrayList.get(position);

        holder.brandTv.setText(item.getBrand());
        holder.modelTV.setText(item.getModel());
        holder.priceTV.setText(item.getPrice());
        holder.dateTV.setText(item.getDate());
        holder.timeTV.setText(item.getTime());
        holder.locationTV.setText(item.getLocation());
        holder.recyclerTV.setText(item.getRecycler());
        holder.contactTV.setText(item.getPhone());

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<AddProductResponse> res = RetroClient.makeApi().acceptProduct(item.getProduct_id());

                res.enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                historyModuleArrayList.remove(position); // Remove the item from the list
                                notifyItemRemoved(position); // Notify the adapter of item removal
                                notifyItemRangeChanged(position, historyModuleArrayList.size());
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
        });

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<AddProductResponse> res = RetroClient.makeApi().rejectProduct(item.getProduct_id());
                res.enqueue(new Callback<AddProductResponse>() {
                    @Override
                    public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){
                                Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                historyModuleArrayList.remove(position); // Remove the item from the list
                                notifyItemRemoved(position); // Notify the adapter of item removal
                                notifyItemRangeChanged(position, historyModuleArrayList.size());
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
        });


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

        Button acceptBtn, rejectBtn;

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
            acceptBtn = itemView.findViewById(R.id.acceptBtn);
            rejectBtn = itemView.findViewById(R.id.rejectBtn);


        }
    }
}
