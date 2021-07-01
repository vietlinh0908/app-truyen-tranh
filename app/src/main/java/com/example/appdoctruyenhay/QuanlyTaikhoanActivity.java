package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyenhay.database.CustomAdapter;
import com.example.appdoctruyenhay.database.database;
import com.example.appdoctruyenhay.moldel.Taikhoan;

import java.util.List;

public class QuanlyTaikhoanActivity extends AppCompatActivity {

    private EditText edttaikhoannguoidung,edtmatkhaunguoidung,edtemailnguoidung,edtphanquyennguoidung,edtidnguoidung;
    private ListView listViewnguoidung;
   private Button btnThemnguoidung, btnSuanguoidung, btnXoanguoidung;
   CustomAdapter customAdapter;
    List<Taikhoan> taikhoans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_taikhoan);

        database databasenguoidung =new database(this);
        Anhxa();
        taikhoans=databasenguoidung.getAllTaikhoan();
        customAdapter=new CustomAdapter(this,R.layout.item_nguoidung,taikhoans);
        listViewnguoidung.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        listViewnguoidung.invalidateViews();

        btnThemnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taikhoan taikhoan= createTaikhoan();
                if(taikhoan != null){
                    databasenguoidung.AddTaikhoan(taikhoan);
                }
                customAdapter.notifyDataSetChanged();
                listViewnguoidung.invalidateViews();
            }
        });

            btnSuanguoidung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=edtidnguoidung.getText().toString();
                    String tentaikhoan = edttaikhoannguoidung.getText().toString();
                    String matkhau = edtmatkhaunguoidung.getText().toString();
                    String email = edtemailnguoidung.getText().toString();
                    String  phanquyen =edtphanquyennguoidung.getId()+"";
                    Boolean update = databasenguoidung.updateTaikhoan(id, tentaikhoan, matkhau, email,phanquyen);
                    if (update == true) {
                        Toast.makeText(QuanlyTaikhoanActivity.this, " Đã sửa vào dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuanlyTaikhoanActivity.this, " sai thông tin", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            btnXoanguoidung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=edtidnguoidung.getText().toString();

                    // Integer phanquyen = edtphanquyennguoidung.getId();
                    // float gia = Float.parseFloat(EditText2.getText().toString());
                    Boolean delete = databasenguoidung.deleteTaikhoan(id);
                    listViewnguoidung.invalidateViews();

                    if (delete == true) {
                        Toast.makeText(QuanlyTaikhoanActivity.this, " Đã xoá vào dữ liệu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QuanlyTaikhoanActivity.this, " sai thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }


    private void Anhxa() {
        edtidnguoidung=findViewById(R.id.edt_idnguoidung);
        edttaikhoannguoidung=findViewById(R.id.edt_tentaikhoannguoidung);
        edtmatkhaunguoidung=findViewById(R.id.edt_tenmatkhaunguoidung);
        edtemailnguoidung=findViewById(R.id.edt_emailnguoidung);
        edtphanquyennguoidung=findViewById(R.id.edt_phanquyennguoidung);
        listViewnguoidung=findViewById(R.id.listview_nguoidung);
        btnThemnguoidung=findViewById(R.id.btn_Savenguoidung);
        btnSuanguoidung=findViewById(R.id.btn_Updatenguoidung);
        btnXoanguoidung=findViewById(R.id.btn_Deletenguoidung);
    }
    private Taikhoan createTaikhoan(){
        String tentaikhoan= edttaikhoannguoidung.getText().toString().trim();//kiểu 1
        String matkhau=edtmatkhaunguoidung.getText().toString().trim();
        String email = edtemailnguoidung.getText()+"";//kiểu 2
        Integer phanquyen= edtphanquyennguoidung.getId();
        Taikhoan taikhoan= new Taikhoan(tentaikhoan,matkhau,email, phanquyen);
        return taikhoan;
    }

}