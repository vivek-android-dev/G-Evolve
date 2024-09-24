package com.saveetha.g_evolve.admin;

import static com.saveetha.g_evolve.api.RetroClient.BASE_URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.databinding.ActivityEducationDetailsBinding;

public class EducationDetailsActivity extends AppCompatActivity {

    String title, description, image;

    ActivityEducationDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityEducationDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> onBackPressed());

        SharedPreferences sf = getSharedPreferences("educationsf", Context.MODE_PRIVATE);
        title = sf.getString("title", null);
        description = sf.getString("description", null);
        image = sf.getString("image", null);

        if (title != null && description != null && image != null) {
            binding.titleTV.setText(title);
            binding.descriptionTV.setText(description);
            Glide.with(this)
                    .load(BASE_URL + image)
                    .placeholder(R.drawable.recycle_icon)
                    .error(R.drawable.recycle_icon)
                    .into(binding.thumbnailIV);
        } else {
            Toast.makeText(this, "Error Loading Details", Toast.LENGTH_SHORT).show();
        }


    }
}