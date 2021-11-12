package com.example.banknote.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.banknote.BuildConfig;
import com.example.banknote.R;
import com.github.mikephil.charting.charts.PieChart;

public class SpendingAnalysisFragment extends Fragment {

    PieChart pcPieChart;
    RecyclerView rvAnalysisTransactions;

    public SpendingAnalysisFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spending_analysis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pcPieChart = view.findViewById(R.id.pcPieChart);
        rvAnalysisTransactions = view.findViewById(R.id.rvAnalysisTransactions);
    }
}