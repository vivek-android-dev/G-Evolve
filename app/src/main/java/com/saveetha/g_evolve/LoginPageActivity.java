package com.saveetha.g_evolve;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.LoginPageBinding;
import com.saveetha.g_evolve.responses.LoginResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPageActivity extends AppCompatActivity {
    Button login;

    LoginPageBinding binding;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sign = getSharedPreferences("signsf", MODE_PRIVATE);
        String status = sign.getString("issignedin", null);

        if (status != null) {
            if (status.equals("User")) {
                startActivity(new Intent(this, UserDashboardActivity.class));
                finish();
            }
            if (status.equals("Admin")) {
                startActivity(new Intent(this, AdminDashboardActivity.class));
                finish();

            }
        }


        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(LoginPageActivity.this, HomepageActivity.class);
//                startActivity(intent);
                GetText();
                if (validation()) {
                    Call<LoginResponse> responseCall = RetroClient.makeApi().login(email, password);

                    responseCall.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getStatus().equals("200")) {
                                    if (response.body().getMessage().equals("Login successfull")) {

                                        if (response.body().getData().getUser_type().equals("Admin")) {

                                            SharedPreferences sf = getSharedPreferences("adminsf", MODE_PRIVATE);
                                            sf.edit().putString("userid", response.body().getData().getUser_id()).apply();

                                            SharedPreferences signsf = getSharedPreferences("signsf", MODE_PRIVATE);
                                            signsf.edit().putString("issignedin", "Admin").apply();

                                            Toast.makeText(LoginPageActivity.this, "Admin Login successfull ", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginPageActivity.this, AdminDashboardActivity.class);
                                            startActivity(intent);
                                        }
                                        if (response.body().getData().getUser_type().equals("User")) {
                                            SharedPreferences sf = getSharedPreferences("usersf", MODE_PRIVATE);
                                            sf.edit().putString("userid", response.body().getData().getUser_id()).apply();

                                            SharedPreferences signsf = getSharedPreferences("signsf", MODE_PRIVATE);
                                            signsf.edit().putString("issignedin", "User").apply();

                                            Toast.makeText(LoginPageActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginPageActivity.this, UserDashboardActivity.class);
                                            startActivity(intent);
                                        }
                                        if (response.body().getStatus() != null) {
                                            Toast.makeText(LoginPageActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                } else if (response.body().getStatus().equals("400")) {
                                    Toast.makeText(LoginPageActivity.this, "Wrong Crenditials or User not found", Toast.LENGTH_SHORT).show();

                                }
                            }
                            ;
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
                }

            }
        });
        TextView textView6 = findViewById(R.id.textView6);
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validation() {
        GetText();
        if (email.isEmpty()) {
            binding.editTextEmail.setError("Cant be empty");
            return false;
        }
        if (password.isEmpty()) {
            binding.editTextPassword.setError("Cant be empty");
            return false;
        }
        return true;
    }

    private void GetText() {

        email = binding.editTextEmail.getText().toString();
        password = binding.editTextPassword.getText().toString();
    }
}