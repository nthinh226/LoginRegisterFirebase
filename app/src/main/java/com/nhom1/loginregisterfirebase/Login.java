package com.nhom1.loginregisterfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://face-recognition-89b43-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

    EditText signInPhoneTextInput;
    EditText passwordTextInput;
    Button signInButton;
    TextView forgotPasswordButton;
    TextView registerNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInPhoneTextInput = findViewById(R.id.signInPhoneTextInput);
        passwordTextInput = findViewById(R.id.signInPasswordTextInput);
        signInButton = findViewById(R.id.signInButton);
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        registerNow = findViewById(R.id.registerNow);

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Forgot password", Toast.LENGTH_SHORT).show();
            }
        });


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = signInPhoneTextInput.getText().toString();
                final String passwordTxt = passwordTextInput.getText().toString();

                if (signInPhoneTextInput.getText().toString().contentEquals("")) {


                    Toast.makeText(Login.this, "Email cant be empty", Toast.LENGTH_SHORT).show();


                } else if (passwordTextInput.getText().toString().contentEquals("")) {

                    Toast.makeText(Login.this, "Password cant be empty", Toast.LENGTH_SHORT).show();

                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if phone is exist in firebase database
                            if(snapshot.hasChild(phoneTxt)){
                                // phone is exist in firebase db
                                // now get password of user from firebase data and match it with user entered password
                                final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);
                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(Login.this, "Succesfully logged in", Toast.LENGTH_SHORT).show();
                                    // open MainActivity on success
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(Login.this, "Wrong pass", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Login.this, "Wrong phone number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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