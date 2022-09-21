package com.nhom1.loginregisterfirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import com.nhom1.loginregisterfirebase.models.Post;
import com.nhom1.loginregisterfirebase.models.Response;
import com.nhom1.loginregisterfirebase.network.RetrofitInstance;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    Button btnInfor, btnTrainingFace, btnLogout;
    ImageView click_image_id;
    private static final int pic_id = 123;
    public static final int MY_CAMERA_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInfor = findViewById(R.id.btnInfor);
        btnTrainingFace = findViewById(R.id.btnTrainingFace);
        btnLogout = findViewById(R.id.btnLogout);

        click_image_id = findViewById(R.id.click_image);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });

        btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Thông tin", Toast.LENGTH_SHORT).show();
            }
        });

        btnTrainingFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FaceTraningActivity.class));
//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
//                        == PackageManager.PERMISSION_DENIED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
//                } else {
//                    Toast.makeText(MainActivity.this, "Open Camera", Toast.LENGTH_SHORT).show();
//
//                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
////                        camera_intent.putExtra("android.intent.extras.CAMERA_FACING", android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
////                        camera_intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
////                        camera_intent.putExtra("android.intent.extras.USE_FRONT_CAMERA", true);
////                    } else {
////                        camera_intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
////                    }
//                    startActivityForResult(camera_intent, pic_id);
//                }

            }
        });
        // This method will help to retrieve the image


//        btnTinhTong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int num1 = Integer.parseInt(txtSoThuNhat.getText().toString());
//                int num2 = Integer.parseInt(txtSoThuHai.getText().toString());
//                RetrofitInstance.getApi().getResult(num1, num2).enqueue(new Callback<Response>() {
//                    @Override
//                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                        Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Response> call, Throwable t) {
//                        Log.e(TAG, "onFailure: ", t);
//
//                    }
//                });
//            }
//        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id && resultCode == Activity.RESULT_OK) {
            // BitMap is data structure of image file which store the image in memory
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

            // bitmap to base64
            String base64 = encodeTobase64(imageBitmap);
            Log.d(TAG, "base64: "+base64);

            // Set the image in imageview for display
            click_image_id.setImageBitmap(imageBitmap);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


}