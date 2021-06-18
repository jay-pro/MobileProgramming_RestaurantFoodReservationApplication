package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
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

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class QuanLyMonActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button add_btn, update_btn, delete_btn;
    EditText code_res, name_res, price_res, purl_res, id_res;
    boolean flagadd = false, flagupdate = false, flagdelete = false;
    //DatabaseReference monan_id;
    //DatabaseReference ref;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public static String ten_mon;

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
        delete_btn.setOnClickListener(v -> {
            Query query = database.child("MonAn").orderByChild("tenMon").equalTo(name_res.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren())
                    {
                        Mon_An mon_an= singleSnapshot.getValue(Mon_An.class);
                        database.child("MonAn").child(singleSnapshot.getKey()).removeValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        });

        update_btn.setOnClickListener(v -> {
            /*Query query = database.child("MonAn").orderByChild("tenMon").equalTo(name_res.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot singleSnapshot : snapshot.getChildren())
                    {
                        Mon_An mon_an= singleSnapshot.getValue(Mon_An.class);
                        database.child("MonAn").child(singleSnapshot.getKey()).removeValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/
            ten_mon = name_res.getText().toString();



            Intent intent = new Intent(QuanLyMonActivity.this, SuaMonActivity.class);
            startActivity(intent);
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
            flagadd=false;
            dialog.dismiss();
            dialog.setMessage("Added Successfully");
            startActivity(intent);
        }
    }


}
