package com.example.g_evolve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.g_evolve.api.RetroClient;
import com.example.g_evolve.databinding.CreateAccountBinding;
import com.example.g_evolve.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    CreateAccountBinding binding;

    String name,email,password,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetTExt();

                Call<RegisterResponse> res = RetroClient.makeApi().register(name,email,password,confirm_password);

                res.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if(response.isSuccessful()){
                            if(response.body().getStatus().equals("200")){

                                Toast.makeText(CreateAccountActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateAccountActivity.this, LoginPageActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(CreateAccountActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }

    private void GetTExt() {

       name = binding.editTextName.getText().toString();
       email = binding.editTextEmail.getText().toString();
       password = binding.editTextPassword.getText().toString();
       confirm_password = binding.editTextConfirmPassword.getText().toString();

    }
}