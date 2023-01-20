package com.example.facts_android_f95565.ui.facts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class FactsViewModel extends ViewModel {

    private MutableLiveData<String> mFacts;
    private FactsAdapter factsAdapter;

    public FactsViewModel() {
        mFacts = new MutableLiveData<>();
        factsAdapter = new FactsAdapter(new ArrayList<String>());
    }

    public LiveData<String> getFacts() {
        return mFacts;
    }

    public void loadFacts() {
        new GetFactsTask(factsAdapter).execute();
    }
}