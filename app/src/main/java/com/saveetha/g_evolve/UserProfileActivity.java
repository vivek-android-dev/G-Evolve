package com.saveetha.g_evolve;

import static com.saveetha.g_evolve.api.RetroClient.BASE_URL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityUserProfileBinding;
import com.saveetha.g_evolve.responses.GetProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {

    ActivityUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sf = getSharedPreferences("usersf",MODE_PRIVATE);
        String userid = sf.getString("userid",null);

        SharedPreferences adminsf = getSharedPreferences("adminsf",MODE_PRIVATE);
        String adminid = adminsf.getString("userid",null);

        if(adminid != null){
            showProfile(adminid);
        } else if(userid != null){
            showProfile(userid);
        }

        binding.imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditUserProfileActivity.class));
            }

        });

    }

    private void showProfile(String id) {
        if(id != null){
            Call<GetProfileResponse> res = RetroClient.makeApi().profile(id);
            res.enqueue(new Callback<GetProfileResponse>() {
                @Override
                public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body().getStatus() == 200){
                            Glide.with(getApplicationContext())
                                    .load(BASE_URL+response.body().getData().getImage())
                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                    .placeholder(R.drawable.circle)
                                    .into(binding.profileImage);
                            binding.editusername.setText(response.body().getData().getName());
                            binding.editEmailAddress.setText(response.body().getData().getEmail());
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                    Toast.makeText(UserProfileActivity.this, "Internal error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}