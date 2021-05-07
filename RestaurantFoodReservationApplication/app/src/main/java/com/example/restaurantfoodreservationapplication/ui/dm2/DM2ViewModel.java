package com.example.restaurantfoodreservationapplication.ui.dm2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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