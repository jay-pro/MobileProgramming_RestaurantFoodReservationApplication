package com.example.restaurantfoodreservationapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMonActivity extends AppCompatActivity {

    CardView MonAn;
    TextView TenMon, GiaMon;
    ImageView HinhAnh;
    RecyclerView recycler;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Mon_An> dsMon;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MonAn");
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       /* TenMon =(TextView) findViewById(R.id.txtTenMon);
        GiaMon =(TextView) findViewById(R.id.txtGiaTien);
        HinhAnh =(ImageView) findViewById(R.id.imageView1);
        MonAn=(CardView) findViewById(R.id.cardview);*/
        recycler = (RecyclerView) findViewById(R.id.recyclerview);
       // DatabaseReference ref = mDatabase.child("MonAn");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
       // Query MonAnquery = ref.equalTo("CB001");
        dsMon = new ArrayList<>();

        //array.add(new Mon_An( "","","",12));
        recyclerAdapter = new RecyclerAdapter(dsMon,this);
        recycler.setAdapter(recyclerAdapter);
        mDatabase.addValueEventListener(new ValueEventListener() { //addListenerForSingleValueEvent
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot singleSnapshot : snapshot.getChildren()){
            Mon_An mon = singleSnapshot.getValue(Mon_An.class);
            dsMon.add(mon);
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    /*mDatabase.addValueEventListener(new ValueEventListener() {

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot singleSnapshot : snapshot.getChildren()){
            Mon_An mon = singleSnapshot.getValue(Mon_An.class);
            dsMon.add(mon);

        }
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
    });*/
       /* ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
*/
    }
}