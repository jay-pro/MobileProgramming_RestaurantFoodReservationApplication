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
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QLBanAnActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button btnThem;
    ArrayList<Ban_An> arrayListBanAn = new ArrayList<>();
    BanAnAdapter banAnAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_ban_an);
        btnThem = (Button) findViewById(R.id.btnThemBanAn);
        banAnAdapter = new BanAnAdapter(arrayListBanAn,getApplicationContext());
        recyclerView= (RecyclerView) findViewById(R.id.recycler_viewBanAN);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getListBanFromFirebase();
        initView();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }
    private  void getListBanFromFirebase()
    {
        mDatabase.child("BanAn").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ban_An ban_an = snapshot.getValue(Ban_An.class);
                arrayListBanAn.add(ban_an);
                banAnAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void initView()
    {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        arrayListBanAn.add(new Ban_An("B01","Bàn 1",2));
//        arrayListBanAn.add(new Ban_An("B02","Bàn 2",3));
//        arrayListBanAn.add(new Ban_An("B03","Bàn 3",4));
//        arrayListBanAn.add(new Ban_An("B03","Bàn 4",5));
        recyclerView.setAdapter(banAnAdapter);

    }

    private  void  DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thembanan);
        dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
        // ánh xạ
        EditText edtMaBan = (EditText) dialog.findViewById(R.id.edtMaBanAn);
        EditText edtTenBan = (EditText) dialog.findViewById(R.id.edtTenBanAn);
        EditText edtSoCho = (EditText) dialog.findViewById(R.id.edtSoLuongCho);
        Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongYThemBan);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyThemBan);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ban_An ban_an = new Ban_An(edtMaBan.getText().toString(), edtTenBan.getText().toString(), Integer.parseInt(edtSoCho.getText().toString()) );
                mDatabase.child("BanAn").push().setValue(ban_an, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {
                            Toast.makeText(QLBanAnActivity.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(QLBanAnActivity.this, "Lưu Thất Bại!", Toast.LENGTH_SHORT).show();
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