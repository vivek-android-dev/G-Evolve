package com.saveetha.g_evolve.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityRecyclerLoginBinding;
import com.saveetha.g_evolve.responses.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerLoginActivity extends AppCompatActivity {

    ActivityRecyclerLoginBinding binding;
    String email, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRecyclerLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.backBtn.setOnClickListener(view -> {
            onBackPressed();
        });


        onclicklistener();

    }

    private void onclicklistener() {

        binding.loginBtn.setOnClickListener(view -> {

            GetText();
            recyclerLogin();

        });
    }

    private void recyclerLogin() {

        Call<LoginResponse> res = RetroClient.makeApi().recyclerLogin(email, contact);

        res.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {
                        if (response.body().getMessage().equals("Login successfull")) {

                            Toast.makeText(RecyclerLoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RecyclerLoginActivity.this, RecyclerDashboardActivity.class));
                            finish();

                        }

                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(RecyclerLoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.errorBody() != null) {
                    try {
                        JSONObject jObjError = new JSONObject(Objects.requireNonNull(response.errorBody().string()));
                        String message = jObjError.getString("message");
                        Toast.makeText(RecyclerLoginActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(RecyclerLoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetText() {
        email = binding.editTextEmail.getText().toString();
        contact = binding.contactET.getText().toString();
    }
}