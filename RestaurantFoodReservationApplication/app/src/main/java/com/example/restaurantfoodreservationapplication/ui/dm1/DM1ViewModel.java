package com.example.restaurantfoodreservationapplication.ui.dm1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DM1ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DM1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}