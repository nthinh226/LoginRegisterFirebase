package com.nhom1.loginregisterfirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
                Toast.makeText(Login.this, "Face Id", Toast.LENGTH_SHORT).show();
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
}