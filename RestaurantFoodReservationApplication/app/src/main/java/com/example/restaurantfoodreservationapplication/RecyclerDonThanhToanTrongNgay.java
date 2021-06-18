package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Thanh_Toan;

import java.util.List;

public class RecyclerDonThanhToanTrongNgay extends RecyclerView.Adapter<RecyclerDonThanhToanTrongNgay.DataViewHolder>{
    private List<Thanh_Toan> dsHoaDonTrongNgay;
    private Context context;
    private RecyclerDonThanhToanTrongNgay.OnItemClickListener Listener;
    public static int soluong;
    public  interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(RecyclerDonThanhToanTrongNgay.OnItemClickListener listener)
    {
        Listener = listener;
    }
    public RecyclerDonThanhToanTrongNgay(List<Thanh_Toan> dsHoaDonTrongNgay, Context context) {
        this.dsHoaDonTrongNgay = dsHoaDonTrongNgay;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerDonThanhToanTrongNgay.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadonngay,parent,false);

        return new RecyclerDonThanhToanTrongNgay.DataViewHolder(v, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDonThanhToanTrongNgay.DataViewHolder holder, int position) {
        Thanh_Toan CTHoaDon = dsHoaDonTrongNgay.get(position);

        //bind data to viewholder
        holder.TongTien.setText(CTHoaDon.getTongTien()+"");
       // holder.NgayThanhToan.setText(String.valueOf(CTHoaDon.getNgayThanhToan()));
        holder.MaBan.setText(String.valueOf(CTHoaDon.getMaBan()));
        //holder.tvMaBan.setText(CTHoaDon.getMaBan());

    }

    @Override
    public int getItemCount() {
        return dsHoaDonTrongNgay.size();
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView TongTien;
        //private TextView NgayThanhToan;
        private TextView MaBan;


        public DataViewHolder(View itemView, RecyclerDonThanhToanTrongNgay.OnItemClickListener listener) {
            super(itemView);
           TongTien = (TextView) itemView.findViewById(R.id.textTongTien);
            MaBan = (TextView) itemView.findViewById(R.id.textMaBan);
          //  NgayThanhToan = (TextView) itemView.findViewById(R.id.textNgayThanhToan);

        }
    }
}
