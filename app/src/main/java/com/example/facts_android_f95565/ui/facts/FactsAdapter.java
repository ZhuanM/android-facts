package com.example.facts_android_f95565.ui.facts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.R;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsViewHolder> {
    private List<String> facts;

    public FactsAdapter(List<String> facts) {
        this.facts = facts;
    }

    @NonNull
    @Override
    public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_list_item, parent, false);
        return new FactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactsViewHolder holder, int position) {
        // Bind the data to the view holder
        String fact = facts.get(position);
        holder.bind(fact);
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public void setData(List<String> facts) {
        this.facts = facts;
        notifyDataSetChanged();
    }
}
