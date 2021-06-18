package com.example.restaurantfoodreservationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.example.restaurantfoodreservationapplication.QuanLyMonActivity.ten_mon;

public class SuaMonActivity extends AppCompatActivity {

    Button update_up_btn;
    EditText code_up_res, name_up_res, price_up_res, purl_up_res;
    DatabaseReference ref_up;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_suamonan);
        update_up_btn = findViewById(R.id.update_up_btn);
        code_up_res = findViewById(R.id.code_up_res);
        name_up_res = findViewById(R.id.name_up_res);
        price_up_res = findViewById(R.id.price_up_res);
        purl_up_res = findViewById(R.id.purl_up_res);
        ref_up = FirebaseDatabase.getInstance().getReference().child("MonAn");
        Query query = ref_up.orderByChild("tenMon").equalTo(ten_mon);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Mon_An mon_an = ds.getValue(Mon_An.class);
                    name_up_res.setText(mon_an.getTenMon());
                    code_up_res.setText(mon_an.getMaDM());
                    price_up_res.setText(mon_an.getGiaMon()+"", TextView.BufferType.EDITABLE);
                    purl_up_res.setText(mon_an.getUrl());
                    key = ds.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update_up_btn.setOnClickListener(v -> {
            Mon_An monAn = new Mon_An(Double.parseDouble(price_up_res.getText().toString()),code_up_res.getText().toString(),name_up_res.getText().toString(),purl_up_res.getText().toString());
            HashMap childUpdates = new HashMap();
            childUpdates.put(key, monAn);
            ref_up.updateChildren(childUpdates);
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SuaMonActivity.this, QuanLyMonActivity.class);
            startActivity(intent);
        });

    }


}
