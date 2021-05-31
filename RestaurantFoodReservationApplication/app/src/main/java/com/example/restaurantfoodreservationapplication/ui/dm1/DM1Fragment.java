package com.example.restaurantfoodreservationapplication.ui.dm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.example.restaurantfoodreservationapplication.R;
import com.example.restaurantfoodreservationapplication.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DM1Fragment extends Fragment {
    ListView MonAn;
    TextView TenMon, GiaMon;
    ImageView HinhAnh;
    RecyclerView recycler;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Mon_An> dsMon;
    DatabaseReference mDatabase;
    private DM1ViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //galleryViewModel =
                //new ViewModelProvider(this).get(DM1ViewModel.class);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        mDatabase = db.child("MonAn");
        Query query =  mDatabase.orderByChild("MaDM").equalTo("DR");
        View root = inflater.inflate(R.layout.recycleview, container, false);

        final TextView textView = root.findViewById(R.id.text_home); ////moiws theem
        //mDatabase = FirebaseDatabase.getInstance().getReference(); //moiws theem
        recycler = (RecyclerView) root.findViewById(R.id.recyclerview);
        // DatabaseReference ref = mDatabase.child("MonAn");  //LinearLayoutManager.HORIZONTAL

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL,true);
        recycler.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        // recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        dsMon = new ArrayList<>();

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

        query.addValueEventListener(new ValueEventListener() {
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
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat(1+"", dsMon.get(position).getTenMon(), dsMon.get(position).getGiaMon(), 1);
                db.child("DonDat").push().setValue(don); //bij looi out ctrinh
            }
        });

        return root;


    }
}