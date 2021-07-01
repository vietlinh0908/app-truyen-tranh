package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Luachonadmin extends AppCompatActivity {
    Button btnQLtruyen,btnQLnguoidung,btnTroveTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luachonadmin);
        Anhxa();
        btnQLtruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Luachonadmin.this,Themtruyen.class);
                startActivity(intent);

            }
        });
        btnQLnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Luachonadmin.this,QuanlyTaikhoanActivity.class);
                startActivity(intent);
            }
        });
        btnTroveTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Luachonadmin.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void Anhxa() {
        btnQLnguoidung=findViewById(R.id.btn_quantringuoidung);
        btnQLtruyen=findViewById(R.id.btn_quantritruyentranh);
        btnTroveTC=findViewById(R.id.btn_trovetrangchu);
    }
}