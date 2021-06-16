package com.example.restaurantfoodreservationapplication;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
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
        txtName = (TextView) itemView.findViewById(R.id.txtTenNhanVien);
        txtMaNV = (TextView) itemView.findViewById(R.id.txtMaNhanVien);
        imgHinh = (ImageView) itemView.findViewById(R.id.imageViewNhanVien);
        btnchitiet = (Button) itemView.findViewById(R.id.buttonChiTietNV);
        btnsua = (Button) itemView.findViewById(R.id.btnSuaNV);
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
                DialogDetail(getAdapterPosition());
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
        private  void  DialogDetail(int position) {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

//            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//            lp.copyFrom(dialog.getWindow().getAttributes());
//            DisplayMetrics metrics = new DisplayMetrics(); //get metrics of screen
//            metrics = context.getResources().getDisplayMetrics();
//            int width = metrics.widthPixels;
//            int height = metrics.heightPixels;
//            Toast.makeText(context, width + " d "+ height, Toast.LENGTH_SHORT).show();
//            lp.width = (int) (width * 0.9f);
////            lp.height = (int) (height * 0.6f);

            dialog.setContentView(R.layout.dialog_chitietnhanvien);
            dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
            // ánh xạ
            TextView txtMaNV = (TextView) dialog.findViewById(R.id.textViewCTMaNV);
            TextView txtChucVu = (TextView) dialog.findViewById(R.id.textViewCTChucVu);
            TextView txtTenNV = (TextView) dialog.findViewById(R.id.textViewCTTenNV);
            TextView txtGioiTinh = (TextView) dialog.findViewById(R.id.textViewCTGioiTinh);
            TextView txtCMND = (TextView) dialog.findViewById(R.id.textViewCTCMND);
            TextView txdSDT = (TextView) dialog.findViewById(R.id.textViewCTSDT);
            TextView txtDiaChi = (TextView) dialog.findViewById(R.id.textViewCTDiaChi);
            TextView txtLuong = (TextView) dialog.findViewById(R.id.textViewCTLuong);
            Button btnDong = (Button) dialog.findViewById(R.id.buttonDongXemChiTiet);

            //set text
            txtMaNV.setText("Mã nhân viên: " + dataNhanViens.get(position).getID());
            txtChucVu.setText("Chức vụ: " + dataNhanViens.get(position).getChucVu());
            txtTenNV.setText("Họ Tên: " + dataNhanViens.get(position).getHoTen());
            txtGioiTinh.setText("Giới tính: " + dataNhanViens.get(position).getGioiTinh());
            txtCMND.setText("CMND: " + dataNhanViens.get(position).getCMND());
            txdSDT.setText("Số điện thoại: " + dataNhanViens.get(position).getSDT());
            txtDiaChi.setText("Địa chỉ: " + dataNhanViens.get(position).getDiaChi());
            txtLuong.setText("Lương: " + dataNhanViens.get(position).getLuong());



            btnDong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            dialog.show();
//            dialog.getWindow().setAttributes(lp);
        }



}
}
