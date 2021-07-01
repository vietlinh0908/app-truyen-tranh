package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CongthongtinActivity extends AppCompatActivity {

    ImageButton imgbtnCall,imgbtnFacebook;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congthongtin);
        context=this;
        Anhxa();
        imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0973305143"));
                startActivity(intent);
            }
        });

        imgbtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/phim.hay.y.nghia.nhat"));
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        imgbtnFacebook=findViewById(R.id.imgbtn_facebook);
        imgbtnCall=findViewById(R.id.imgbtn_call);
    }
}