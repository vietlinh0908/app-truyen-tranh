package com.example.appdoctruyenhay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Themtruyen extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText edtTentruyen, edtTaptruyen,edtTacgia,edtNamsx;
    ImageButton ibtnCamera,ibtnFolder;
    ImageView imgHinh;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtruyen);
        AnhXa();
        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyển data của imageviews sang mảng byte[]
                BitmapDrawable bitmapDrawable =(BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap= bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray= new ByteArrayOutputStream();
                ///định dạng lại kiểu dữ liệu để xuất ra
                ///bao gồm kiểu hình PNG, chất lượng 100(giống ảnh thật,nếu chỉnh nét hơn thì chỉnh nhỏ hơn 100 đến 1)
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh= byteArray.toByteArray();
                ////////////////////////
                MainActivity.database.INSERT_TRUYEN_HAY(
                        edtTentruyen.getText().toString().trim(),
                        edtTacgia.getText().toString().trim(),
                        edtTaptruyen.getText().toString().trim(),
                        edtNamsx.getText().toString().trim(),
                        hinhAnh
                );
                Toast.makeText(Themtruyen.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Themtruyen.this,MainActivity.class));
            }
        });
    }

    private void AnhXa() {
        btnAdd=findViewById(R.id.btn_themtruyen);
        btnCancel=findViewById(R.id.btn_huytruyen);
        edtTentruyen=findViewById(R.id.edt_tentruyen);
        edtTaptruyen=findViewById(R.id.edt_tentap);
        edtTacgia=findViewById(R.id.edt_tentacgia);
        edtNamsx=findViewById(R.id.edt_namsx);
        imgHinh=findViewById(R.id.imv_anhthat);
        ibtnCamera=findViewById(R.id.imgbtn_camera);
        ibtnFolder=findViewById(R.id.imgbtn_folder);
    }

    ////in ảnh ra màn hình(chưa lưu vào sqlite)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode==RESULT_OK && data != null){
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_FOLDER && resultCode== RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}