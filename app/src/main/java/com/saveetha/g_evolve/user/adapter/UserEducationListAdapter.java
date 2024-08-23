package com.saveetha.g_evolve.user.adapter;

import static com.saveetha.g_evolve.api.RetroClient.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.admin.EducationDetailsActivity;
import com.saveetha.g_evolve.modules.EducationListModule;

import java.util.ArrayList;

public class UserEducationListAdapter extends RecyclerView.Adapter<UserEducationListAdapter.MyViewHolder> {

    private ArrayList<EducationListModule> educationList;
    private Context context;

    public UserEducationListAdapter(ArrayList<EducationListModule> educationList, Context context) {
        this.educationList = educationList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = ViewGroup.inflate(context, R.layout.education_details_layout, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_details_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        EducationListModule item = educationList.get(position);

        holder.titleTV.setText(item.getTitle());

        Glide.with(context)
                .load(BASE_URL + item.getImage())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.no_image_error)
                .into(holder.imageView);

//

        holder.readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences SharedPreferences = context.getSharedPreferences("educationsf", Context.MODE_PRIVATE);
                SharedPreferences.edit().putString("title", item.getTitle()).apply();
                SharedPreferences.edit().putString("description", item.getDescription()).apply();
                SharedPreferences.edit().putString("image", item.getImage()).apply();

                context.startActivity(new Intent(context, EducationDetailsActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {

        if (educationList == null) {
            return 0;
        }
        return educationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;
        AppCompatImageView imageView;
        Button readMoreBtn, editEduBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.thumbnailIV);
            titleTV = itemView.findViewById(R.id.titleTV);
            readMoreBtn = itemView.findViewById(R.id.readMoreBtn);
            editEduBtn = itemView.findViewById(R.id.editEduBtn);

        }
    }
}
