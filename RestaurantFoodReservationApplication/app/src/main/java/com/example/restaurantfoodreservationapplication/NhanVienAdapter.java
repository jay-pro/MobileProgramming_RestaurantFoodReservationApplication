package com.example.restaurantfoodreservationapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Nhan_Vien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    ArrayList<Nhan_Vien> dataNhanViens;
    Context context;
    Spinner spinner;
    ArrayAdapter arrayAdapterChucVu;
    String TenChucVu = "";
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


            btnchitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogDetail(getAdapterPosition());
                }
            });
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                DialogEdit();
                }
            });
        }

        private void ShowdialogXacNhan()
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("B???n c?? ch???c x??a kh??ng ?");

            alertDialog.setPositiveButton("X??c nh???n", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Query query = mDatabase.child("NhanVien").orderByChild("id").equalTo(dataNhanViens.get(getAdapterPosition()).getID().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot singleSnapshot : snapshot.getChildren())
                            {

                                mDatabase.child("NhanVien").child(singleSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null)
                                            Toast.makeText(context, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(context, "X??a b??? l???i!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
//                                DongVuaBiXoa = getAdapterPosition();
                                
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });//Sua lai ban cho phu hop

                }
            });
            alertDialog.setNegativeButton("H???y", new DialogInterface.OnClickListener() {
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
            // ??nh x???
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
            txtMaNV.setText("M?? nh??n vi??n: " + dataNhanViens.get(position).getID());
            txtChucVu.setText("Ch???c v???: " + dataNhanViens.get(position).getChucVu());
            txtTenNV.setText("H??? T??n: " + dataNhanViens.get(position).getHoTen());
            txtGioiTinh.setText("Gi???i t??nh: " + dataNhanViens.get(position).getGioiTinh());
            txtCMND.setText("CMND: " + dataNhanViens.get(position).getCMND());
            txdSDT.setText("S??? ??i???n tho???i: " + dataNhanViens.get(position).getSDT());
            txtDiaChi.setText("?????a ch???: " + dataNhanViens.get(position).getDiaChi());
            txtLuong.setText("L????ng: " + dataNhanViens.get(position).getLuong());


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
            int positionTenChucVu = 0;
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_themnhanvien);
            dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
            // ??nh x???
            TextView txtSuaNV = (TextView) dialog.findViewById(R.id.txtThemNhanVien) ;
            EditText edtMaNV = (EditText) dialog.findViewById(R.id.edtMaNV);
            EditText edtTenNV = (EditText) dialog.findViewById(R.id.edtHoTen);
            EditText edtGioiTinh = (EditText) dialog.findViewById(R.id.edtGioiTinh);
            EditText edtCMND = (EditText) dialog.findViewById(R.id.edtCMND);
            EditText edtSDT = (EditText) dialog.findViewById(R.id.edtSDT);
            EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edtDiaChi);
            EditText edtLuong = (EditText) dialog.findViewById(R.id.edtLuong);
            Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongYThemNV);
            Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyThemNV);

            edtMaNV.setEnabled(false);
            txtSuaNV.setText("S???a Nh??n vi??n");

            edtMaNV.setText(dataNhanViens.get(getAdapterPosition()).getID().toString());
            edtTenNV.setText(dataNhanViens.get(getAdapterPosition()).getHoTen().toString());
            edtGioiTinh.setText(dataNhanViens.get(getAdapterPosition()).getGioiTinh().toString());
            edtCMND.setText(dataNhanViens.get(getAdapterPosition()).getCMND().toString(),TextView.BufferType.EDITABLE);
            edtSDT.setText(dataNhanViens.get(getAdapterPosition()).getSDT().toString(),TextView.BufferType.EDITABLE);
            edtDiaChi.setText(dataNhanViens.get(getAdapterPosition()).getDiaChi().toString());
            edtLuong.setText(dataNhanViens.get(getAdapterPosition()).getLuong()+"",TextView.BufferType.EDITABLE);

            btnDongY.setText("Ok");

            TenChucVu = QLNhanVienActivity.arrayListChucVu.get(positionTenChucVu).toString();
            Spinner spinner =  spinner = (Spinner) dialog.findViewById(R.id.spinnerChucVu);
            arrayAdapterChucVu = new ArrayAdapter(context, android.R.layout.simple_spinner_item,QLNhanVienActivity.arrayListChucVu);
            arrayAdapterChucVu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapterChucVu);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TenChucVu = QLNhanVienActivity.arrayListChucVu.get(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            btnDongY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtMaNV.getText().toString().trim().length() == 0 ||
                            edtTenNV.getText().toString().trim().length() == 0|| edtGioiTinh.getText().toString().trim().length() == 0 ||
                            edtCMND.getText().toString().trim().length() == 0 || edtSDT.getText().toString().trim().length() == 0 || edtDiaChi.getText().toString().trim().length() == 0 || edtLuong.getText().toString().trim().length() == 0)
                    {
                        Toast.makeText(context, "Vui l??ng nh???p ????? th??ng tin!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Query query = mDatabase.child("NhanVien").orderByChild("id").equalTo(edtMaNV.getText().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()){
                                HashMap hashMap = new HashMap();
                                Nhan_Vien nv = new Nhan_Vien(edtMaNV.getText().toString(),TenChucVu,edtTenNV.getText().toString(),edtGioiTinh.getText().toString(),edtCMND.getText().toString(), edtSDT.getText().toString(),edtDiaChi.getText().toString(),Double.parseDouble(edtLuong.getText().toString()) ,dataNhanViens.get(getAdapterPosition()).getHinhAnh().toString(),"","");
                                hashMap.put(ds.getKey(), nv);
                                mDatabase.child("NhanVien").updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null)
                                        {
                                            Toast.makeText(context, "C???p nh???t th??nh c??ng", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                        else
                                            Toast.makeText(context, "C???p nh???t l???i!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Toast.makeText(context, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            });



            btnHuy.setOnClickListener(new View.OnClickListener() {
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
