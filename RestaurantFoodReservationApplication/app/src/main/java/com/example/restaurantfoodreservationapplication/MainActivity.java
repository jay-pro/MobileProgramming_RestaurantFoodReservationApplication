package com.example.restaurantfoodreservationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnQLNV,btnQLDatMon,btnQLMonAn,btnQLBan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQLNV = (Button) findViewById(R.id.btnQLNhanVien);
        btnQLDatMon = (Button) findViewById(R.id.btnQLDatmon);
        btnQLMonAn = (Button) findViewById(R.id.btnQLMonAn);
        btnQLBan = (Button) findViewById(R.id.btnQLBan);

        btnQLBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QLBanAnActivity.class);
                startActivity(intent);
            }
        });
        btnQLDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DatMonActivity.class);
                startActivity(intent);
            }
        });
        btnQLMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuanLyMonActivity.class);
                startActivity(intent);
            }
        });
        btnQLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QLNhanVienActivity.class);
                startActivity(intent);
            }
        });
    }
}