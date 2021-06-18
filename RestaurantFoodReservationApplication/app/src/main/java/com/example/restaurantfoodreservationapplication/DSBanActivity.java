package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class DSBanActivity extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerBanAdapter recyclerAdapter;
    ArrayList<Ban_An> dsBan;
    Button btnQuayLui;
    // ArrayList<Don_Dat_1_Ban> dsMonDatCuaBan;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.item_dsban);
        setContentView(R.layout.recyclerview_ban);
        MaBan = "";
        btnQuayLui = (Button) findViewById(R.id.btnQuayLui);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("BanAn");
        recycler = (RecyclerView) findViewById(R.id.recycler_ban);
        // DatabaseReference ref = mDatabase.child("MonAn");

        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        //recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        // Query MonAnquery = ref.equalTo("CB001");
        // dsMonDat = new ArrayList<>();
        dsBan = new ArrayList<>();

        //array.add(new Mon_An( "","","",12));
        recyclerAdapter = new RecyclerBanAdapter(dsBan, this);
        recycler.setAdapter(recyclerAdapter);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                {
                    for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                        Ban_An ban_an = singleSnapshot.getValue(Ban_An.class);
                        dsBan.add(ban_an);
                    }
                    recyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerAdapter.setOnItemClickListener(new RecyclerBanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MaBan = dsBan.get(position).getMaBan();
                Intent intent = new Intent(DSBanActivity.this, DatMonActivity.class);
                startActivity(intent);
            }
        });
        btnQuayLui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DSBanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}