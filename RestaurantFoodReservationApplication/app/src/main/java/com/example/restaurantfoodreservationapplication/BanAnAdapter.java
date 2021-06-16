package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;

import java.util.ArrayList;

public class BanAnAdapter extends RecyclerView.Adapter<BanAnAdapter.ViewHolder>{

    ArrayList<Ban_An> dataBanAns;
    Context context;

    public BanAnAdapter(ArrayList<Ban_An> dataBanAns, Context context) {
        this.dataBanAns = dataBanAns;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_banan,parent,false);

        return new ViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaBan.setText("Mã bàn: " +dataBanAns.get(position).getMaBan());
        holder.txtTenBan.setText("Tên bàn: " + dataBanAns.get(position).getTenBan());
        holder.txtSoLuong.setText("Số lượng chỗ: " + dataBanAns.get(position).getSoCho()+"");
    }

    @Override
    public int getItemCount() {
        return dataBanAns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaBan, txtTenBan, txtSoLuong;
        Button btnsua, btnxoa;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtMaBan = (TextView) itemView.findViewById(R.id.txtTenNhanVien);
            txtTenBan = (TextView) itemView.findViewById(R.id.txtMaNhanVien);
            txtSoLuong = (TextView) itemView.findViewById(R.id.txtSoLuongCho);

            btnsua = (Button) itemView.findViewById(R.id.btnSuaNV);
            btnxoa = (Button) itemView.findViewById(R.id.btnXoaBan);
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XacNhanXoa();
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    private void XacNhanXoa(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Thông báo!");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa môn học này không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}
