package com.example.restaurantfoodreservationapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantfoodreservationapplication.Class.Ban_An;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BanAnAdapter extends RecyclerView.Adapter<BanAnAdapter.ViewHolder> {

    ArrayList<Ban_An> dataBanAns;
    Context context;
    public static int DongVuaBiXoa = -1;
    private DatabaseReference mDatabase;

    public BanAnAdapter(ArrayList<Ban_An> dataBanAns, Context context) {
        this.dataBanAns = dataBanAns;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.row_banan, parent, false);

        return new ViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaBan.setText("Mã bàn: " + dataBanAns.get(position).getMaBan());
        holder.txtTenBan.setText("Tên bàn: " + dataBanAns.get(position).getTenBan());
        holder.txtSoLuong.setText("Số lượng chỗ: " + dataBanAns.get(position).getSoCho() + "");
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
            txtMaBan = (TextView) itemView.findViewById(R.id.txtMaBanAn);
            txtTenBan = (TextView) itemView.findViewById(R.id.txtTenBanAn);
            txtSoLuong = (TextView) itemView.findViewById(R.id.txtSoLuongCho);

            btnsua = (Button) itemView.findViewById(R.id.btnSuaDongBanAn);
            btnxoa = (Button) itemView.findViewById(R.id.btnXoaBan);
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowdialogXacNhan();
                }
            });
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit();
                }
            });
        }

        private void DialogEdit() {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_thembanan);
            dialog.setCanceledOnTouchOutside(false); // nhap ra ngoai khong tat dialog
            // ánh xạ
            TextView texviewEdit = (TextView) dialog.findViewById(R.id.txtThemBanAn);
            EditText edtMaBan = (EditText) dialog.findViewById(R.id.edtMaBanAn);
            EditText edtTenBan = (EditText) dialog.findViewById(R.id.edtTenBanAn);
            EditText edtSoCho = (EditText) dialog.findViewById(R.id.edtSoLuongCho);
            Button btnDongY = (Button) dialog.findViewById(R.id.buttonDongYThemBan);
            Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuyThemBan);
            edtMaBan.setEnabled(false);
            texviewEdit.setText("Sửa bàn ăn");
            edtMaBan.setText(dataBanAns.get(getAdapterPosition()).getMaBan().toString());
            edtTenBan.setText(dataBanAns.get(getAdapterPosition()).getTenBan().toString());
            edtSoCho.setText(dataBanAns.get(getAdapterPosition()).getSoCho() + "", TextView.BufferType.EDITABLE);
            btnDongY.setText("Ok");
            btnHuy.setText("Hủy");
            btnDongY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtMaBan.getText().toString().trim().length() == 0 || edtTenBan.getText().toString().trim().length() == 0 || edtSoCho.getText().toString().length() == 0) {
                        Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (Integer.parseInt(edtSoCho.getText().toString()) < 0) {
                        Toast.makeText(context, "Số chỗ phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Query query = mDatabase.child("BanAn").orderByChild("maBan").equalTo(edtMaBan.getText().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                HashMap hashMap = new HashMap();
                                Ban_An ban_an = new Ban_An(edtMaBan.getText().toString(), edtTenBan.getText().toString(), Integer.parseInt(edtSoCho.getText().toString()));
                                hashMap.put(ds.getKey(), ban_an);
                                mDatabase.child("BanAn").updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null)
                                        {
                                            Toast.makeText(context, "Cập nhật thành Công", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                        else
                                            Toast.makeText(context, "Cập nhật thất Bại!", Toast.LENGTH_SHORT).show();

                                    }
                                });
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

        }

        private void ShowdialogXacNhan() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Bạn có chắc xóa không ?");

            alertDialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Query query = mDatabase.child("BanAn").orderByChild("maBan").equalTo(dataBanAns.get(getAdapterPosition()).getMaBan().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot singleSnapshot : snapshot.getChildren()) {

                                mDatabase.child("BanAn").child(singleSnapshot.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if(error == null)
                                        {
                                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                            Toast.makeText(context, "Xóa Thất Bại!!!", Toast.LENGTH_SHORT).show();
                                    }

                                });
                                DongVuaBiXoa = getAdapterPosition();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });//Sua lai ban cho phu hop

                }
            });
            alertDialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }


}
