package com.example.restaurantfoodreservationapplication.ui.dm2;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.restaurantfoodreservationapplication.Class.Mon_An;
import com.example.restaurantfoodreservationapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DM2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DM2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}