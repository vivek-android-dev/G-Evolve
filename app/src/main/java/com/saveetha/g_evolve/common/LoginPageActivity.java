package com.saveetha.g_evolve.common;

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

import com.saveetha.g_evolve.CreateAccountActivity;
import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.recycler.RecyclerDashboardActivity;
import com.saveetha.g_evolve.user.UserDashboardActivity;
import com.saveetha.g_evolve.admin.AdminDashboardActivity;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.LoginPageBinding;
import com.saveetha.g_evolve.recycler.RecyclerLoginActivity;
import com.saveetha.g_evolve.responses.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPageActivity extends AppCompatActivity {
    Button login;

    LoginPageBinding binding;
    String email, password;
    String message;

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
            if (status.equals("Recycler")) {
                startActivity(new Intent(this, RecyclerDashboardActivity.class));
                finish();
            }
        }

        binding.recyclerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerLoginActivity.class));
            }
        });

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

        binding.button2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.button2.setElevation(20);
                } else {
                    binding.button2.setElevation(5);
                }
            }
        });

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                binding.button2.setElevation(10);
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
                                            finish();
                                        }
                                        if (response.body().getData().getUser_type().equals("User")) {
                                            SharedPreferences sf = getSharedPreferences("usersf", MODE_PRIVATE);
                                            sf.edit().putString("userid", response.body().getData().getUser_id()).apply();

                                            SharedPreferences signsf = getSharedPreferences("signsf", MODE_PRIVATE);
                                            signsf.edit().putString("issignedin", "User").apply();

                                            Toast.makeText(LoginPageActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginPageActivity.this, UserDashboardActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                } else if (response.body().getMessage() != null) {
                                    Toast.makeText(LoginPageActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            } else if (response.errorBody() != null) {
                                try {

                                    JSONObject jObjError = new JSONObject(Objects.requireNonNull(response.errorBody().string()));
                                    message = jObjError.getString("message");
                                    Toast.makeText(LoginPageActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Toast.makeText(LoginPageActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    Toast.makeText(LoginPageActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                            Toast.makeText(LoginPageActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

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