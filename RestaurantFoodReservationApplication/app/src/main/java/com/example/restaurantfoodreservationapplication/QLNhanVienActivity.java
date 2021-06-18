package com.example.restaurantfoodreservationapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;

import java.util.ArrayList;

public class QLNhanVienActivity extends AppCompatActivity {
    Button btnThem;
    ArrayList<Nhan_Vien> arrayListNV = new ArrayList<>();
    NhanVienAdapter nhanvienAdapter;
    RecyclerView recyclerViewNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_nhan_vien);
        btnThem = (Button) findViewById(R.id.btnThemNhanVien);
        nhanvienAdapter = new NhanVienAdapter(arrayListNV,getApplicationContext());
        recyclerViewNV= (RecyclerView) findViewById(R.id.recycler_viewNhanVien);
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                arrayListNV.add(new Nhan_Vien(R.drawable.kiwi,"Tien vua them"));
////                shopAdapter.notifyDataSetChanged();
//            }
//        });
        initView();
    }
    public void initView()
    {

        recyclerViewNV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewNV.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewNV.getContext(),DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerViewNV.addItemDecoration(dividerItemDecoration);
        recyclerViewNV.setItemAnimator(new DefaultItemAnimator());

        arrayListNV.add(new Nhan_Vien("NV01","NhanVien","Tiến","Nam","123","4561","Quan 9",5000,""));
        arrayListNV.add(new Nhan_Vien("NV02","NhanVien","Phương","Nữ","1234","4562","Quan 9",6000,""));
        arrayListNV.add(new Nhan_Vien("NV03","NhanVien","Nhi","Nữ","12345","4563","Quan 9",7000,""));
        arrayListNV.add(new Nhan_Vien("NV04","NhanVien","Chánh","Nam","123456","4564","Quan 9",8000,""));
        recyclerViewNV.setAdapter(nhanvienAdapter);



    }
}