package com.example.facts_android_f95565.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
    private List<String> favoriteFacts;
    private Context parentContext;

    public FavoritesAdapter(List<String> listOfFacts, Context context) {
        favoriteFacts = listOfFacts;
        parentContext = context;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_list_item, parent, false);
        return new FavoritesViewHolder(view, favoriteFacts, parentContext);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        // Bind the data to the view holder
        String fact = favoriteFacts.get(position);
        holder.bind(fact);
    }

    @Override
    public int getItemCount() {
        return favoriteFacts.size();
    }

    public void setData(List<String> facts) {
        this.favoriteFacts = facts;
        notifyDataSetChanged();
    }
}