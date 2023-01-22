package com.example.facts_android_f95565.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facts_android_f95565.DatabaseHelper;
import com.example.facts_android_f95565.R;

import java.util.List;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    private TextView factTextView;
    private ImageView heartIcon;
    private List<String> facts;
    private Context parentContext;

    public FavoritesViewHolder(@NonNull View itemView, List<String> listOfFacts, Context context) {
        super(itemView);
        factTextView = itemView.findViewById(R.id.fact_text_view);
        heartIcon = itemView.findViewById(R.id.heart_icon);
        facts = listOfFacts;
        parentContext = context;

        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getBindingAdapterPosition();
                String fact = facts.get(position);

                // Get the current username from SharedPreferences
                SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                final String currentUsername = sharedPreferences.getString("username", "");

                DatabaseHelper db = new DatabaseHelper(view.getContext());
                db.saveFact(fact, currentUsername, parentContext);
            }
        });
    }

    public void bind(String fact) {
        factTextView.setText(fact);
    }
}