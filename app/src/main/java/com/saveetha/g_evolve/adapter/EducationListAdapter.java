package com.saveetha.g_evolve.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.modules.EducationListModule;

import java.util.ArrayList;

public class EducationListAdapter extends RecyclerView.Adapter<EducationListAdapter.MyViewHolder> {

    private ArrayList<EducationListModule> educationList;
    private Context context;

    public EducationListAdapter(ArrayList<EducationListModule> educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @NonNull
    @Override
    public EducationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = ViewGroup.inflate(context, R.layout.education_details_layout, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationListAdapter.MyViewHolder holder, int position) {

        Glide.with(context)
                .load(educationList.get(position).getImage())
                .into(holder.imageView);

        EducationListModule item = educationList.get(position);

        holder.titleTV.setText(item.getTitle());

        holder.readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // read more button click
            }
        });

    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV, decriptionTV;
        ImageView imageView;
        Button readMoreBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.thumbnailIV);
            titleTV = itemView.findViewById(R.id.titleTV);
            readMoreBtn = itemView.findViewById(R.id.readMoreBtn);

        }
    }
}
