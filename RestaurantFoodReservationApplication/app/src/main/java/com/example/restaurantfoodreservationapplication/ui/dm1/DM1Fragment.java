package com.example.restaurantfoodreservationapplication.ui.dm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurantfoodreservationapplication.R;

public class DM1Fragment extends Fragment {
    TextView tenmon, giamon;
    Button btnDatMon;
    ImageView imgView;

    private DM1ViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(DM1ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dm1, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        tenmon = (TextView) root.findViewById(R.id.txtTenMon);
        giamon = (TextView) root.findViewById(R.id.txtGiaTien);
        imgView = (ImageView) root.findViewById(R.id.imageView1);
       // btnDatMon = (Button) root.findViewById(R.id.btnDatMon);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
               // tenmon.setText(s);
                //btnDatMon = (Button) root.findViewById(R.id.btnDatMon);
            }
        });


        return root;

    }
}