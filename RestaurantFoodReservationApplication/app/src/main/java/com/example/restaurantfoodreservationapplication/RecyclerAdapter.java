package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Chi_Tiet_Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Don_Dat;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder>{
    private List<Mon_An> dsMon;
    private Context context;
    public static  List<Chi_Tiet_Don_Dat> dsMonDaDat;

    public static int ViTri = -1;
    public static Chi_Tiet_Don_Dat dondat;

    private OnItemClickListener Listener;
public  interface OnItemClickListener{
    void onItemClick(int position);
}
public  void setOnItemClickListener(OnItemClickListener listener)
{
    Listener = listener;
}
    public RecyclerAdapter(List<Mon_An> dsMon, Context context) {
        this.dsMon = dsMon;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dm2,parent,false);

        return new RecyclerAdapter.DataViewHolder(v, Listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.DataViewHolder holder, int position) {
        Mon_An monan = dsMon.get(position);

        //bind data to viewholder
        holder.tvGiaMon.setText((int) monan.getGiaMon()+"");
        holder.tvMaDM.setText(monan.getMaDM().toString());
        holder.tvTenMon.setText(monan.getTenMon());
      /*  holder.btnDatMon.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              int position = holder.getAdapterPosition();
          }
      });*/

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViTri = position;
                //Toast.makeText(this, "DDax theem mon", Toast.LENGTH_SHORT).show();
                //Mon_An monan = dsMon.get(holder.getAdapterPosition());
                //dondat = new Chi_Tiet_Don_Dat( "1",monan.getTenMon(), monan.getGiaMon(),1);
                datmon=true;
                //dsMonDaDat.add(dondat);
            }
        });
        //holder.tvLyric.setText(song.getLyric());
        // holder.tvArtist.setText(song.getArtist());*/
    }

    @Override
    public int getItemCount() {
        return dsMon.size();
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private Button btnDatMon;
        private TextView tvMaDM;
        private TextView tvTenMon;
        private TextView tvGiaMon;

        public DataViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            tvMaDM = (TextView) itemView.findViewById(R.id.text_home);
            tvTenMon = (TextView) itemView.findViewById(R.id.txtTenMon);
            tvGiaMon = (TextView) itemView.findViewById(R.id.txtGiaTien);
            btnDatMon = (Button) itemView.findViewById(R.id.btnDatMon);
            btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
               {
                    int position = getAdapterPosition();
                    if (position!= RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }


            }
           });

        }
    }

}