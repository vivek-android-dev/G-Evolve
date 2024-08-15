package com.saveetha.g_evolve.admin;

import static com.saveetha.g_evolve.EditUserProfileActivity.REQUEST_CODE_PERMISSION;
import static com.saveetha.g_evolve.EditUserProfileActivity.REQUEST_CODE_SELECT_IMAGE;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityAddEducationBinding;
import com.saveetha.g_evolve.responses.AddEducationResponse;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEducationActivity extends AppCompatActivity {

    ActivityAddEducationBinding binding;

    MultipartBody.Part image;

    RequestBody title, description, user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAddEducationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.thumbnailET.setOnClickListener(view -> {
            selectImage();
        });

        binding.submitBtn.setOnClickListener(view -> {
            submit();
        });

    }

    private void submit() {
        if(validate()){

            Call<AddEducationResponse> res = RetroClient.makeApi().addEducation(title,image,description);

            res.enqueue(new Callback<AddEducationResponse>() {
                @Override
                public void onResponse(Call<AddEducationResponse> call, Response<AddEducationResponse> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals("200")) {
                            Log.d("AddEducationResActivityResponse", "onResponse: " +response.body().getMessage());
                            Toast.makeText(AddEducationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (Objects.requireNonNull(response.body().getMessage()!=null)) {

                            Toast.makeText(AddEducationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("AddEducationResActivityResponse", "onResponse: status something" +response.body().getMessage());


                        }
                    } else if (response.errorBody() != null) {

                        try {
                            Toast.makeText(AddEducationActivity.this, ""+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            Log.d("AddEducationResActivityResponse", "onResponse: errorbody" +response.body().getMessage());

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddEducationResponse> call, Throwable t) {
                    Toast.makeText(AddEducationActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("AddEducationResActivityResponse", "onResponse: failure" +t.getMessage());

                }
            });
        }
    }

    private boolean validate() {
        getBody();

        boolean isValid = true;

        if (binding.headingET.getText().toString().isEmpty()) {
            binding.headingET.setError("Enter Heading");
            isValid = false;
        }
        if (binding.descriptionET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Write Something In Description", Toast.LENGTH_SHORT).show();
            isValid =  false;
        }
        if (binding.thumbnailET.getText().toString().isEmpty()) {
            binding.thumbnailET.setError("Select Thumbnail");
            isValid = false;
        }
        return isValid;
    }

    private void getBody() {
        title = RequestBody.create(MultipartBody.FORM, binding.headingET.getText().toString());
        description = RequestBody.create(MultipartBody.FORM, binding.descriptionET.getText().toString());
    }

    private void selectImage() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        } else {
            openImageSelector();
        }
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                binding.cardView.setVisibility(View.VISIBLE);
                binding.profileImage.setImageBitmap(bitmap);

                File file = uriToFile(imageUri);
                RequestBody reqFile = RequestBody.create(MediaType.parse("*/*"), file);
                image = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

                binding.thumbnailET.setText(file.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageSelector();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File uriToFile(Uri uri) {
        File file = null;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            file = new File(filePath);
        }
        return file;
    }
}