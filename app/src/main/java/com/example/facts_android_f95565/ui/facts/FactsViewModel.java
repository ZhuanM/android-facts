package com.example.facts_android_f95565.ui.facts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FactsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is facts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}