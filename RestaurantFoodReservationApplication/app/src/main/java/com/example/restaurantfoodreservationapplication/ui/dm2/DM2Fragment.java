package com.example.restaurantfoodreservationapplication.ui.dm2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.example.restaurantfoodreservationapplication.R;
import com.example.restaurantfoodreservationapplication.RecyclerAdapter;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.restaurantfoodreservationapplication.RecyclerAdapter.ViTri;

import static com.example.restaurantfoodreservationapplication.RecyclerAdapter.dondat;

public class DM2Fragment extends Fragment {

    private DM2ViewModel homeViewModel;
    ListView MonAn;
    TextView TenMon, GiaMon;
    Button BtnDatMon;
    ImageView HinhAnh;
    RecyclerView recycler;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Mon_An> dsMon;
    ArrayList<Chi_Tiet_Don_Dat> dsMonDat;
    DatabaseReference mDatabase;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* homeViewModel =
                new ViewModelProvider(this).get(DM2ViewModel.class);*/
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        mDatabase = db.child("MonAn");
        Query query =  mDatabase.orderByChild("MaDM").equalTo("DA");
        View root = inflater.inflate(R.layout.recycleview, container, false);
        BtnDatMon = (Button) root.findViewById(R.id.btnDatMon);
        final TextView textView = root.findViewById(R.id.text_home); ////moiws theem
        //mDatabase = FirebaseDatabase.getInstance().getReference(); //moiws theem
        recycler = (RecyclerView) root.findViewById(R.id.recyclerview);
        // DatabaseReference ref = mDatabase.child("MonAn");  //LinearLayoutManager.HORIZONTAL

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL,true);
        recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
       // recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        dsMon = new ArrayList<>();
        dsMonDat = new ArrayList<>();
        //array.add(new Mon_An( "","","",12));
        recyclerAdapter = new RecyclerAdapter(dsMon,this.getActivity());
        recycler.setAdapter(recyclerAdapter);
      /*  mDatabase.addValueEventListener(new ValueEventListener() { //addListenerForSingleValueEvent
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    Mon_An mon = singleSnapshot.getValue(Mon_An.class);
                    dsMon.add(mon);

                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    Mon_An mon = singleSnapshot.getValue(Mon_An.class);
                    dsMon.add(mon);

                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        if(ViTri!=-1) { //khong hoat dong
//            //Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat("1", dsMon.get(ViTri).getTenMon(), dsMon.get(ViTri).getGiaMon(), 4);
//           // Mon_An chucvu2 = new Mon_An(15000, "CB014","Combo");
//           // db.child("MonAn").push().setValue(chucvu2);
//
//            Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat("1", dsMon.get(ViTri).getTenMon(), dsMon.get(ViTri).getGiaMon(), 4);
//            db.child("DonDat").push().setValue(don);
//        }
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
         @Override
         public void onItemClick(int position) {
             Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat(1+"", dsMon.get(position).getTenMon(), dsMon.get(position).getGiaMon(), 1);
             db.child("DonDat").push().setValue(don); //bij looi out ctrinh
         }
        });
/*
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

}