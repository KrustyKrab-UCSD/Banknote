package com.example.banknote.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.FontFamily;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SpendingAnalysisFragment extends Fragment {

    public static final String TAG = "SpendingAnalysisFrag";
    private PieChart pcPieChart;
    private RecyclerView rvAnalysisTransactions;
    private List<Transaction> analysisRecyclerViewTransactions;
    private SpendingAnalysisTransactionsAdapter adapter;
    private ArrayList<PieEntry> accountsWithTransactions;
    private long totalBalance = 0;
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
        setRecyclerView(view);
        loadPieChart(view);
        loadBarChart(view);
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

    private void queryAccountsForPieChart(List<PieEntry> entries) {
        // Sets PieChart Values and sums up total balance in Users Banknote account
        ParseQuery<Account> query = ParseQuery.getQuery(Account.class);
        query.whereEqualTo(Account.KEY_USER, user);
        query.findInBackground(new FindCallback<Account>() {
            @Override
            public void done(List<Account> accounts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Accounts Query failed", e);
                    return;
                }

                for (Account account : accounts) {
                    if (account.getTotalTransactions() != 0) {
                        entries.add(new PieEntry(account.getTotalTransactions(), account.getAccountName()));
                    }
                    totalBalance += account.getBalance();
                }
            }
        });
    }

    private void loadPieChart(View view) {

        pcPieChart = view.findViewById(R.id.pcPieChart);

        // Styles the PieChart
        pcPieChart.setDrawHoleEnabled(true);
        pcPieChart.setHoleRadius(90);
        pcPieChart.getDescription().setEnabled(false);
        pcPieChart.setDrawEntryLabels(false);

        Log.i(TAG, "reached line 134");

        accountsWithTransactions = new ArrayList<>();
        //queryAccountsForPieChart(accountsWithTransactions);
        Log.i(TAG, "reached line 136 " + accountsWithTransactions.size());
        accountsWithTransactions.add(new PieEntry(1, "No"));
        accountsWithTransactions.add(new PieEntry(2, "Accounts"));
        accountsWithTransactions.add(new PieEntry(3, "Found"));

        Log.i(TAG, "reached line 139 " + accountsWithTransactions.size());
        // Sets center text to total balance in Users Banknote account
        pcPieChart.setCenterText(new SpannableString("$" + totalBalance));
        pcPieChart.setCenterTextSize(24);
        pcPieChart.setCenterTextColor(Color.BLACK);
        pcPieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);

        Log.i(TAG, "reached line 146");
        // To set colors for pie slices
        ArrayList<Integer> colors = new ArrayList<>();
        for (Integer color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        Log.i(TAG, "reached line 153");
        PieDataSet dataSet = new PieDataSet(accountsWithTransactions, "Accounts");
        dataSet.setColors(colors);
        dataSet.setDrawValues(false);

        Log.i(TAG, "reached line 158");
        PieData data = new PieData(dataSet);

        Log.i(TAG, "reached line 161");
        // Loads PieChart into pcPieChart view
        pcPieChart.setData(data);
        pcPieChart.invalidate();

        Log.i(TAG, "reached line 166");
        // This is to animate the Pie Chart
        // Controls the speed and type of animation
        pcPieChart.animateY(750, Easing.EaseInOutCirc);
    }

    private void loadBarChart(View view) {

    }
}