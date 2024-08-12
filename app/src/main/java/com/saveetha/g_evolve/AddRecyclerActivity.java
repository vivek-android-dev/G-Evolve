package com.saveetha.g_evolve;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.AddRecyclerBinding;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecyclerActivity extends AppCompatActivity implements OnMapReadyCallback {

    AddRecyclerBinding binding;
    private GoogleMap mMap;
    String companyName, capacity, address, contact, email, openTime, closeTime, latitude, longitude, location;

    private boolean isMapFullscreen = false;
    private FrameLayout mapContainer;



    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = AddRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    addRecyclerData();
                }
            }
        });
    }



    private void addRecyclerData() {

        getText();
        Call<ShowAllRecyclerResponse> res = RetroClient.makeApi().addRecycler(companyName, capacity, address, email, contact, openTime, closeTime, latitude, longitude);

        res.enqueue(new Callback<ShowAllRecyclerResponse>() {
            @Override
            public void onResponse(Call<ShowAllRecyclerResponse> call, Response<ShowAllRecyclerResponse> response) {
                // Handle successful response
                if(response.isSuccessful()){
                    if(response.body().getStatus()==200){
                        Toast.makeText(AddRecyclerActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(response.body().getMessage()!=null){
                        Toast.makeText(AddRecyclerActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowAllRecyclerResponse> call, Throwable t) {
                // Handle failure
                Toast.makeText(AddRecyclerActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();

                latitude = String.valueOf(latLng.latitude);
                longitude = String.valueOf(latLng.longitude);

                Geocoder geocoder = new Geocoder(AddRecyclerActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String addressText = address.getAddressLine(0);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(addressText));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8f), 30, null);
                        binding.addressRecyclerTV.setText(addressText);
                        Toast.makeText(AddRecyclerActivity.this, addressText, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddRecyclerActivity.this, "No address found for this location", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddRecyclerActivity.this, "Error getting address: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void getText() {
        companyName = binding.companyNameET.getText().toString();
        capacity = binding.capacityET.getText().toString();
        address = binding.addressRecyclerTV.getText().toString();
        contact = binding.contactET.getText().toString();
        email = binding.emailET.getText().toString();
        openTime = binding.openTimeET.getText().toString();
        closeTime = binding.closeTimeET.getText().toString();
    }

    boolean validate() {
        getText();

        boolean isValid = true;

        if (companyName.isEmpty()) {
            binding.companyNameET.setError("Enter Company Name");
            isValid = false;
        }
        if (capacity.isEmpty()) {
            binding.capacityET.setError("Enter Capacity");
            isValid = false;
        }

        if (contact.isEmpty()) {
            binding.contactET.setError("Enter Contact");
            isValid = false;
        }
        if (email.isEmpty()) {
            binding.emailET.setError("Enter Email");
            isValid = false;
        }
        if (openTime.isEmpty()) {
            binding.openTimeET.setError("Enter Open Time");
            isValid = false;
        }
        if (closeTime.isEmpty()) {
            binding.closeTimeET.setError("Enter Close Time");
            isValid = false;
        }
        if (longitude == null || longitude.isEmpty()) {
            Toast.makeText(this, "Choose Location", Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        return isValid;
    }


}
