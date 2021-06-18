package com.example.restaurantfoodreservationapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;
import com.example.restaurantfoodreservationapplication.Class.Thanh_Toan;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    ArrayList<Nhan_Vien> dataNhanViens;
    Context context;
    public static int DongVuaBiXoa = -1;
    private DatabaseReference mDatabase;
    public NhanVienAdapter(ArrayList<Nhan_Vien> dataNhanViens, Context context) {
        this.dataNhanViens = dataNhanViens;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_nhanvien, parent, false);

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
        // Picasso.with(context).load(dataNhanViens.get(position).getHinhAnh()).into(holder.imgHinh);
        try {
            Picasso.with(context).load(dataNhanViens.get(position).getHinhAnh().toString()).into(holder.imgHinh);
        } catch (Exception e) {

        }

    }

    @Override
    public int getItemCount() {
        return dataNhanViens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMaNV;
        ImageView imgHinh;
        Button btnchitiet, btnsua, btnxoa;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtTenNhanVien);
            txtMaNV = (TextView) itemView.findViewById(R.id.txtMaNhanVien);
            imgHinh = (ImageView) itemView.findViewById(R.id.imageViewNhanVien);
            btnchitiet = (Button) itemView.findViewById(R.id.buttonChiTietNV);
            btnsua = (Button) itemView.findViewById(R.id.btnSuaNV);
            btnxoa = (Button) itemView.findViewById(R.id.buttonXoaNV);
            btnxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ShowdialogXacNhan();
                }
            });
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    DialogEdit();
                }
            });
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

        private void ShowdialogXacNhan()
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Bạn có chắc xóa không ?");

            alertDialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Query query = mDatabase.child("NhanVien").orderByChild("id").equalTo(dataNhanViens.get(getAdapterPosition()).getID().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot singleSnapshot : snapshot.getChildren())
                            {

                                mDatabase.child("NhanVien").child(singleSnapshot.getKey()).removeValue();
                                DongVuaBiXoa = getAdapterPosition();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });//Sua lai ban cho phu hop

                }
            });
            alertDialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
        private void DialogDetail(int position) {
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
        private  void  DialogEdit() {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_themnhanvien);
            dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
            // ánh xạ
//            TextView txtSuaNV = (TextView) dialog.findViewById(R.id.txtThemNhanVien) ;
//            EditText edtMaNV = (EditText) dialog.findViewById(R.id.edtMaNV);
//            EditText edtChucVu = (EditText) dialog.findViewById(R.id.edtChucVu);
//            EditText edtTenNV = (EditText) dialog.findViewById(R.id.edtHoTen);
//            EditText edtGioiTinh = (EditText) dialog.findViewById(R.id.edtGioiTinh);
//            EditText edtCMND = (EditText) dialog.findViewById(R.id.edtCMND);
//            EditText edtSDT = (EditText) dialog.findViewById(R.id.edtSDT);
//            EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edtDiaChi);
//            EditText edtLuong = (EditText) dialog.findViewById(R.id.edtLuong);
//            Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongYThemNV);
//            Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyThemNV);
//
//            edtMaNV.setEnabled(false);
//            txtSuaNV.setText("Sửa Nhân viên");
//
//            edtMaNV.setText(dataNhanViens.get(getAdapterPosition()).getID().toString());
//            edtChucVu.setText(dataNhanViens.get(getAdapterPosition()).getChucVu().toString());
//            edtTenNV.setText(dataNhanViens.get(getAdapterPosition()).getHoTen().toString());
//            edtGioiTinh.setText(dataNhanViens.get(getAdapterPosition()).getGioiTinh().toString());
//            edtCMND.setText(dataNhanViens.get(getAdapterPosition()).getCMND().toString(),TextView.BufferType.EDITABLE);
//            edtSDT.setText(dataNhanViens.get(getAdapterPosition()).getSDT().toString(),TextView.BufferType.EDITABLE);
//            edtDiaChi.setText(dataNhanViens.get(getAdapterPosition()).getID().toString());
//            edtLuong.setText(dataNhanViens.get(getAdapterPosition()).getID().toString(),TextView.BufferType.EDITABLE);
//
//            btnDongY.setText("Ok");
//
//
//            btnDongY.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(edtMaNV.getText().toString().trim().length() == 0 ||  edtChucVu.getText().toString().trim().length() == 0 ||
//                            edtTenNV.getText().toString().trim().length() == 0|| edtGioiTinh.getText().toString().trim().length() == 0 ||
//                            edtCMND.getText().toString().trim().length() == 0 || edtSDT.getText().toString().trim().length() == 0 || edtDiaChi.getText().toString().trim().length() == 0 || edtLuong.getText().toString().trim().length() == 0)
//                    {
//                        Toast.makeText(context, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    mDatabase = FirebaseDatabase.getInstance().getReference();
//                    Query query = mDatabase.child("NhanVien").orderByChild("id").equalTo(edtMaNV.getText().toString());
//                    query.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for(DataSnapshot ds : snapshot.getChildren()){
//                                HashMap hashMap = new HashMap();
//                                Nhan_Vien nv = new Nhan_Vien(edtMaNV.getText().toString(), edtChucVu.getText().toString(),edtTenNV.getText().toString(),edtGioiTinh.getText().toString(),edtCMND.getText().toString(), edtSDT.getText().toString(),edtDiaChi.getText().toString(),Double.parseDouble(edtLuong.getText().toString()) ,dataNhanViens.get(getAdapterPosition()).getHinhAnh().toString());
//                                hashMap.put(ds.getKey(), nv);
//                                mDatabase.child("NhanVien").updateChildren(hashMap);
//                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                                dialog.cancel();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//                }
//            });



//            btnHuy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.cancel();
//                }
//            });
            dialog.show();
//            dialog.getWindow().setAttributes(lp);
        }

    }
}
