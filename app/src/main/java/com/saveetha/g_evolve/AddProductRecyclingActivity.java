
package com.saveetha.g_evolve;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityAddProductRecyclingBinding;
import com.saveetha.g_evolve.responses.AddProductResponse;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductRecyclingActivity extends AppCompatActivity {

    private EditText editTextDate;

    private EditText editTextTime;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    ArrayList<String> list = new ArrayList<>();
    String[] stringArray;
    String userId;

    String brand, model, price, date, time, location, phone, recycler, user_id;

    ActivityAddProductRecyclingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddProductRecyclingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("selected_facility", MODE_PRIVATE);
        String facilityName = sharedPreferences.getString("facility_id", null);

        SharedPreferences usersf = getSharedPreferences("usersf", MODE_PRIVATE);
        userId = usersf.getString("userid", null);

        if (facilityName != null) {
            TextView textView = findViewById(R.id.title);
            textView.setText(facilityName);
        }

        LoadData();
        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define your back button behavior here
                finish(); // Close the current activity
            }
        });


        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setFocusable(false);
        editTextTime = findViewById(R.id.editTextTime);
        editTextTime.setFocusable(false);
        // Create a date picker dialog listener
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Display the selected date in the EditText
                String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                editTextDate.setText(selectedDate);
            }
        };

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                editTextTime.setText(selectedTime);
            }
        };


        // Set an OnClickListener on the EditText to show the date picker dialog
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnSubmit();
            }
        });


    }

    private void OnSubmit() {


        Button submit = findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateData()) {
                    Call<AddProductResponse> res = RetroClient.makeApi().addProduct(user_id, brand, model, price, date, time, location, phone, recycler);

                    res.enqueue(new Callback<AddProductResponse>() {
                        @Override
                        public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getStatus().equals("200")) {

                                    Toast.makeText(AddProductRecyclingActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else if (response.body().getMessage() != null) {
                                    Toast.makeText(AddProductRecyclingActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            } else if (response.errorBody() != null) {

                                try {
                                    Toast.makeText(AddProductRecyclingActivity.this, "" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddProductResponse> call, Throwable t) {
                            Toast.makeText(AddProductRecyclingActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }

    private boolean validateData() {

        boolean isValid = true;

        brand = binding.brandET.getText().toString();
        model = binding.modelET.getText().toString();
        price = binding.editTextText.getText().toString();
        date = binding.editTextDate.getText().toString();
        time = binding.editTextTime.getText().toString();
        location = binding.editTextTextPostalAddress.getText().toString();
        phone = binding.editTextPhone.getText().toString();
        recycler = binding.spinner3.getSelectedItem().toString();
        user_id = userId;

        if (user_id.isEmpty()) {
            isValid = false;
        }
        if (brand.isEmpty()) {
            isValid = false;
            binding.brandET.setError("Brand is required");
        }
        if (model.isEmpty()) {
            isValid = false;
            binding.modelET.setError("Model is required");
        }
        if (price.isEmpty()) {
            isValid = false;
            binding.editTextText.setError("Price is required");
        }
        if (date.isEmpty()) {
            isValid = false;
            binding.editTextDate.setError("Date is required");
        }
        if (time.isEmpty()) {
            isValid = false;
            binding.editTextTime.setError("Time is required");
        }
        if (location.isEmpty()) {
            isValid = false;
            binding.editTextTextPostalAddress.setError("Location is required");
        }
        if (phone.isEmpty()) {
            isValid = false;
            binding.editTextPhone.setError("Phone is required");
        }
        if (recycler.isEmpty()) {
            isValid = false;
        }


        return isValid;
    }

    private void LoadData() {
        Call<ShowAllRecyclerResponse> res = RetroClient.makeApi().showAllRecycler();

        res.enqueue(new Callback<ShowAllRecyclerResponse>() {
            @Override
            public void onResponse(Call<ShowAllRecyclerResponse> call, Response<ShowAllRecyclerResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        stringArray = new String[response.body().getRecycler().size()];
                        for (int i = 0; i < response.body().getRecycler().size(); i++) {
                            stringArray[i] = response.body().getRecycler().get(i).getCompany_name();
                            list.add(response.body().getRecycler().get(i).getCompany_name());


                            Log.d("AddProductRecyclingActivityReso", "onResponse: " + response.body().getRecycler().get(i).getCompany_name());
                        }

                        Spinner spinner = findViewById(R.id.spinner3);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProductRecyclingActivity.this, android.R.layout.simple_spinner_item, stringArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = parent.getItemAtPosition(position).toString();
//                                Toast.makeText(parent.getContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                Toast.makeText(AddProductRecyclingActivity.this, "nothing", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }

            }

            @Override
            public void onFailure(Call<ShowAllRecyclerResponse> call, Throwable t) {

                Toast.makeText(AddProductRecyclingActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        // Get the current date as default values for the date picker dialog
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, hour, minute, false);
        timePickerDialog.show();
    }
}
