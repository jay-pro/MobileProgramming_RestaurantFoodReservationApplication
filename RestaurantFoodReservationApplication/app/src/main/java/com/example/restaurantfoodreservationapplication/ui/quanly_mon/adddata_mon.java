package com.example.restaurantfoodreservationapplication.ui.quanly_mon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restaurantfoodreservationapplication.R;
import com.example.restaurantfoodreservationapplication.ui.quanly_mon.QUANLY_MONFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata_mon extends AppCompatActivity {

    EditText tenmon, giamon, purl;
    Button submit, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata_mon);

        tenmon = (EditText) findViewById(R.id.add_tenmon);
        giamon = (EditText) findViewById(R.id.add_giamon);
        purl = (EditText) findViewById(R.id.add_purlmon);
        submit = (Button) findViewById(R.id.add_submitmon);
        back = (Button) findViewById(R.id.add_backmon);

        back.setOnClickListener(new View.OnClickListener() {//nhấn nút BACK thì quay lại fragment QUẢN LÝ MÓN
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QUANLY_MONFragment.class));
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();
            }
        });


    }

    private void processinsert() {
        Map<String,Object> map = new HashMap<>();
        map.put("tenmon",tenmon.getText().toString());
        map.put("giamon",giamon.getText().toString());
        map.put("purl",purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("quanlymon").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        tenmon.setText("");
                        giamon.setText("");
                        purl.setText("");
                        Toast.makeText(getApplicationContext(), "Thêm món thành công", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tenmon.setText("");
                        giamon.setText("");
                        purl.setText("");
                        Toast.makeText(getApplicationContext(), "Thêm món thất bại", Toast.LENGTH_LONG).show();
                    }
                });
    }
}