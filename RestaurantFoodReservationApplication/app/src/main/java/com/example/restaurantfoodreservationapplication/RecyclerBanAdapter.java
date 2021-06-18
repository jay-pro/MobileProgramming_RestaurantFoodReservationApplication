package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.example.restaurantfoodreservationapplication.DatMonActivity;

import java.util.List;

public class RecyclerBanAdapter extends RecyclerView.Adapter<RecyclerBanAdapter.DataViewHolder>{
    private List<Ban_An> dsBan;
    private Context context;

    private RecyclerBanAdapter.OnItemClickListener Listener;

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(RecyclerBanAdapter.OnItemClickListener listener)
    {
        Listener = listener;
    }
    public RecyclerBanAdapter(List<Ban_An> dsBan, Context context) {
        this.dsBan = dsBan;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerBanAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_dsban,parent,false);

        return new RecyclerBanAdapter.DataViewHolder(v, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBanAdapter.DataViewHolder holder, int position) {
        Ban_An ban = dsBan.get(position);

        //bind data to viewholder
        holder.btnBan.setText(ban.getTenBan());
       // holder.tvSoLuong.setText(String.valueOf(ban.getSoLuong()));
       // Glide.with(context).load(ban.get(position).getUrl()).into(holder.imgHinh);*/

    }

    @Override
    public int getItemCount() {
        return dsBan.size();
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMaBan;
        private TextView tvSoCho;
       // private ImageView imgHinh;
        private Button btnBan;

        public DataViewHolder(View itemView, RecyclerBanAdapter.OnItemClickListener listener) {
            super(itemView);
            //tvMaBan = (TextView) itemView.findViewById(R.id.textMaBan);
            btnBan =(Button) itemView.findViewById(R.id.btnBan);



            btnBan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                          //  MaBan = String.format("%d", dsBan.get(position).getTenBan());
                           // Intent intent = new Intent(DSBanActivity.class, DatMonActivity.class);
                            //startActivity(intent);
                          //  soluong = dsBan.get(position).getTenBan();  //
                            //soluong = soluong+1;

                          //  dsBan.get(position).getTenBan(soluong);//
                            listener.onItemClick(position);


                        }
                    }


                }
            });

        }
    }
}
