package com.example.facts_android_f95565.ui.facts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.R;

public class FactsViewHolder extends RecyclerView.ViewHolder {

    private TextView factTextView;

    public FactsViewHolder(@NonNull View itemView) {
        super(itemView);
        factTextView = itemView.findViewById(R.id.fact_text_view);
    }

    public void bind(String fact) {
        factTextView.setText(fact);
    }
}