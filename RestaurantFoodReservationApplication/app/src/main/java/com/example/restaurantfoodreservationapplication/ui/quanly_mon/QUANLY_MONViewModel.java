package com.example.restaurantfoodreservationapplication.ui.quanly_mon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QUANLY_MONViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QUANLY_MONViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is QUANLY_MON fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}