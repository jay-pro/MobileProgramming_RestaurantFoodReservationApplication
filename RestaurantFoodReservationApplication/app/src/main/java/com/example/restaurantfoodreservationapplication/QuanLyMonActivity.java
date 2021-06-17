package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class QuanLyMonActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button add_btn, update_btn, delete_btn;
    EditText code_res, name_res, price_res, purl_res, id_res;
    boolean flagadd = false, flagupdate = false, flagdelete = false;
    //DatabaseReference monan_id;
    //DatabaseReference ref;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_mon);
        toolbar = findViewById(R.id.toolbar);
        add_btn = findViewById(R.id.add_btn);
        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);
        code_res = findViewById(R.id.code_res);
        name_res = findViewById(R.id.name_res);
        price_res = findViewById(R.id.price_res);
        purl_res = findViewById(R.id.purl_res);
        id_res = findViewById(R.id.id_res);

        //Thêm món ăn
        add_btn.setOnClickListener(v -> {
            addtofirebase();
        });

        //Xóa món ăn
        /*Intent intent_xoa = getIntent();
        String key = intent_xoa.getStringExtra("key");
        ref = FirebaseDatabase.getInstance().getReference().child("MonAn");
        Query query_xoa = ref.orderByKey().equalTo(key);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot ds:snapshot.getChildren()){
                        code_res.setText(ds.child("giaMon").getValue(String.class));
                        name_res.setText(ds.child("tenMon").getValue(String.class));
                        price_res.setText(ds.child("giaMon").getValue(String.class));
                        purl_res.setText(ds.child("purl").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query_xoa.addListenerForSingleValueEvent(eventListener);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletemon(key);
            }
        });
        *//*delete_btn.setOnClickListener(v -> {
            deletewithid();
        });*//*

        //Sua mon an
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        delete_btn.setOnClickListener(v -> {
            //Cách 1: Nhập 4 thông tin của món rồi nhấn Xóa
            /*DatabaseReference xoa_giaMon = FirebaseDatabase.getInstance().getReference().getRoot().child("MonAn").child(price_res.toString());
            xoa_giaMon.setValue(null);
            DatabaseReference xoa_tenMon = FirebaseDatabase.getInstance().getReference().getRoot().child("MonAn").child(name_res.toString());
            xoa_giaMon.setValue(null);
            DatabaseReference xoa_maMon = FirebaseDatabase.getInstance().getReference().getRoot().child("MonAn").child(code_res.toString());
            xoa_giaMon.setValue(null);
            DatabaseReference xoa_purl = FirebaseDatabase.getInstance().getReference().getRoot().child("MonAn").child(purl_res.toString());
            xoa_giaMon.setValue(null);*/
            //Cách 2: Nhập id của món rồi nhấn Xóa
            //database.getReference("MonAn").child(id_res.toString()).removeValue();
            //Cách 3:
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Deleting");
            dialog.show();
            flagdelete = true;
            if(flagdelete == true){
                Intent intent = new Intent(QuanLyMonActivity.this, DatMonActivity.class);
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("MonAn");
                Mon_An mon_an = new Mon_An(Double.parseDouble(price_res.getText().toString()),code_res.getText().toString(),name_res.getText().toString(),purl_res.getText().toString());
                dbr.push().setValue(mon_an);//null
                flagadd=false;
                dialog.dismiss();
                dialog.setMessage("Added Successfully");
                startActivity(intent);
            }
        });

        update_btn.setOnClickListener(v -> {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Updating");
            dialog.show();
            flagupdate = true;
            if(flagupdate == true){
                Intent intent = new Intent(QuanLyMonActivity.this, DatMonActivity.class);
                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("MonAn");
                Mon_An mon_an = new Mon_An(Double.parseDouble(price_res.getText().toString()),code_res.getText().toString(),name_res.getText().toString(),purl_res.getText().toString());
                dbr.push().setValue(mon_an);
                flagupdate=false;
                dialog.dismiss();
                dialog.setMessage("Updated Successfully");
                startActivity(intent);
            }
        });

        //show back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

    }

    /*private void deletemon(String key) {
        ref = FirebaseDatabase.getInstance().getReference("MonAn").child(key);
        ref.removeValue();
        Toast.makeText(getApplicationContext(),"Successfully Deleted", Toast.LENGTH_LONG).show();
    }*/

    private void addtofirebase(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Adding");
        dialog.show();
        flagadd = true;
        if(flagadd == true){
            Intent intent = new Intent(QuanLyMonActivity.this, DatMonActivity.class);
            DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("MonAn");
            Mon_An mon_an = new Mon_An(Double.parseDouble(price_res.getText().toString()),code_res.getText().toString(),name_res.getText().toString(),purl_res.getText().toString());
            dbr.push().setValue(mon_an);
            flagadd=false;
            dialog.dismiss();
            dialog.setMessage("Added Successfully");
            startActivity(intent);
        }
    }

    /*private void deletewithid(){
        ref.child(id_res.toString()).removeValue();
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }*/



}
