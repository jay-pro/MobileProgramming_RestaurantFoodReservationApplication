package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class RecyclerDonDatAdapter extends RecyclerView.Adapter<RecyclerDonDatAdapter.DataViewHolder>{
    private List<Chi_Tiet_Don_Dat> dsDonDat;
    private Context context;
   // public static boolean Xoa = false;
    private RecyclerDonDatAdapter.OnItemClickListener Listener;
    public static int soluong;
    public  interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(RecyclerDonDatAdapter.OnItemClickListener listener)
    {
        Listener = listener;
    }
    public RecyclerDonDatAdapter(List<Chi_Tiet_Don_Dat> dsDonDat, Context context) {
        this.dsDonDat = dsDonDat;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerDonDatAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_mondadat,parent,false);

        return new RecyclerDonDatAdapter.DataViewHolder(v, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDonDatAdapter.DataViewHolder holder, int position) {
        Chi_Tiet_Don_Dat CTDon = dsDonDat.get(position);

        //bind data to viewholder
        holder.tvTenMon.setText(CTDon.getTenMon());
        holder.tvSoLuong.setText(String.valueOf(CTDon.getSoLuong()));
        Glide.with(context).load(dsDonDat.get(position).getUrl()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return dsDonDat.size();
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTangSL;
        private ImageView imgGiamSL;
        private TextView tvTenMon;
        private TextView tvSoLuong;
        private ImageView imgHinh;

        public DataViewHolder(View itemView, RecyclerDonDatAdapter.OnItemClickListener listener) {
            super(itemView);
            tvTenMon = (TextView) itemView.findViewById(R.id.textMaBan);
            tvSoLuong = (TextView) itemView.findViewById(R.id.textSoluong);
            imgTangSL = (ImageView) itemView.findViewById(R.id.imgTangSL);
            imgGiamSL = (ImageView) itemView.findViewById(R.id.imgGiamSL);
            imgHinh = (ImageView) itemView.findViewById(R.id.img);
            imgHinh.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener!=null)
                    {
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                            alertDialog.setIcon(R.mipmap.ic_launcher);
                            alertDialog.setMessage("Ban co chac muon xoa mon nay khoi don dat?");

                            alertDialog.setPositiveButton("Xac nhan", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listener.onItemClick(position);
                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                    Query query = mDatabase.child("DonDat").child("DonDat"+MaBan).orderByChild("tenMon").equalTo(dsDonDat.get(position).getTenMon());//.child(dsDonDat.get(position).getTenMon()).removeValue();
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
                                    dsDonDat.remove(dsDonDat.get(position));
                                    mDatabase.child("DonDat").child("DonDat" + MaBan).child(singleSnapshot.getKey()).removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                                    }
                            });
                            alertDialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();

                           // dsDonDat.remove(dsDonDat.get(position));

                        }
                    }
                    return false;
                }
            });
            imgTangSL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {

                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            soluong = dsDonDat.get(position).getSoLuong();  //
                            soluong = soluong+1;
                            dsDonDat.get(position).setSoLuong(soluong);//
                            listener.onItemClick(position);


                        }
                    }


                }
            });
            imgGiamSL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {

                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            soluong = dsDonDat.get(position).getSoLuong();  //
                              //
                            soluong = soluong-1;
                           if(soluong>0)
                          {
                            dsDonDat.get(position).setSoLuong(soluong);
                          }
                            listener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }

    private void HienTongTien(String MaBanDat) {


    }

}
