package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class RecyclerDonDatAdapter extends RecyclerView.Adapter<RecyclerDonDatAdapter.DataViewHolder>{
    private List<Chi_Tiet_Don_Dat> dsDonDat;
    private Context context;
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

        public DataViewHolder(View itemView, RecyclerDonDatAdapter.OnItemClickListener listener) {
            super(itemView);
            tvTenMon = (TextView) itemView.findViewById(R.id.textViewTenMon);
            tvSoLuong = (TextView) itemView.findViewById(R.id.textViewSL);
            imgTangSL = (ImageView) itemView.findViewById(R.id.imgTangSL);
            imgGiamSL = (ImageView) itemView.findViewById(R.id.imgGiamSL);
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
                            soluong = soluong-1;  //
                            dsDonDat.get(position).setSoLuong(soluong);
                            listener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }

}
