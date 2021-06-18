package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class MainActivity extends AppCompatActivity {

    Button btnQLNV,btnQLDatMon,btnQLMonAn,btnQLBan, btnThongKe;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnQLNV = (Button) findViewById(R.id.btnQLNhanVien);
        btnQLDatMon = (Button) findViewById(R.id.btnQLDatmon);
        btnQLMonAn = (Button) findViewById(R.id.btnQLMonAn);
        btnQLBan = (Button) findViewById(R.id.btnQLBan);
        btnThongKe = (Button) findViewById(R.id.btnThongKe);


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
                Intent intent = new Intent(MainActivity.this,DSBanActivity.class);
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
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ThongKeActivity.class);
                startActivity(intent);
            }
        });




    }
}