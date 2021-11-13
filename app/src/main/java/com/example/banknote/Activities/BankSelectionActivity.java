package com.example.banknote.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.banknote.Adapters.BankSelectionAdapter;
import com.example.banknote.Models.Bank;
import com.example.banknote.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BankSelectionActivity extends AppCompatActivity {

    private static final String TAG = "BankSelectionActivity";
    private RecyclerView rvBankSelection;
    private BankSelectionAdapter adapter;
    private List<Bank> allBanks;
    private Button btnSelect;
    private ImageButton ibReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection);
        rvBankSelection = findViewById(R.id.rvBankSelection);
        btnSelect = findViewById(R.id.btnSelect);
        ibReturn = findViewById(R.id.ibReturn);


        allBanks = new ArrayList<>();
        adapter = new BankSelectionAdapter(this, allBanks);
        rvBankSelection.setAdapter(adapter);
        rvBankSelection.setLayoutManager(new LinearLayoutManager(this));
        queryBanks();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = BankSelectionAdapter.getIntent();
                setResult(RESULT_OK, data);
                finish();
            }
        });

        ibReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void queryBanks() {
        ParseQuery<Bank> query = ParseQuery.getQuery(Bank.class);
        query.findInBackground(new FindCallback<Bank>() {
            @Override
            public void done(List<Bank> banks, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting Banks", e);
                    return;
                }
                allBanks.addAll(banks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}