package com.saveetha.g_evolve;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.saveetha.g_evolve.api.RetroClient;
import com.saveetha.g_evolve.databinding.ActivityEditUserProfileBinding;
import com.saveetha.g_evolve.responses.EditProfileResponse;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserProfileActivity extends AppCompatActivity {

    ActivityEditUserProfileBinding binding;
    static final int REQUEST_CODE_SELECT_IMAGE = 1;
    public static final int REQUEST_CODE_PERMISSION = 2;
    String userid;
    RequestBody name, email, user_id;
    MultipartBody.Part image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityEditUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sf = getSharedPreferences("usersf", MODE_PRIVATE);
        String uuserid = sf.getString("userid", null);

        SharedPreferences adminsf = getSharedPreferences("adminsf", MODE_PRIVATE);
        String adminid = adminsf.getString("userid", null);

        if (adminid != null) {
            userid = adminid;
        } else if (uuserid != null) {
            userid = uuserid;
        }


        binding.profileImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION);
            } else {
                openImageSelector();
            }
        });

        binding.btnUpdate.setOnClickListener(v -> {
            updateProfile();
        });


    }

    private void updateProfile() {
        GetData();

        if (userid != null) {

            Call<EditProfileResponse> res = RetroClient.makeApi().updateProfile(user_id, image, name, email);
            res.enqueue(new Callback<EditProfileResponse>() {
                @Override
                public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals("200")) {
                            Toast.makeText(EditUserProfileActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Response Image", "onResponse: "+ response.body().getPath());
//                            Toast.makeText(EditUserProfileActivity.this, response.body().getPath(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if(response.body().getMessage() != null){
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<EditProfileResponse> call, Throwable t) {

                    Toast.makeText(EditUserProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void GetData() {
        email = RequestBody.create(MediaType.parse("text/plain"), binding.editEmailAddress.getText().toString());
        user_id = RequestBody.create(MediaType.parse("text/plain"), userid);
        name = RequestBody.create(MediaType.parse("text/plain"), binding.editusername.getText().toString());
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
                binding.profileImage.setImageBitmap(bitmap);

                    File file = uriToFile(imageUri);
                    RequestBody reqFile = RequestBody.create(MediaType.parse("*/*"), file);
                    image = MultipartBody.Part.createFormData("image", file.getName(), reqFile);


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