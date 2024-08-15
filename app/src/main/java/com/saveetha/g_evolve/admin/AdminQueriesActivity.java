package com.saveetha.g_evolve.admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.saveetha.g_evolve.R;
import com.saveetha.g_evolve.adapter.AdminQueriesAdapter;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityAdminQueriesBinding;
import com.saveetha.g_evolve.modules.QueriesModule;
import com.saveetha.g_evolve.responses.ShowAllMessageResponse;
import com.saveetha.g_evolve.utils.LastItemBottomMarginDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminQueriesActivity extends AppCompatActivity {

    ActivityAdminQueriesBinding binding;

    ArrayList<QueriesModule> list;

    String name, email, message, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAdminQueriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        loadQueries();

    }

    private void loadQueries() {

        Call<ShowAllMessageResponse> res = RetroClient.makeApi().showAllMessages();

        res.enqueue(new Callback<ShowAllMessageResponse>() {
            @Override
            public void onResponse(Call<ShowAllMessageResponse> call, Response<ShowAllMessageResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("200")) {

                        try {
                            if (response.body().getQueries().size() != 0) {
                                list = new ArrayList<>();

                                for (int i = 0; i < response.body().getQueries().size(); i++) {

                                    name = response.body().getQueries().get(i).getUser_name();
                                    email = response.body().getQueries().get(i).getUser_email();
                                    message = response.body().getQueries().get(i).getMessage();
                                    date = response.body().getQueries().get(i).getDate_query();

                                    list.add(new QueriesModule(name, email, message, date));
                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AdminQueriesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("AdminQueriesActivityexec", "onResponse: " + e.getMessage());
                        }

                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        int bottomMargin = getResources().getDimensionPixelSize(R.dimen.queries_margin); // Define your margin in dimens.xml
                        binding.recyclerView.addItemDecoration(new LastItemBottomMarginDecoration(bottomMargin));
                        binding.recyclerView.setAdapter(new AdminQueriesAdapter(list, getApplicationContext()));

                    } else if (response.body().getMessage() != null) {
                        Toast.makeText(AdminQueriesActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else if (response.errorBody() != null) {

                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(AdminQueriesActivity.this, error, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(AdminQueriesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowAllMessageResponse> call, Throwable t) {
                Toast.makeText(AdminQueriesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}