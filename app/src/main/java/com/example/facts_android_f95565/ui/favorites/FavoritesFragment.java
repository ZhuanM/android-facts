package com.example.facts_android_f95565.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.DatabaseHelper;
import com.example.facts_android_f95565.R;
import com.example.facts_android_f95565.databinding.FragmentFavoritesBinding;
import com.example.facts_android_f95565.ui.facts.FactsAdapter;
import com.example.facts_android_f95565.ui.facts.GetFactsTask;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;
    private List<String> favoriteFacts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Initialize the recycler view
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the facts list and adapter
        favoriteFacts = new ArrayList<>();
        favoritesAdapter = new FavoritesAdapter(favoriteFacts, view.getContext());

        // Set the adapter on the recycler view
        recyclerView.setAdapter(favoritesAdapter);

        // Get the current username from SharedPreferences
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String currentUsername = sharedPreferences.getString("username", "");

        // Get user's favorite facts
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        GetFavoritesTask getFavoritesTask = new GetFavoritesTask(dbHelper, favoritesAdapter, currentUsername);
        getFavoritesTask.execute();

        return view;
    }
}