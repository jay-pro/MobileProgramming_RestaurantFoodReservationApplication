package com.example.restaurantfoodreservationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class QuanLyNhanVienActivity extends AppCompatActivity{
    Button btnThemNV, btnXoaNV, btnSuaNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_nhanvien);

        btnThemNV = (Button) findViewById(R.id.buttonThemNV);
        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openPageThemNV();
            }
        });
        btnSuaNV = (Button) findViewById(R.id.buttonSuaNV);
        btnSuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openPageSuaNV();
            }
        });
        btnXoaNV = (Button) findViewById(R.id.buttonXoaNV);
        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openPageXoaNV();
            }
        });

    }


    /*
    public void openPageThemNV(){
        Intent intent = new Intent(this, ThemNhanVien.class);
        startActivity(intent);
    }
    public void openPageSuaNV(){
        Intent intent = new Intent(this, SuaNhanVien.class);
        startActivity(intent);
    }
    public void openPageXoaNV(){
        Intent intent = new Intent(this, XoaNhanVien.class);
        startActivity(intent);
    }
    */
}