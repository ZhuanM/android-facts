package com.example.facts_android_f95565.ui.facts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.R;

import java.util.ArrayList;
import java.util.List;

public class FactsFragment extends Fragment {

    private List<String> facts;
    private FactsAdapter factsAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facts, container, false);

        // Initialize the recycler view
        recyclerView = view.findViewById(R.id.facts_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the facts list and adapter
        facts = new ArrayList<>();
        factsAdapter = new FactsAdapter(facts);

        // Set the adapter on the recycler view
        recyclerView.setAdapter(factsAdapter);

        // Execute the async task
        new GetFactsTask(factsAdapter).execute();

        return view;
    }
}