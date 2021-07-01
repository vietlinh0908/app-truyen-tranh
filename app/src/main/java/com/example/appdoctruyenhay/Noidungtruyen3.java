package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appdoctruyenhay.database.ContactAdapter;
import com.example.appdoctruyenhay.moldel.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class Noidungtruyen3 extends AppCompatActivity {

    private ListView lvContact;
    private List<ContactModel> listContact = new ArrayList<>();
    //Khi làm việc với ListView thường sẽ có 3 bước chính
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noidungtruyen);
        //Bước 1: Tạo data
        initData();

        //Bước 2: Tạo adapter ở ví dụ này chúng ta tự tạo một Adapter không phụ thuộc vào Adapter có sẵn
        ContactAdapter adapter = new ContactAdapter(listContact, this);

        //Bước 3: Tạo ListView Sét adapter vào ListView
        lvContact = (ListView) findViewById(R.id.lv_contact);
        lvContact.setAdapter(adapter);

        //Bắt sự kiện click vào ListView  dòng bao nhiêu nhận biết thông qua position
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactModel contactModel = listContact.get(position);
                Toast.makeText(Noidungtruyen3.this, "Bạn đang đọc truyện conan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        listContact.add(new ContactModel( R.drawable.conan1));
        listContact.add(new ContactModel( R.drawable.conan2));
        listContact.add(new ContactModel( R.drawable.conan3));
        listContact.add(new ContactModel( R.drawable.conan4));
        listContact.add(new ContactModel( R.drawable.conan5));
        listContact.add(new ContactModel( R.drawable.conan6));
        listContact.add(new ContactModel( R.drawable.conan7));
        listContact.add(new ContactModel( R.drawable.conan8));
    }
}