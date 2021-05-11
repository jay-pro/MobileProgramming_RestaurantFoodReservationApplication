package com.example.restaurantfoodreservationapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DonDatMonAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<DonDatMon> Listdondat;

    public DonDatMonAdapter(Context context, int layout, List<DonDatMon> listdondat) {
        this.context = context;
        this.layout = layout;
        Listdondat = listdondat;
    }
    private class ViewHolder {
        ImageView imgHinh;
        TextView txtBanSo,txtMaDonDat,txtThoiGian;

    }
    @Override
    public int getCount() {
        return Listdondat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new ViewHolder();
            // ánh xạ view
            holder.txtBanSo  = (TextView) convertView.findViewById(R.id.textviewBan);
            holder.txtMaDonDat  = (TextView) convertView.findViewById(R.id.textviewMaDonDat);
            holder.txtThoiGian = (TextView) convertView.findViewById(R.id.textviewThoiGian);
            holder.imgHinh  = (ImageView) convertView.findViewById(R.id.imageviewHinh);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        // gán giá trị
        DonDatMon dondat = Listdondat.get(position);

        holder.txtBanSo.setText(dondat.getMaBan());
        holder.txtMaDonDat.setText(dondat.getMaDonDat());
        holder.txtThoiGian.setText(dondat.getThoiGian());
        holder.imgHinh.setImageResource(dondat.getHinh());
        return convertView;
    }
}
