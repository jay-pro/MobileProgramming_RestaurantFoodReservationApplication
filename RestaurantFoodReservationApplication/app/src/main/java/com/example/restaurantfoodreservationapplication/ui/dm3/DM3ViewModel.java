package com.example.restaurantfoodreservationapplication.ui.dm3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DM3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DM3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}