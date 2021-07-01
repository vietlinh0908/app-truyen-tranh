package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyenhay.database.ContactAdapter;
import com.example.appdoctruyenhay.database.TruyenhayAdapter;
import com.example.appdoctruyenhay.database.database;
import com.example.appdoctruyenhay.moldel.ContactModel;
import com.example.appdoctruyenhay.moldel.Sachhay;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ImageView imgvdanhsachnhac;
    DrawerLayout drawerLayout;
    ListView listViewtruyenhay;
    ArrayList<Sachhay> arraysachHay;
    TruyenhayAdapter adapter;
    public static database database;
    //private ViewFlipper ViewFlipper1;

    int[] images = {
            R.drawable.doremon,
            R.drawable.dragonball,
            R.drawable.conan,
            R.drawable.olongvien
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchinh);
        toolbar=findViewById(R.id.toolbarmanhinh);
        viewFlipper=findViewById(R.id.viewflipper);
        listViewtruyenhay=findViewById(R.id.listviewtruyen);
        imgvdanhsachnhac=findViewById(R.id.imgv_music);
        //////////////sự kiện viewFlipper
        for(int i=0;i<images.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(images[i]);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setDisplayedChild(3000);//hiển thị trong bao lâu
        viewFlipper.setAutoStart(true);//bật chế độ auto chạy
        ActionBar();
        ActionViewFliper();
        ////////////
        database=new database(this);
        database.QueryData("CREATE TABLE IF NOT EXISTS TruyenHay( Id INTEGER PRIMARY KEY AUTOINCREMENT , TenTruyen VARCHAR(150) , TenTacGia VARCHAR(50) , TenTap VARCHAR(50) , NamSX VARCHAR(50) ,  HinhAnh BLOB)");
        //đối tượng
        database=new database(this);
        arraysachHay=new ArrayList<>();
        adapter=new TruyenhayAdapter(this,R.layout.item_sachhay,arraysachHay);
        listViewtruyenhay.setAdapter(adapter);
        ///get database
        Cursor cursor=database.GetData("SELECT * FROM TruyenHay");
        while (cursor.moveToNext()){
            arraysachHay.add(new Sachhay(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getBlob(5)
            ));
        }
        adapter.notifyDataSetChanged();
        listViewtruyenhay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Intent intent= new Intent(MainActivity.this, Noidungtruyen.class);
                    startActivityForResult(intent,111);

                    Toast.makeText(MainActivity.this, "Chúc bạn đọc truyện vui vẻ", Toast.LENGTH_SHORT).show();
                }
                if (position ==  1) {
                    Intent intent= new Intent(MainActivity.this, Noidungtruyen2.class);
                    startActivityForResult(intent,222);
                    Toast.makeText(MainActivity.this, "Chúc bạn đọc truyện vui vẻ", Toast.LENGTH_SHORT).show();
                }
                if (position ==  2) {
                    Intent intent= new Intent(MainActivity.this, Noidungtruyen3.class);
                    startActivityForResult(intent,333);
                    Toast.makeText(MainActivity.this, "Chúc bạn đọc truyện vui vẻ", Toast.LENGTH_SHORT).show();
                }
                if (position ==  3) {
                    Toast.makeText(MainActivity.this, "Instagram Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  4) {
                    Toast.makeText(MainActivity.this, "Youtube Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgvdanhsachnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NhacActivity.class);
                startActivity(intent);
            }
        });
    }


        private void ActionViewFliper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        //
    }
        private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);///nhảy ra giữa
            }
        });
    }
}

