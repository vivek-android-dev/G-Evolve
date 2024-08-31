package com.saveetha.g_evolve.adapter;

import static com.saveetha.g_evolve.api.RetroClient.BASE_URL;
import static com.saveetha.g_evolve.api.RetroClient.makeApi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.admin.AddEducationActivity;
import com.saveetha.g_evolve.admin.EducationDetailsActivity;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.modules.EducationListModule;
import com.saveetha.g_evolve.responses.AddEducationResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        View view = ViewGroup.inflate(context, R.layout.education_details_layout, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_details_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        EducationListModule item = educationList.get(position);

        holder.titleTV.setText(item.getTitle());

        Glide.with(context)
                .load(BASE_URL + item.getImage())
                .placeholder(R.mipmap.placeholder)
                .error(R.mipmap.no_image_error)
                .into(holder.imageView);


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

        holder.editEduBtn.setOnClickListener(v -> deleteData(item.getEdu_id(),position));

    }

    private void deleteData(String eduId, int position) {


        Call<AddEducationResponse> res = RetroClient.makeApi().deleteEducation(eduId);

        res.enqueue(new Callback<AddEducationResponse>() {
            @Override
            public void onResponse(Call<AddEducationResponse> call, Response<AddEducationResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body().getStatus().equals("200")){
                        Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        educationList.remove(position);
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddEducationResponse> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
