package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_HD;

import java.util.List;

public class RecyclerThanhToanAdapter extends RecyclerView.Adapter<RecyclerThanhToanAdapter.DataViewHolder>{
    private List<Chi_Tiet_HD> dsHoaDon;
    private Context context;
    private RecyclerThanhToanAdapter.OnItemClickListener Listener;
    public static int soluong;
    public  interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(RecyclerThanhToanAdapter.OnItemClickListener listener)
    {
        Listener = listener;
    }
    public RecyclerThanhToanAdapter(List<Chi_Tiet_HD> dsHoaDon, Context context) {
        this.dsHoaDon = dsHoaDon;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerThanhToanAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_thanh_toan,parent,false);

        return new RecyclerThanhToanAdapter.DataViewHolder(v, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerThanhToanAdapter.DataViewHolder holder, int position) {
        Chi_Tiet_HD CTHoaDon = dsHoaDon.get(position);

        //bind data to viewholder
        holder.tvTenMon.setText(CTHoaDon.getTenMon());
        holder.tvSoLuong.setText(String.valueOf(CTHoaDon.getSoLuong()));
        holder.tvThanhTien.setText(String.valueOf(CTHoaDon.getThanhTien()));
        //holder.tvMaBan.setText(CTHoaDon.getMaBan());
        Glide.with(context).load(dsHoaDon.get(position).getUrl()).into(holder.imgHinh);

    }

    @Override
    public int getItemCount() {
        return dsHoaDon.size();
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView tvThanhTien;
        private TextView tvMaBan;
        private TextView tvTenMon;
        private TextView tvSoLuong;
        private ImageView imgHinh;

        public DataViewHolder(View itemView, RecyclerThanhToanAdapter.OnItemClickListener listener) {
            super(itemView);
            tvTenMon = (TextView) itemView.findViewById(R.id.textMaBan);
           // tvMaBan = (TextView) itemView.findViewById(R.id.textviewMaBan);
            tvSoLuong = (TextView) itemView.findViewById(R.id.textTongTien);
            tvThanhTien = (TextView) itemView.findViewById(R.id.textViewThanhTien);

            imgHinh = (ImageView) itemView.findViewById(R.id.img);


        }
    }
}
