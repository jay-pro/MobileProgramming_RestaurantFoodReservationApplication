package com.example.restaurantfoodreservationapplication;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Chuc_Vu;
import com.example.restaurantfoodreservationapplication.Class.Danh_Muc;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DatMonActivity extends AppCompatActivity {

    Button btnXemDonDat, btnThanhToan;
    TextView soBan;
    public static String MaBan;
    Button btnDatMon;
    ImageView imgView;
    private AppBarConfiguration mAppBarConfiguration;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datmon);
        btnXemDonDat = (Button) findViewById(R.id.btnDonHang);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        soBan = (TextView) findViewById(R.id.txtMaBan);
        soBan.setText(MaBan);
        // MaBan = soBan.getText().toString();
        btnXemDonDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatMonActivity.this, XemDonDatActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatMonActivity.this, ThanhToanActivity.class);
                startActivity(intent);
            }
        });


        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

         */

        //Node lớn
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Node nhỏ

        //  Danh_Muc danhmuc10 = new Danh_Muc("GR","Gà rán");

        //  mDatabase.child("DanhMuc").push().setValue(danhmuc10);


        /*Ban_An ban_an = new Ban_An("B3-001","Ban Don 2");
        Danh_Muc chucvu = new Danh_Muc("CB","Combo");
        Mon_An chucvu2 = new Mon_An("CB","CB001","BTSMeal",150000);

        mDatabase.child("DanhMuc").push().setValue(chucvu);
        mDatabase.child("MonAn").push().setValue(chucvu2);*/





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatMonActivity.this, DSBanActivity.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dm2, R.id.nav_dm1, R.id.nav_dm3)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        /*tenmon = (TextView) findViewById(R.id.txtTenMon);
        giamon = (TextView) findViewById(R.id.txtGiaTien);
        imgView = (ImageView) findViewById(R.id.imgView);
        btnDatMon = (Button) findViewById(R.id.btnDatMon);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}