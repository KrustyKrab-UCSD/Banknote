package com.example.banknote.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.banknote.Adapters.TransactionsAdapter;
import com.example.banknote.Models.Account;
import com.example.banknote.Models.Transaction;
import com.example.banknote.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class IndividualAccountActivity extends AppCompatActivity {

    public static final String TAG = "IndividualAccountAct";
    private Button btnAddTransaction;
    private ImageView btnBack;
    private TextView tvAccountName;
    private TextView tvBalance;
    private RecyclerView rvTransactions;
    private List<Transaction> allTransactions;
    private TransactionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_account);

        btnAddTransaction = findViewById(R.id.btnAddTransaction);
        btnBack = findViewById(R.id.btnBack);
        tvAccountName = findViewById(R.id.tvAccountName);
        tvBalance = findViewById(R.id.tvBalance);
        rvTransactions = findViewById(R.id.rvTransactions);
        allTransactions = new ArrayList<>();

        Account account = Parcels.unwrap(getIntent().getParcelableExtra("account"));
        tvAccountName.setText(account.getAccountName());
        tvBalance.setText("$  " + account.getBalance());

        adapter = new TransactionsAdapter(IndividualAccountActivity.this, allTransactions);
        rvTransactions.setAdapter(adapter);
        rvTransactions.setLayoutManager(new LinearLayoutManager(IndividualAccountActivity.this));

        queryTransactions(account);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonShowPopupWindowClick(view);
            }
        });
    }

    public void onButtonShowPopupWindowClick(View view) {
        // inflate layout of popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.create_transaction_popup, null);

        // create popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    protected void queryTransactions(Account account) {
        ParseQuery<Transaction> query = ParseQuery.getQuery(Transaction.class);
        query.include(Transaction.KEY_ACCOUNT);
        query.whereEqualTo(Transaction.KEY_ACCOUNT, account);
        query.addDescendingOrder(Transaction.KEY_DATE);
        query.findInBackground(new FindCallback<Transaction>() {
            @Override
            public void done(List<Transaction> transactions, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting transactions", e);
                    return;
                }

                for (Transaction transaction : transactions) {
                    Log.i(TAG, "Transaction: " + transaction.getTransactionAmount() + " at " + transaction.getDate());
                }

                allTransactions.addAll(transactions);
                adapter.notifyDataSetChanged();
            }
        });
    }
}