package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.bumptech.glide.load.model.ByteArrayLoader;
import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QLNhanVienActivity extends AppCompatActivity{
    Button btnThem;
    ArrayList<Nhan_Vien> arrayListNV = new ArrayList<>();
    NhanVienAdapter nhanvienAdapter;
    RecyclerView recyclerViewNV;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_nhan_vien);
        btnThem = (Button) findViewById(R.id.btnThemNhanVien);
        nhanvienAdapter = new NhanVienAdapter(arrayListNV,getApplicationContext());
        recyclerViewNV= (RecyclerView) findViewById(R.id.recycler_viewNhanVien);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                arrayListNV.add(new Nhan_Vien(R.drawable.kiwi,"Tien vua them"));
////                shopAdapter.notifyDataSetChanged();
//            }
//        });
        getListNhanVienFromFirebase();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               DialogAdd();
            }
        });
        initView();



//        Nhan_Vien nv1 = new Nhan_Vien("NV01","NhanVien","Nguyễn QUốc Tiến","Nam","123","4561","Quan 9",5000,"");
//        Nhan_Vien nv2 = new Nhan_Vien("NV02","NhanVien","Phương","Nữ","1234","4562","Quan 9",6000,"");
//        Nhan_Vien nv3 = new Nhan_Vien("NV03","NhanVien","Nhi","Nữ","12345","4563","Quan 9",7000,"");
//        Nhan_Vien nv4 = new Nhan_Vien("NV04","NhanVien","Chánh","Nam","123456","4564","Quan 9",8000,"");
//
//        mDatabase.child("NhanVien").push().setValue(nv1);
//        mDatabase.child("NhanVien").push().setValue(nv2);
//        mDatabase.child("NhanVien").push().setValue(nv3);
//        mDatabase.child("NhanVien").push().setValue(nv4);
    }
    private  void getListNhanVienFromFirebase()
    {
        mDatabase.child("NhanVien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Nhan_Vien nhan_vien = snapshot.getValue(Nhan_Vien.class);
                arrayListNV.add(nhan_vien);
                nhanvienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if (NhanVienAdapter.DongVuaBiXoa != -1 ) {
                    arrayListNV.remove(NhanVienAdapter.DongVuaBiXoa);
                    nhanvienAdapter.notifyDataSetChanged();
                    NhanVienAdapter.DongVuaBiXoa = -1;
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void DialogXacNhanXoa(){

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

//        arrayListNV.add(new Nhan_Vien("NV01","NhanVien","Nguyễn QUốc Tiến","Nam","123","4561","Quan 9",5000,""));
//        arrayListNV.add(new Nhan_Vien("NV02","NhanVien","Phương","Nữ","1234","4562","Quan 9",6000,""));
//        arrayListNV.add(new Nhan_Vien("NV03","NhanVien","Nhi","Nữ","12345","4563","Quan 9",7000,""));
//        arrayListNV.add(new Nhan_Vien("NV04","NhanVien","Chánh","Nam","123456","4564","Quan 9",8000,""));
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
                if(edtMaNV.getText().toString().trim().length() == 0 ||  edtChucVu.getText().toString().trim().length() == 0 ||
                        edtTenNV.getText().toString().trim().length() == 0|| edtGioiTinh.getText().toString().trim().length() == 0 ||
                edtCMND.getText().toString().trim().length() == 0 || edtSDT.getText().toString().trim().length() == 0 || edtDiaChi.getText().toString().trim().length() == 0 || edtLuong.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(QLNhanVienActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Nhan_Vien nv = new Nhan_Vien(edtMaNV.getText().toString(), edtChucVu.getText().toString(),edtTenNV.getText().toString(),edtGioiTinh.getText().toString(),edtCMND.getText().toString(), edtSDT.getText().toString(),edtDiaChi.getText().toString(),Double.parseDouble(edtLuong.getText().toString()) ,"");
                mDatabase.child("NhanVien").push().setValue(nv, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {
                            Toast.makeText(QLNhanVienActivity.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(QLNhanVienActivity.this, "Lưu Thất Bại!", Toast.LENGTH_SHORT).show();
                    }
                });

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