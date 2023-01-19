package com.example.facts_android_f95565.ui.facts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.facts_android_f95565.databinding.FragmentFactsBinding;

public class FactsFragment extends Fragment {

    private FragmentFactsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FactsViewModel factsViewModel =
                new ViewModelProvider(this).get(FactsViewModel.class);

        binding = FragmentFactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFacts;
        factsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}