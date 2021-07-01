package com.example.appdoctruyenhay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appdoctruyenhay.database.database;
import com.example.appdoctruyenhay.moldel.Taikhoan;

public class DangkyActivity extends AppCompatActivity {

    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangKy,btnDKtrove;
    ImageButton imgBtnPower;
    database databasedoctruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        anhXa();
        databasedoctruyen=new database(this);
        btnDKtrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan=edtDKTaiKhoan.getText().toString();
                String matkhau=edtDKMatKhau.getText().toString();
                String email=edtDKEmail.getText().toString();

                Taikhoan taikhoan1=CreateTaiKhoan();
                if(taikhoan.equals("")||matkhau.equals("")|| email.equals("")){
                    Log.e("Thông báo:","Chưa nhập đầy đủ thông tin");
                }else {
                    databasedoctruyen.AddTaikhoan(taikhoan1);
                    Toast.makeText(DangkyActivity.this,"Đăng kí thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    ///Phương thức tạo tài khoản
    private Taikhoan CreateTaiKhoan(){
        String taikhoan= edtDKTaiKhoan.getText().toString();
        String matkhau= edtDKMatKhau.getText().toString();
        String email= edtDKEmail.getText().toString();
        int phanquyen =1;
        Taikhoan tk = new Taikhoan(taikhoan,matkhau,email,phanquyen);
        return tk;
    }

    private void anhXa() {
        edtDKTaiKhoan=findViewById(R.id.edt_tk);
        edtDKMatKhau=findViewById(R.id.edt_mk);
        edtDKEmail=findViewById(R.id.edt_email);
        btnDKDangKy=findViewById(R.id.btn_dangky);
        btnDKtrove=findViewById(R.id.btn_trove);
        imgBtnPower=findViewById(R.id.imgBtnPower);

    }
    public void thongbaothoat(){
        androidx.appcompat.app.AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
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