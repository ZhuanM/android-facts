package com.example.facts_android_f95565.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.facts_android_f95565.ui.facts.FactsAdapter;
import com.example.facts_android_f95565.ui.facts.GetFactsTask;

import java.util.ArrayList;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<String> mFacts;
    private FavoritesAdapter favoritesAdapter;

    public FavoritesViewModel() {
        mFacts = new MutableLiveData<>();
        favoritesAdapter = new FavoritesAdapter(new ArrayList<String>(), null);
    }

    public LiveData<String> getFacts() {
        return mFacts;
    }

//    public void loadFacts() {
//        new GetFavoritesTask(favoritesAdapter).execute();
//    }
}