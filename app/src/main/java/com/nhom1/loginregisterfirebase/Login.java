package com.nhom1.loginregisterfirebase;

import static android.content.ContentValues.TAG;

import static com.nhom1.loginregisterfirebase.MainActivity.encodeTobase64;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nhom1.loginregisterfirebase.models.LoginModal;
import com.nhom1.loginregisterfirebase.models.LoginResponse;
import com.nhom1.loginregisterfirebase.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://face-recognition-89b43-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    EditText signInEmailTextInput;
    EditText passwordTextInput;
    Button signInButton, btnFaceId;
    TextView forgotPasswordButton;
    TextView registerNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        SharedPreferences preferences = getSharedPreferences("app", 0);
        signInEmailTextInput = findViewById(R.id.signInEmailTextInput);
        passwordTextInput = findViewById(R.id.signInPasswordTextInput);
        signInButton = findViewById(R.id.signInButton);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        registerNow = findViewById(R.id.registerNow);
        btnFaceId = findViewById(R.id.btnFaceId);

        btnFaceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity( new Intent(Login.this, FaceRegconitionActivity.class));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);

                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Forgot password", Toast.LENGTH_SHORT).show();
            }
        });


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt = signInEmailTextInput.getText().toString();
                final String passwordTxt = passwordTextInput.getText().toString();
//
                if (emailTxt.contentEquals("")) {
                    Toast.makeText(Login.this, "Email cant be empty", Toast.LENGTH_SHORT).show();
                } else if (passwordTxt.contentEquals("")) {
                    Toast.makeText(Login.this, "Password cant be empty", Toast.LENGTH_SHORT).show();
                } else {
                    LoginModal user = new LoginModal(emailTxt, passwordTxt);
                    RetrofitInstance.getApi().login(user).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if(response.isSuccessful() && response.body()!=null){
                                LoginResponse response1 = response.body();
                                Toast.makeText(Login.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                                getSharedPreferences("app", 0).edit().putString("fullName", response1.getFullName()).apply();
                                getSharedPreferences("app", 0).edit().putString("email", emailTxt).apply();
                                finish();
                                startActivity(new Intent(Login.this, MainActivity.class));
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                        }
                    });

                }


            }
        });
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String base64 = encodeTobase64(photo);

            Log.d(TAG, "base64 img: "+base64);
        }
    }
}