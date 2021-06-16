package com.example.restaurantfoodreservationapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DialogAdd();
            }
        });
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

        arrayListNV.add(new Nhan_Vien("NV01","NhanVien","Nguyễn QUốc Tiến","Nam","123","4561","Quan 9",5000,""));
        arrayListNV.add(new Nhan_Vien("NV02","NhanVien","Phương","Nữ","1234","4562","Quan 9",6000,""));
        arrayListNV.add(new Nhan_Vien("NV03","NhanVien","Nhi","Nữ","12345","4563","Quan 9",7000,""));
        arrayListNV.add(new Nhan_Vien("NV04","NhanVien","Chánh","Nam","123456","4564","Quan 9",8000,""));
        recyclerViewNV.setAdapter(nhanvienAdapter);

    }
    private  void  DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themnhanvien);
        dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
        // ánh xạ
        EditText edtMaNV = (EditText) dialog.findViewById(R.id.edtMaNV);
        EditText edtChucVu = (EditText) dialog.findViewById(R.id.edtChucVu);
        EditText edtTenNV = (EditText) dialog.findViewById(R.id.edtHoTen);
        EditText edtGioiTinh = (EditText) dialog.findViewById(R.id.edtGioiTinh);
        EditText edtCMND = (EditText) dialog.findViewById(R.id.edtCMND);
        EditText edtSDT = (EditText) dialog.findViewById(R.id.edtSDT);
        EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edtDiaChi);
        EditText edtLuong = (EditText) dialog.findViewById(R.id.edtLuong);
        Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongYThemNV);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyThemNV);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
//            dialog.getWindow().setAttributes(lp);
    }
}