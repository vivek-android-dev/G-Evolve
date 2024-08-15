package com.saveetha.g_evolve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.modules.QueriesModule;

import java.util.ArrayList;

public class AdminQueriesAdapter extends RecyclerView.Adapter<AdminQueriesAdapter.MyViewHolder> {

    ArrayList<QueriesModule> list;
    Context context;


    public AdminQueriesAdapter(ArrayList<QueriesModule> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminQueriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.queries_item_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminQueriesAdapter.MyViewHolder holder, int position) {

        QueriesModule item = list.get(position);

        holder.name.setText("Name:"+item.getName());
        holder.message.setText("Message:"+item.getMessage());
        holder.email.setText("Email:"+item.getEmail());
        holder.date.setText(item.getDate());



    }

    @Override
    public int getItemCount() {

//        if (list == null) {
//            return 0;
//        }
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,message,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTV);
            email = itemView.findViewById(R.id.emailTV);
            message = itemView.findViewById(R.id.messageTV);
            date = itemView.findViewById(R.id.dateTV);


        }
    }
}
