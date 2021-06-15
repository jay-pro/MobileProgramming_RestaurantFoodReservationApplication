package com.example.restaurantfoodreservationapplication;

import android.app.Fragment;
import android.app.UiAutomation;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Chuc_Vu;
import com.example.restaurantfoodreservationapplication.Class.Danh_Muc;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DatMonActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/{
    //private static final int FRAGMENT_DM1 = 1;
    //private static final int FRAGMENT_DM2 = 2;
    //private static final int FRAGMENT_DM3 = 3;
    //private static final int FRAGMENT_QLMON = 4;
    //private int currentFragment = FRAGMENT_DM1;//phát hiện đang ở fragment nào


    Button btnXemDonDat;
  /*  TextView tenmon, giamon;
    Button btnDatMon;
    ImageView imgView;*/
    private AppBarConfiguration mAppBarConfiguration;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datmon);
        btnXemDonDat = (Button) findViewById(R.id.btnDonHang);
        btnXemDonDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dm2, R.id.nav_dm1, R.id.nav_dm3, R.id.nav_qlm)
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











    //Chạy cái item ql món ở left menu (phía dưới)

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        //Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_dm1){
            //handle the thucuong action
            if(FRAGMENT_DM1 != currentFragment){
                replaceFragment(new DatMonActivity());
                currentFragment = FRAGMENT_DM1;
            }
        }
        //else if (id == R.id.nav_dm2){ }
        //else if (id == R.id.nav_dm3){ }
        else if (id == R.id.nav_qlm){
            //quan ly mon
            if(FRAGMENT_DM1 != currentFragment){
                replaceFragment(new DatMonActivity());
                currentFragment = FRAGMENT_DM1;
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.quanlymon_fragment, fragment);
      fragmentTransaction.commit();
    }

 */


}