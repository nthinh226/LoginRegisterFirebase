package com.nhom1.loginregisterfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    // create object of DatabaseReference class to access firebase's Realime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://face-recognition-89b43-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

    Button signUpButton;
    EditText signUpFullNameTextInput;
    EditText signUpPhoneTextInput;
    EditText signUpEmailTextInput;
    EditText signUpPasswordTextInput;
    CheckBox agreementCheckBox;
    TextView signInNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpFullNameTextInput = findViewById(R.id.signUpFullNameTextInput);
        signUpPhoneTextInput = findViewById(R.id.signUpPhoneTextInput);
        signUpEmailTextInput = findViewById(R.id.signUpEmailTextInput);
        signUpPasswordTextInput = findViewById(R.id.signUpPasswordTextInput);
        signUpButton = findViewById(R.id.signUpButton);
        signInNow = findViewById(R.id.signInNow);
        agreementCheckBox = findViewById(R.id.agreementCheckbox);

        signInNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnameTxt = signUpFullNameTextInput.getText().toString();
                final String emailTxt = signUpEmailTextInput.getText().toString();
                final String phoneTxt = signUpPhoneTextInput.getText().toString();
                final String passwordTxt = signUpPasswordTextInput.getText().toString();

                if (fullnameTxt.isEmpty()) {

                    Toast.makeText(Register.this, "Full name cannot be empty", Toast.LENGTH_SHORT).show();


                } else if (emailTxt.isEmpty()) {

                    Toast.makeText(Register.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();


                } else if (phoneTxt.isEmpty()) {

                    Toast.makeText(Register.this, "Phone cannot be empty", Toast.LENGTH_SHORT).show();


                } else if (passwordTxt.isEmpty()) {


                    Toast.makeText(Register.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();


                } else if (!agreementCheckBox.isChecked()) {

                    Toast.makeText(Register.this, "Please agree to terms and Condition", Toast.LENGTH_SHORT).show();


                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check if phone is not registered before
                            if (snapshot.hasChild(phoneTxt)) {
                                Toast.makeText(Register.this, "Phone is already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                // sending data to firebase realtime db
                                // we are using phone number as unique identity of every user
                                // so all the other details of user comes under phone number
                                databaseReference.child("users").child(phoneTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(phoneTxt).child("email").setValue(EncodeString(emailTxt));
                                databaseReference.child("users").child(phoneTxt).child("password").setValue(passwordTxt);

                                // show a success msg then finish the activity
                                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

            }
        });
    }

    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }

    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }
}