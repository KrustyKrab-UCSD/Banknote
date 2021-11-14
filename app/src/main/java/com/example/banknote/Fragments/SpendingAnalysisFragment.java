package com.example.banknote.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.banknote.Adapters.SpendingAnalysisTransactionsAdapter;
import com.example.banknote.BuildConfig;
import com.example.banknote.Models.Account;
import com.example.banknote.Models.Transaction;
import com.example.banknote.R;
import com.github.mikephil.charting.charts.PieChart;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class SpendingAnalysisFragment extends Fragment {

    public static final String TAG = "SpendingAnalysisFrag";
    PieChart pcPieChart;
    RecyclerView rvAnalysisTransactions;
    List<Transaction> analysisRecyclerViewTransactions;
    SpendingAnalysisTransactionsAdapter adapter;
    private static final ParseUser user = ParseUser.getCurrentUser();

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
        setRecyclerView(view);
    }

    protected void queryTransactions() {
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.include(Transaction.KEY_ACCOUNT);
        query.whereEqualTo(Transaction.KEY_USER, user);
        query.addDescendingOrder(Transaction.KEY_DATE);
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> transactions, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting transactions", e);
                    return;
                }

                analysisRecyclerViewTransactions.addAll(transactions);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setRecyclerView(View view) {
        rvAnalysisTransactions = view.findViewById(R.id.rvAnalysisTransactions);
        analysisRecyclerViewTransactions = new ArrayList<>();
        adapter = new SpendingAnalysisTransactionsAdapter(view.getContext(), analysisRecyclerViewTransactions);
        rvAnalysisTransactions.setAdapter(adapter);
        rvAnalysisTransactions.setLayoutManager(new LinearLayoutManager(view.getContext()));
        queryTransactions();
    }
}