package com.example.banknote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.banknote.Models.Bank;

import java.util.List;

public class BankSelectionActivity extends AppCompatActivity {

    private RecyclerView rvBankSelection;
    private BankSelectionAdapter adapter;
    private List<Bank> allBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_selection);

        rvBankSelection = findViewById(R.id.rvBankSelection);

        // Initialize with all banks we have in Parse Database.
        // This will be everything under Bank class in Parse
        // Would require Parse Query (Exact one in the README.md)
        // Work in progress. (Gonna ask questions on Discord later)
        allBanks = null;

        adapter = new BankSelectionAdapter(this, allBanks);
        rvBankSelection.setAdapter(adapter);
        rvBankSelection.setLayoutManager(new LinearLayoutManager(this));
    }
}