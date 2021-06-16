package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    ArrayList<Nhan_Vien> dataNhanViens;
    Context context;

    public NhanVienAdapter(ArrayList<Nhan_Vien> dataNhanViens, Context context) {
        this.dataNhanViens = dataNhanViens;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_nhanvien,parent,false);

        return new ViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(dataNhanViens.get(position).getHoTen());
        holder.txtMaNV.setText(dataNhanViens.get(position).getID());
        //holder.imgHinh.setImageResource(dataNhanViens.get(position).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return dataNhanViens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtName,txtMaNV;
    ImageView imgHinh;
    Button btnchitiet,btnsua,btnxoa;
    public ViewHolder(@NonNull View itemView) {

        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txtTenNV);
        txtMaNV = (TextView) itemView.findViewById(R.id.txtMaNV);
        imgHinh = (ImageView) itemView.findViewById(R.id.imageViewNhanVien);
        btnchitiet = (Button) itemView.findViewById(R.id.buttonChiTietNV);
        btnsua = (Button) itemView.findViewById(R.id.buttonSuaNV);
        btnxoa = (Button) itemView.findViewById(R.id.buttonXoaNV);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    RemoveItem(getAdapterPosition());
//                    Toast.makeText(itemView.getContext(), "Remove " + txtName.getText(), Toast.LENGTH_SHORT).show();
//                }
//            });
        btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Vua nhan vao" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
}
