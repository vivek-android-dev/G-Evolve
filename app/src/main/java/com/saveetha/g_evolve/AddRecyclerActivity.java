package com.saveetha.g_evolve;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.saveetha.g_evolve.databinding.AddRecyclerBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddRecyclerActivity extends AppCompatActivity implements OnMapReadyCallback {

    private NestedScrollView nestedScrollView;
    AddRecyclerBinding binding;
    private GoogleMap mMap;
    String companyName, capacity, address, contact, email, openTime, closeTime, location;

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

        nestedScrollView = findViewById(R.id.nestedScrollView);

        FrameLayout mapContainer = findViewById(R.id.mapContainer);
        mapContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;
                    default:
                        return true;
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();

                addRecyclerData();
            }
        });

    }

    private void addRecyclerData() {
        // Add recycler data to using api



    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                // Get the address
                Geocoder geocoder = new Geocoder(AddRecyclerActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String addressText = address.getAddressLine(0);
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

    void GetText() {

        companyName = binding.companyNameET.getText().toString();
        capacity = binding.capacityET.getText().toString();
        address = binding.addressET.getText().toString();
        contact = binding.contactET.getText().toString();
        email = binding.emailET.getText().toString();
        openTime = binding.openTimeET.getText().toString();
        closeTime = binding.closeTimeET.getText().toString();
        location = binding.addressRecyclerTV.getText().toString();

    }

    void validate() {
        GetText();

        if (companyName.isEmpty()) {
            binding.companyNameET.setError("Enter Company Name");
        }
        if (capacity.isEmpty()) {
            binding.capacityET.setError("Enter Capacity");
        }
        if (address.isEmpty()) {
            binding.addressET.setError("Enter Address");
        }
        if (contact.isEmpty()) {
            binding.contactET.setError("Enter Contact");
        }
        if (email.isEmpty()) {
            binding.emailET.setError("Enter Email");
        }
        if (openTime.isEmpty()) {
            binding.openTimeET.setError("Enter Open Time");
        }
        if (closeTime.isEmpty()) {
            binding.closeTimeET.setError("Enter Close Time");
        }
        if (location.isEmpty()) {
            binding.addressRecyclerTV.setError("Select Location");
        }
    }
}