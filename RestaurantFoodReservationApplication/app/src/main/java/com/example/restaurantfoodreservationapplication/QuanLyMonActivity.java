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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class QuanLyMonActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView add_btn;
    String dish_url;
    Bitmap bitmap;
    HashMap<String,String> hashMap = new HashMap<>();
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    final String userID=firebaseUser.getUid();
    EditText code_res, name_res, price_res, purl_res;
    boolean flagadd = false;
    DatabaseReference monan_id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_quan_ly_mon);
        setContentView(R.layout.activity_quan_ly_mon);

        toolbar = findViewById(R.id.toolbar);
        add_btn = findViewById(R.id.add_btn);
        code_res = findViewById(R.id.code_res);
        name_res = findViewById(R.id.name_res);
        price_res = findViewById(R.id.price_res);
        purl_res = findViewById(R.id.purl_res);
        monan_id = FirebaseDatabase.getInstance().getReference("User").child(userID).child("monan_id");
        add_btn.setOnClickListener(v -> {
            addtofirebase();
        });
        //show back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

    }

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
            /*hashMap.put("GiaMon",price_res.getText().toString());
            hashMap.put("MaDM",code_res.getText().toString());
            hashMap.put("TenMon",name_res.getText().toString());
            hashMap.put("Url",purl_res.getText().toString());*/
            /*String id = UUID.randomUUID().toString();
            monan_id.child(id).setValue(hashMap);
            dbr.child(id).setValue(hashMap);
            monan_id.child(id).setValue(true);*/
            flagadd=false;
            dialog.dismiss();
            dialog.setMessage("Added Successfully");
            startActivity(intent);
        }
    }

    private void StoreLink(String url){
        ProgressDialog dialog = new ProgressDialog(this);
        if(flagadd == true){
            Intent intent = new Intent(QuanLyMonActivity.this, DatMonActivity.class);
            DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("MonAn");
            hashMap.put("GiaMon",price_res.getText().toString());
            hashMap.put("MaDM",code_res.getText().toString());
            hashMap.put("TenMon",name_res.getText().toString());
            hashMap.put("Url",purl_res.getText().toString());
            String id = UUID.randomUUID().toString();
            monan_id.child(id).setValue(hashMap);
            dbr.child(id).setValue(hashMap);
            monan_id.child(id).setValue(true);
            flagadd=false;
            dialog.dismiss();
            dialog.setMessage("Added Successfully");
            startActivity(intent);
        }
    }




























}
