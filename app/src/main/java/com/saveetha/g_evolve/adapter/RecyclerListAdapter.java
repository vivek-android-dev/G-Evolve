package com.saveetha.g_evolve.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.admin.EditRecyclerActivity;
import com.saveetha.g_evolve.modules.RecyclerListModule;

import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.MyViewHolder> {

    private List<RecyclerListModule> itemList;
    private Context context;

    public RecyclerListAdapter(List<RecyclerListModule> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_details_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        RecyclerListModule item = itemList.get(position);
        holder.textView1.setText(item.getCompanyName());
        holder.textView2.setText(item.getEmail());
        holder.textView3.setText(item.getAddress());
        holder.textView4.setText(item.getTime());
        holder.textView5.setText(item.getLocation());
        holder.textView6.setText(item.getContact());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sf = context.getSharedPreferences("recyclersf", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("recycler_id", item.getRecycler_id());
                editor.putString("companyName", item.getCompanyName());
                editor.putString("email", item.getEmail());
                editor.putString("capacity", item.getCapacity());
                editor.putString("address", item.getAddress());
                editor.putString("contact", item.getContact());
                editor.putString("time", item.getTime());
                editor.putString("location", item.getLocation());
                editor.putString("latitude", item.getLatitude());
                editor.putString("longitude", item.getLongitude());
                editor.putString("open_time", item.getOpen_time());
                editor.putString("close_time", item.getClose_time());
                editor.apply();


               context.startActivity(new Intent(context, EditRecyclerActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4, textView5, textView6;
        Button editBtn, deactivateBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.companyNameTV);
            textView2 = itemView.findViewById(R.id.emailTV);
            textView3 = itemView.findViewById(R.id.addressTV);
            textView4 = itemView.findViewById(R.id.timeTV);
            textView5 = itemView.findViewById(R.id.locationTV);
            textView6 = itemView.findViewById(R.id.contactTV);
            editBtn = itemView.findViewById(R.id.editBtn);
//            deactivateBtn = itemView.findViewById(R.id.deactivateBtn);

        }
    }
}

