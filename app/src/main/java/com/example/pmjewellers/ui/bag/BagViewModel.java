package com.example.pmjewellers.ui.bag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BagViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BagViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Bag fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}