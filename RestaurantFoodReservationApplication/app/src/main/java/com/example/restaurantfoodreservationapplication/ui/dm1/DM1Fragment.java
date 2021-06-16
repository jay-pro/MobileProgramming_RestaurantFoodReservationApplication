package com.example.restaurantfoodreservationapplication.ui.dm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import java.util.HashMap;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

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
        Query query =  mDatabase.orderByChild("MaDM").equalTo("DA");
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
            int i;
            @Override
            public void onItemClick(int position) {
                // i = 1;
                // Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat(dsMon.get(position).getGiaMon(), MaBan,1, dsMon.get(position).getTenMon()); //Sua lai ban cho phu hop
                db.child("DonDat").child("DonDat"+MaBan).orderByChild("tenMon").equalTo(dsMon.get(position).getTenMon()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            //snapshot bang NULL nen phia duoi du lieu bi sai
                            for(DataSnapshot ds : snapshot.getChildren()) {
                                Chi_Tiet_Don_Dat don = ds.getValue(Chi_Tiet_Don_Dat.class);

                                // Chi_Tiet_Don_Dat don = snapshot.getValue(Chi_Tiet_Don_Dat.class);
                                // Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat(dsMon.get(position).getGiaMon(), MaBan, 1, dsMon.get(position).getTenMon()); //Sua lai ban cho phu hop

                                int soluong = don.getSoLuong() + 1;
                                don.setSoLuong(soluong);
                                // ctdondat.setTenMon(dsMonDat.get(position).getTenMon());
                                //ctdondat.setSoLuong(soluong);
                                String key = ds.getKey();
                                HashMap childUpdates = new HashMap();
                                childUpdates.put(key, don);
                                // if (i==1) {
                                db.child("DonDat").child("DonDat" + MaBan).updateChildren(childUpdates);
                                recyclerAdapter.notifyDataSetChanged();
                                // }
                                //i = i+1;
                            }
                        }
                        else {
                            //if(i==1) {
                            Chi_Tiet_Don_Dat don = new Chi_Tiet_Don_Dat(dsMon.get(position).getGiaMon(), MaBan, 1, dsMon.get(position).getTenMon(), dsMon.get(position).getUrl()); //Sua lai ban cho phu hop
                            db.child("DonDat").child("DonDat" + MaBan).push().setValue(don);
                            recyclerAdapter.notifyDataSetChanged();
                            // }
                            //i=i+1;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // db.child("DonDat").child("DonDat"+MaBan).push().setValue(don);
            }
        });

        return root;


    }
}