package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;
import static com.example.restaurantfoodreservationapplication.RecyclerDonDatAdapter.soluong;

public class XemDonDatActivity extends AppCompatActivity {


    ImageView imgTangSL, imgGiamSL;
    RecyclerView recycler;
    RecyclerDonDatAdapter recyclerAdapter;
    ArrayList<Chi_Tiet_Don_Dat> dsMonDat;
   // ArrayList<Don_Dat_1_Ban> dsMonDatCuaBan;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xem_don_dat);
        setContentView(R.layout.recycler_chitietdondat);
        imgGiamSL = (ImageView) findViewById(R.id.imgGiamSL);
        imgTangSL = (ImageView) findViewById(R.id.imgTangSL);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("DonDat");
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabXacNhan = findViewById(R.id.fabXacNhan);
        fab.setOnClickListener(new View.OnClickListener() { //nut QUAY LAI
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XemDonDatActivity.this, DatMonActivity.class);
                startActivity(intent);
            }
        });
        fabXacNhan.setOnClickListener(new View.OnClickListener() {  //nut XAC NHAN DON DAT
            @Override
            public void onClick(View v) {
                Query query = mDatabase.child("DonDat"+MaBan).orderByChild("maBan").equalTo(MaBan); //Sua lai ban cho phu hop
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                            Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);
                            mDatabase.child("DonDat"+MaBan).child(singleSnapshot.getKey()).removeValue();
                            db.child("ThanhToan").push().setValue(ctdondat);
                        }
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
       /* TenMon =(TextView) findViewById(R.id.txtTenMon);
        GiaMon =(TextView) findViewById(R.id.txtGiaTien);
        HinhAnh =(ImageView) findViewById(R.id.imageView1);
        MonAn=(CardView) findViewById(R.id.cardview);*/
        recycler = (RecyclerView) findViewById(R.id.recyclerview_ctdd);
        // DatabaseReference ref = mDatabase.child("MonAn");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        // Query MonAnquery = ref.equalTo("CB001");
       // dsMonDat = new ArrayList<>();
        dsMonDat = new ArrayList<>();

        //array.add(new Mon_An( "","","",12));
        recyclerAdapter = new RecyclerDonDatAdapter(dsMonDat, this);
        recycler.setAdapter(recyclerAdapter);
        Query queryDonDat =  mDatabase.child("DonDat"+MaBan).orderByChild("maBan").equalTo(MaBan);//Lay ds mon da dat theo ban
        queryDonDat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);
                    //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                    dsMonDat.add(ctdondat);
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });  //VUA DONG CMT


       /* queryDonDat.addValueEventListener(new ValueEventListener() { //addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {  //chir laasy theo danh mujc
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);
                    //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                    dsMonDat.add(ctdondat);
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



        recyclerAdapter.setOnItemClickListener(new RecyclerDonDatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


               // Chi_Tiet_Don_Dat ctdondat = new Chi_Tiet_Don_Dat(dsMonDat.get(position).getDonGia(), dsMonDat.get(position).getMaBan(), soluong, dsMonDat.get(position).getTenMon());
               // HashMap childUpdates  = new HashMap();
               // childUpdates.put("soLuong", soluong);
                //String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
               // mDatabase.child("soLuong").setValue(soluong);
               //String key = mDatabase.push().getKey();
               // Query query =  mDatabase.orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());
                Query query = mDatabase.orderByChild("maBan").equalTo(MaBan);//.orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());
               // Query query = query1.orderByChild("maBan").equalTo(MaBan);
                Query query1 = query.getRef().orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());
                Query query2 = mDatabase.child("DonDat"+MaBan).orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());


               query2.addValueEventListener(new ValueEventListener() { //LOI TANG GIAM SL --> ANH HUONG TOI MON KHAC TRONG CUNG MA BAN
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       for (DataSnapshot singleSnapshot : snapshot.getChildren()) {

                           //Chi_Tiet_Don_Dat ctdondat = snapshot.getValue(Chi_Tiet_Don_Dat.class);
                            Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);//new Chi_Tiet_Don_Dat(dsMonDat.get(position).getDonGia(), MaBan dsMonDat, soluong, dsMonDat.get(position).getTenMon());

                            ctdondat.setSoLuong(soluong);
                       // ctdondat.setTenMon(dsMonDat.get(position).getTenMon());
                           //ctdondat.setSoLuong(soluong);
                            String key = singleSnapshot.getKey();
                            HashMap childUpdates  = new HashMap();
                            childUpdates.put(key, ctdondat);

                            mDatabase.child("DonDat"+MaBan).updateChildren(childUpdates);
                           //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                          // dsMonDat.add(ctdondat);
                       }
                       recyclerAdapter.notifyDataSetChanged();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
                // HashMap childUpdates  = new HashMap();
                // childUpdates.put(, soluong);
                // mDatabase.child(position).updateChildren(childUpdates);
               // FirebaseDatabase.getInstance().getReference().updateChildren(ctdondat);
            }
        });



                            //NHAP////////
     /*  Query query = mDatabase.orderByChild("maBan").equalTo(MaBan);
      // Query query1 = query.getRef().orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());
       queryDonDat.addValueEventListener(new ValueEventListener() { //LOI KHI TANG GIAM SL CUA MON KHAC CUNG MA BAN
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot singleSnapshot : snapshot.getChildren())
               { //CHI ANH HUONG SNAPSHOT CUOI CUNG
                   Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);//new Chi_Tiet_Don_Dat(dsMonDat.get(position).getDonGia(), MaBan *//**//*dsMonDat.get(position).getMaBan()*//**//*, soluong, dsMonDat.get(position).getTenMon());*//*
                   String key = singleSnapshot.getKey();

                    recyclerAdapter.setOnItemClickListener(new RecyclerDonDatAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            //Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);//new Chi_Tiet_Don_Dat(dsMonDat.get(position).getDonGia(), MaBan, soluong, dsMonDat.get(position).getTenMon());
                            ctdondat.setSoLuong(soluong);//(dsMonDat.get(position).getSoLuong());
                            //mDatabase.orderByChild("tenMon").equalTo(dsMonDat.get(position).getTenMon());

                            HashMap childUpdates  = new HashMap();
                            childUpdates.put(key, ctdondat);

                            mDatabase.updateChildren(childUpdates);
                            //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                            // dsMonDat.add(ctdondat);

                        }
                    });
                    //recyclerAdapter.notifyDataSetChanged();
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });  */


    }
}
