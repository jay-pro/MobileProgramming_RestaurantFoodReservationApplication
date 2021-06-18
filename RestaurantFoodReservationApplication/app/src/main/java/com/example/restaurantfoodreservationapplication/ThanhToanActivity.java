package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_HD;
import com.example.restaurantfoodreservationapplication.Class.Thanh_Toan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView recycler;
    TextView txtmaBan;
    RecyclerThanhToanAdapter recyclerAdapter;
    ArrayList<Chi_Tiet_HD> dsHoaDon;
    double tong = 0;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_donthanhtoan);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("ChuaThanhToan");
        txtmaBan = (TextView) findViewById(R.id.tvMaBan);
        txtmaBan.setText(MaBan);
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fabXacNhan = findViewById(R.id.fabXacNhan);

        recycler = (RecyclerView) findViewById(R.id.recyclerview_hoadon);
        // DatabaseReference ref = mDatabase.child("MonAn");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        // Query MonAnquery = ref.equalTo("CB001");
        // dsMonDat = new ArrayList<>();
        dsHoaDon = new ArrayList<>();

        //array.add(new Mon_An( "","","",12));
        recyclerAdapter = new RecyclerThanhToanAdapter(dsHoaDon, this);
        recycler.setAdapter(recyclerAdapter);
        Query queryHoaDon = mDatabase.child("DonBan" + MaBan);//.orderByChild("maBan").equalTo(MaBan);
        queryHoaDon.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                    Chi_Tiet_Don_Dat ctdondat = singleSnapshot.getValue(Chi_Tiet_Don_Dat.class);
                    //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                    Chi_Tiet_HD cthoadon = new Chi_Tiet_HD(ctdondat.getTenMon(), ctdondat.getSoLuong(), ctdondat.getSoLuong() * ctdondat.getDonGia(), ctdondat.getUrl());
                    dsHoaDon.add(cthoadon);
                    tong += ctdondat.getSoLuong() * ctdondat.getDonGia();
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fabXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HienTongTien(MaBan);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() { //nut QUAY LAI
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThanhToanActivity.this, DatMonActivity.class);
                startActivity(intent);
            }
        });

    }

    private void HienTongTien(String MaBanDat) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Tong tien cua ban so " + MaBanDat + " la: ");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage(tong + "VND. /nTien hanh thanh toan va in hoa don?");
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(calendar);

       // Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.DATE,Calendar.MONTH,Calendar.YEAR);
       // SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
       // dinhDangNgay.format(calendar.getTime());

        alertDialog.setPositiveButton("Xac nhan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query queryHoaDon = FirebaseDatabase.getInstance().getReference().child("DaThanhToan");
                queryHoaDon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Thanh_Toan thanhtoan = new Thanh_Toan(tong, formattedDate+"", MaBan);
                        mDatabase.child("DonBan" + MaBan).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("DaThanhToan").push().setValue(thanhtoan);
                        tong = 0; //dat lai so tien;
                        Toast.makeText(ThanhToanActivity.this, "Da in hoa don va thanh toan thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        alertDialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }


}