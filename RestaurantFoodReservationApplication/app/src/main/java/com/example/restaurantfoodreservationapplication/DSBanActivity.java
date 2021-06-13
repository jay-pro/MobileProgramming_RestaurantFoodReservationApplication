package com.example.restaurantfoodreservationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.restaurantfoodreservationapplication.DatMonActivity.MaBan;

public class DSBanActivity extends AppCompatActivity {
    Button btnBan1, btnBan2, btnBan3, btnBan4, btnBan5, btnBan6, btnBan7, btnBan8, btnBan9;
    ArrayList<Button> arrayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_dsban);
        AnhXa();
        arrayBtn = new ArrayList<Button>();  //khởi tạo mảng lưu các image button
        arrayBtn.add(btnBan1); arrayBtn.add(btnBan2); arrayBtn.add(btnBan3);
        arrayBtn.add(btnBan4); arrayBtn.add(btnBan5); arrayBtn.add(btnBan6);
        arrayBtn.add(btnBan7); arrayBtn.add(btnBan8); arrayBtn.add(btnBan9);

        //int i =0;
        for(int i = 0; i<arrayBtn.size(); i++) {
            int finalI = i;
            arrayBtn.get(finalI).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaBan = String.format("%d", finalI + 1);
                    Intent intent = new Intent(DSBanActivity.this, DatMonActivity.class);
                    startActivity(intent);
                }
            });
        }


    }
    public void AnhXa() {
        btnBan1 = (Button) findViewById(R.id.btnBan1);
        btnBan2 = (Button) findViewById(R.id.btnBan2);
        btnBan3 = (Button) findViewById(R.id.btnBan3);
        btnBan4 = (Button) findViewById(R.id.btnBan4);
        btnBan5 = (Button) findViewById(R.id.btnBan5);
        btnBan6 = (Button) findViewById(R.id.btnBan6);
        btnBan7 = (Button) findViewById(R.id.btnBan7);
        btnBan8 = (Button) findViewById(R.id.btnBan8);
        btnBan9 = (Button) findViewById(R.id.btnBan9);
    }
}