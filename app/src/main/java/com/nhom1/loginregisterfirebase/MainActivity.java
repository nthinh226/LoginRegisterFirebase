package com.nhom1.loginregisterfirebase;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import com.nhom1.loginregisterfirebase.models.Post;
import com.nhom1.loginregisterfirebase.models.Response;
import com.nhom1.loginregisterfirebase.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText txtSoThuNhat, txtSoThuHai;
    private TextView txtKetQua;
    private Button btnTinhTong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSoThuNhat = findViewById(R.id.txtSoThuNhat);
        txtSoThuHai = findViewById(R.id.txtSoThuHai);
        txtKetQua = findViewById(R.id.txtKetQua);
        btnTinhTong = findViewById(R.id.btnTinhTong);






        btnTinhTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(txtSoThuNhat.getText().toString());
                int num2 = Integer.parseInt(txtSoThuHai.getText().toString());
                RetrofitInstance.getApi().getResult(num1, num2).enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);

                    }
                });
            }
        });
    }

    private void getAdd(int a, int b) {
        Post post = new Post(a, b);


    }    private void getHello() {


    }
}