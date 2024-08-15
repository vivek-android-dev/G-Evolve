package com.saveetha.g_evolve.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityContactUsBinding;
import com.saveetha.g_evolve.responses.ShowAllMessageResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {

    ActivityContactUsBinding binding;

    String name,email,message,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityContactUsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateText()){

                    Call<ShowAllMessageResponse> res = RetroClient.makeApi().sendMessage(user_id,message);

                    res.enqueue(new Callback<ShowAllMessageResponse>() {
                        @Override
                        public void onResponse(Call<ShowAllMessageResponse> call, Response<ShowAllMessageResponse> response) {
                            if(response.isSuccessful()){

                                if(response.body().getStatus().equals("200")){

                                    Toast.makeText(ContactUsActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                } else if (response.body().getMessage()!=null) {

                                    Toast.makeText(ContactUsActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else if (response.errorBody() != null) {

                                try {
                                    String string = response.errorBody().string();
                                    Toast.makeText(ContactUsActivity.this, ""+string, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Toast.makeText(ContactUsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ShowAllMessageResponse> call, Throwable t) {
                            Toast.makeText(ContactUsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });



    }

    private boolean validateText() {

        boolean isValid = true;
        name = binding.nameET.getText().toString();
        email = binding.emailET.getText().toString();
        message = binding.messageET.getText().toString();

        if(name.isEmpty()){

            binding.nameET.setError("can't be empty");
            isValid = false;
        }
        if(email.isEmpty()){
            binding.emailET.setError("can't be empty");
            isValid = false;
        }
        if(message.isEmpty()){
            binding.messageET.setError("can't be empty");
            isValid = false;
        }

        return isValid;
    }
}