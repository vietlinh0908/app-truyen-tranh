package com.example.appdoctruyenhay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdoctruyenhay.database.database;

public class DangnhapActivity extends AppCompatActivity {
    EditText edtTaiKhoan,edtMatKhau;
    Button btnDangnhap,btnDangKy;
    ImageButton imgBtnPower;
    ImageView imgvThongtinungdung;
    TextView textViewdoimk;
    //tạo đối tượng cho database
    public static database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        anhXa();
        database databasedoctruyen=new database(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DangnhapActivity.this,DangkyActivity.class);
                startActivity(intent);
            }
        });

        imgvThongtinungdung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DangnhapActivity.this,CongthongtinActivity.class);
                startActivity(intent);
            }
        });

        imgBtnPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongbaothoat();
            }
        });
        textViewdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DangnhapActivity.this,DoimatkhauActivity.class);
                startActivity(intent);
            }
        });

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String user = edtTaiKhoan.getText().toString();
                    String pass = edtMatKhau.getText().toString();
                    if (user.equals("") || pass.equals("")) {
                        Toast.makeText(DangnhapActivity.this, "Bạn nhập thiếu thông tin vui lòng nhập lại!!! ", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkuserpass = databasedoctruyen.checkusernamepassword(user, pass);
                        if (checkuserpass == true) {
                            if(user.equals("admin")) {
                                Toast.makeText(DangnhapActivity.this, "Admin đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangnhapActivity.this, Luachonadmin.class);
                                startActivity(intent);
                            }
                            else  {
                                Toast.makeText(DangnhapActivity.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangnhapActivity.this, MainActivity.class);
                                startActivity(intent);

                            }
                        } else {

                            Toast.makeText(DangnhapActivity.this, "Tài khoản hoặc mật khẩu không chính xác, Xin vui lòng thử lại ", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {e.printStackTrace();}
            }
        });



        ///xin cấp quyền
        int permission_internet = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        int permission_send_sms = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permission_camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int permission_call = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);

        if (permission_internet != PackageManager.PERMISSION_GRANTED
                || permission_send_sms != PackageManager.PERMISSION_GRANTED
                || permission_camera != PackageManager.PERMISSION_GRANTED
                || permission_call !=PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        }
    }

    private void anhXa() {
        textViewdoimk=findViewById(R.id.tv_doimk);
        edtTaiKhoan=findViewById(R.id.edt_tk);
        edtMatKhau=findViewById(R.id.edt_mk);
        btnDangKy=findViewById(R.id.btn_dangky);
        btnDangnhap=findViewById(R.id.btn_dangnhap);
        imgBtnPower=findViewById(R.id.imgBtnPower);
        imgvThongtinungdung=findViewById(R.id.imgv_thongtinungdung);
    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                Manifest.permission.SEND_SMS, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE}, 1);
    }
    public void thongbaothoat(){
        androidx.appcompat.app.AlertDialog.Builder
                alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("Thông báo !!");
        alerDialog.setIcon(R.mipmap.ic_launcher);
        alerDialog.setMessage("Bạn có muốn thoát ?");
        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                System.exit(0);

            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerDialog.show();

    }
}