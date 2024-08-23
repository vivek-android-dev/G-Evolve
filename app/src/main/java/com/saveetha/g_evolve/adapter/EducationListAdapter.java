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

//        holder.editEduBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context, "" + item.getEdu_id(), Toast.LENGTH_SHORT).show();
//                Log.d("educationdetailsreso ", "onClick: " + item.getEdu_id());
//
//
////                RetroClient.makeApi().deleteEducation(item.getEdu_id());
//
//
//
//                String url = BASE_URL + "/api/deleteEducation/" + item.getEdu_id();
//
//                Log.d("educationdetailsreso ", "onClick: " + url);
//
//                Call<AddEducationResponse> res = RetroClient.makeApi().deleteEducation(url);
//
//                res.enqueue(new Callback<AddEducationResponse>() {
//                    @Override
//                    public void onResponse(Call<AddEducationResponse> call, Response<AddEducationResponse> response) {
//
//                        if (response.isSuccessful()) {
//                            if (response.body().getStatus().equals("200")) {
//                                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                Log.d("educationdetailsreso", "onResponse: succefully deleted " + response.body().getMessage());
//                                educationList.remove(position);
//                                notifyDataSetChanged();
//
//                            } else if (response.body().getMessage() != null) {
//                                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                                Log.d("educationdetailsreso", "onResponse on other: " + response.body().getMessage());
//
//                            }
//                        } else if (response.errorBody() != null) {
//
//                            try {
//                                String error = response.errorBody().string();
//
//                                Toast.makeText(context, "" +error, Toast.LENGTH_SHORT).show();
//                                Log.d("educationdetailsreso", "onResponse: error body " + error);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                Log.d("educationdetailsreso", "onResponse:  on exception " + e.getMessage());
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<AddEducationResponse> call, Throwable t) {
//                        Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.d("educationdetailsreso", "onFailure: " + t.getMessage());
//
//                    }
//                });
//
//            }
//
//        });


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
