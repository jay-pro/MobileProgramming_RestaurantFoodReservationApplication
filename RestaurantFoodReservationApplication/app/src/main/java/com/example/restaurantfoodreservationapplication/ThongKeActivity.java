package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class ThongKeActivity extends AppCompatActivity {
    Spinner spinnerLoaiThongKe;
    EditText edtDate;
    TextView TongTien;
    Button btnLoc;
    RecyclerView recycler;
    RecyclerDonThanhToanTrongNgay recyclerAdapter;

    ArrayList<Thanh_Toan> dsHoaDonTrongNgay;
    double tongTienNgay = 0;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        spinnerLoaiThongKe = (Spinner) findViewById(R.id.spinner);
        edtDate = (EditText) findViewById(R.id.editNgay);
        TongTien = (TextView) findViewById(R.id.txtTongTienNgay);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });


        ArrayList<String> Loai = new ArrayList<>();
        Loai.add("Theo Ngay");
        Loai.add("Theo Thang");
        Loai.add("Theo Nam");

        // (@resource) android.R.layout.simple_spinner_item:
        //   The resource ID for a layout file containing a TextView to use when instantiating views.
        //    (Layout for one ROW of Spinner)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Loai
                );

        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerLoaiThongKe.setAdapter(adapter);
        //RECYCLERVIEW
        mDatabase = FirebaseDatabase.getInstance().getReference().child("DaThanhToan");

        recycler = (RecyclerView) findViewById(R.id.recycler_thongke);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);

        dsHoaDonTrongNgay = new ArrayList<>();

        recyclerAdapter = new RecyclerDonThanhToanTrongNgay(dsHoaDonTrongNgay, this);
        recycler.setAdapter(recyclerAdapter);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query queryHoaDon = mDatabase.orderByChild("ngayThanhToan").equalTo(edtDate.getText().toString());
                queryHoaDon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                            Thanh_Toan ctdondat = singleSnapshot.getValue(Thanh_Toan.class);
                            //Don_Dat_1_Ban dondat = singleSnapshot.getValue(Don_Dat_1_Ban.class);
                            //Chi_Tiet_HD cthoadon = new Chi_Tiet_HD(ctdondat.getTenMon(), ctdondat.getSoLuong(), ctdondat.getSoLuong() * ctdondat.getDonGia(), ctdondat.getUrl());
                            dsHoaDonTrongNgay.add(ctdondat);
                            tongTienNgay+= ctdondat.getTongTien();
                            TongTien.setText(tongTienNgay+"");
                        }
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        });

    }
    private void ChonNgay()
    {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);  //gán ngày tháng năm đã chọn

                SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                edtDate.setText(dinhDangNgay.format(calendar.getTime()));
            }
        },nam, thang, ngay);
        datePickerDialog.show();
    }

}