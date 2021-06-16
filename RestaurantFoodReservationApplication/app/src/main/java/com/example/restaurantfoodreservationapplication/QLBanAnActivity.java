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

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;

import java.util.ArrayList;

public class QLBanAnActivity extends AppCompatActivity {
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
        initView();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListBanAn.add(new Ban_An("B09","Bàn 9",9));
                banAnAdapter.notifyDataSetChanged();
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

        arrayListBanAn.add(new Ban_An("B01","Bàn 1",2));
        arrayListBanAn.add(new Ban_An("B02","Bàn 2",3));
        arrayListBanAn.add(new Ban_An("B03","Bàn 3",4));
        arrayListBanAn.add(new Ban_An("B03","Bàn 4",5));
        recyclerView.setAdapter(banAnAdapter);

    }
}