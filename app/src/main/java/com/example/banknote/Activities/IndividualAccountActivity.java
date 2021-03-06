package com.example.banknote.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.banknote.Adapters.TransactionsAdapter;
import com.example.banknote.Models.Account;
import com.example.banknote.Models.Transaction;
import com.example.banknote.R;
import com.google.android.material.button.MaterialButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IndividualAccountActivity extends AppCompatActivity {

    public static final String TAG = "IndividualAccountAct";
    private MaterialButton btnAddTransaction;
    private ImageView btnBack;
    private TextView tvTitle;
    private TextView tvBalance;
    private RecyclerView rvTransactions;
    private List<Transaction> allTransactions;
    private TransactionsAdapter adapter;
    private Account account;

    private AlertDialog newTransactionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_account);

        btnAddTransaction = findViewById(R.id.btnAddTransaction);
        btnBack = findViewById(R.id.btnBack);
        tvTitle = findViewById(R.id.tvTitle);
        tvBalance = findViewById(R.id.tvBalance);
        rvTransactions = findViewById(R.id.rvTransactions);
        allTransactions = new ArrayList<>();

        account = getIntent().getParcelableExtra("account");
        Log.i(TAG, "Account Name: " + account.getAccountName());
        tvTitle.setText(account.getAccountName());
        tvBalance.setText("$  " + account.getBalance() + "0");

        TransactionsAdapter.OnClickListener onClickListener = new TransactionsAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Transaction transaction = allTransactions.get(position);
                Log.i(TAG, rvTransactions.getChildAt(position).toString());
//                onButtonShowPopupWindowClick(rvTransactions.getChildAt(position),
//                        transaction.getTransactionAmount(),
//                        transaction.getIsSpending(),
//                        transaction.getDate(),
//                        transaction.getDescription(),
//                        position);
                showAddTransactionDialog(transaction, position);
            }
        };

        adapter = new TransactionsAdapter(IndividualAccountActivity.this, onClickListener, allTransactions);
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
                Log.i(TAG, "View: " + view.toString());
//                onButtonShowPopupWindowClick(view, null, null, null, null, -1);
                showAddTransactionDialog(null, -1);
            }
        });
    }

    public void onButtonShowPopupWindowClick(View view, Double amount, Boolean isSpending, Date date, String description, int position) {
        // inflate layout of popup window
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.create_transaction_popup, null);

        // create popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
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

        EditText etTransactionAmount = popupView.findViewById(R.id.etTransactionAmount);
        RadioGroup rbGroup = popupView.findViewById(R.id.rbGroup);
        RadioButton rbSpending = popupView.findViewById(R.id.rbSpending);
        RadioButton rbSaving = popupView.findViewById(R.id.rbSaving);

        EditText etDate = popupView.findViewById(R.id.etDate);
        EditText etDescription = popupView.findViewById(R.id.etDescription);

        if (amount != null) {
            etTransactionAmount.setText(amount.toString());
        }

        if (isSpending != null) {
            if (isSpending) {
                rbSpending.setChecked(true);
            } else {
                rbSaving.setChecked(true);
            }
        }

        if (date != null) {
            etDate.setText("" + (date.getMonth() + 1) + "/" + date.getDay() + "/" + (date.getYear() % 100));
        }

        if (description != null) {
            etDescription.setText(description);
        }

        Log.i(TAG, "Transaction amount: " + etTransactionAmount.getText().toString());
        Log.i(TAG, "Date: " + etDate.getText().toString());
        Log.i(TAG, "Description: " + etDescription.getText().toString());

        Button btnCreateTransaction = popupView.findViewById(R.id.btnCreateTransaction);
        Button btnCancel = popupView.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        btnCreateTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String transactionAmountText = etTransactionAmount.getText().toString();
                if (transactionAmountText.isEmpty()) {
                    Toast.makeText(IndividualAccountActivity.this, "Transaction amount can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                double transactionAmount;
                try {
                    transactionAmount = Double.parseDouble(transactionAmountText);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(IndividualAccountActivity.this, "Transaction amount is invalid!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error in parsing transaction amount", e);
                    return;
                }

                String dateString = etDate.getText().toString();
                if (dateString.isEmpty()) {
                    Toast.makeText(IndividualAccountActivity.this, "Date can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] dateSplit = dateString.split("/");
                Date date;
                try {
                    int year = Integer.parseInt(dateSplit[2]);
                    int month = Integer.parseInt(dateSplit[0]);
                    int day = Integer.parseInt(dateSplit[1]);
                    date = new Date(year, month, day);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(IndividualAccountActivity.this, "Date is invalid!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error in parsing date", e);
                    return;
                }
                catch (IndexOutOfBoundsException e) {
                    Toast.makeText(IndividualAccountActivity.this, "Date is invalid!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error in parsing date", e);
                    return;
                }

                String description = etDescription.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(IndividualAccountActivity.this, "Description can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (position == -1) {
                    saveTransaction(transactionAmount,
                            rbGroup.getCheckedRadioButtonId() == R.id.rbSpending, date, description);
                    popupWindow.dismiss();
                    queryTransactions(account);
                }
                else {
                    Transaction transaction = allTransactions.get(position);
                    transaction.setTransactionAmount(transactionAmount);
                    transaction.setIsSpending(rbGroup.getCheckedRadioButtonId() == R.id.rbSpending);
                    transaction.setDate(date);
                    transaction.setDescription(description);
                    transaction.setUser(ParseUser.getCurrentUser());
                    transaction.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Log.e(TAG, "Error while saving", e);
                                Toast.makeText(IndividualAccountActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Log.i(TAG, "Transaction save was successful!");
                        }
                    });
                    adapter.notifyDataSetChanged();
                    popupWindow.dismiss();

                }

            }
        });

    }

    private void saveTransaction(double transactionAmount, boolean isSpending, Date date, String description) {
        Transaction transaction = new Transaction();
        transaction.setIsSpending(isSpending);
        transaction.setTransactionAmount(transactionAmount);
        transaction.setDate(date);
        transaction.setDescription(description);
        transaction.setAccount(account);
        transaction.setUser(account.getUser());
        transaction.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(IndividualAccountActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "Transaction save was successful!");
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

                allTransactions.clear();
                allTransactions.addAll(transactions);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void showAddTransactionDialog(Transaction transaction, int position) {
        if (newTransactionDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(IndividualAccountActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.create_transaction_popup,
                    (ViewGroup) findViewById(R.id.newTransactionContainer)
            );
            builder.setView(view);

            newTransactionDialog = builder.create();

            if (newTransactionDialog.getWindow() != null) {
                newTransactionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            TextView title = view.findViewById(R.id.Title);
            EditText etTransactionAmount = view.findViewById(R.id.etTransactionAmount);
            RadioGroup rbGroup = view.findViewById(R.id.rbGroup);
            RadioButton rbSpending = view.findViewById(R.id.rbSpending);
            RadioButton rbSaving = view.findViewById(R.id.rbSaving);
            EditText etDate = view.findViewById(R.id.etDate);
            EditText etDescription = view.findViewById(R.id.etDescription);

            if (transaction != null) {
                title.setText("Update Transaction");
                etTransactionAmount.setText(transaction.getTransactionAmount().toString());
                etDate.setText("" + (transaction.getDate().getMonth() + 1) + "/" +
                        transaction.getDate().getDay() + "/" + (transaction.getDate().getYear() % 100));
                etDescription.setText(transaction.getDescription());
                if (transaction.getIsSpending()) {
                    rbSpending.setChecked(true);
                } else {
                    rbSaving.setChecked(true);
                }
            }

            view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Transaction Dialog - Cancel pressed");

                    etDate.setText("");
                    etDescription.setText("");
                    etTransactionAmount.setText("00.00");
                    rbSpending.setChecked(true);

                    newTransactionDialog.dismiss();
                }
            });

            view.findViewById(R.id.btnCreateTransaction).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Transaction Dialog - Create pressed");

                    String transactionAmountText = etTransactionAmount.getText().toString();
                    if (transactionAmountText.isEmpty()) {
                        Toast.makeText(IndividualAccountActivity.this, "Transaction amount can't be empty!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double transactionAmount;
                    try {
                        transactionAmount = Double.parseDouble(transactionAmountText);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(IndividualAccountActivity.this, "Transaction amount is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error in parsing transaction amount", e);
                        return;
                    }

                    String dateString = etDate.getText().toString();
                    if (dateString.isEmpty()) {
                        Toast.makeText(IndividualAccountActivity.this, "Date can't be empty!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String[] dateSplit = dateString.split("/");
                    Date date;
                    try {
                        int year = Integer.parseInt(dateSplit[2]);
                        int month = Integer.parseInt(dateSplit[0]);
                        int day = Integer.parseInt(dateSplit[1]);
                        date = new Date(year, month, day);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(IndividualAccountActivity.this, "Date is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error in parsing date", e);
                        return;
                    }
                    catch (IndexOutOfBoundsException e) {
                        Toast.makeText(IndividualAccountActivity.this, "Date is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error in parsing date", e);
                        return;
                    }

                    String description = etDescription.getText().toString();
                    if (description.isEmpty()) {
                        Toast.makeText(IndividualAccountActivity.this, "Description can't be empty!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (position == -1) {
                        saveTransaction(transactionAmount,
                                rbGroup.getCheckedRadioButtonId() == R.id.rbSpending, date, description);

                        etDate.setText("");
                        etDescription.setText("");
                        etTransactionAmount.setText("00.00");
                        rbSpending.setChecked(true);

                        newTransactionDialog.dismiss();
                        queryTransactions(account);
                    }
                    else {
                        Transaction transaction = allTransactions.get(position);
                        transaction.setTransactionAmount(transactionAmount);
                        transaction.setIsSpending(rbGroup.getCheckedRadioButtonId() == R.id.rbSpending);
                        transaction.setDate(date);
                        transaction.setDescription(description);
                        transaction.setUser(ParseUser.getCurrentUser());
                        transaction.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e(TAG, "Error while saving", e);
                                    Toast.makeText(IndividualAccountActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Log.i(TAG, "Transaction save was successful!");
                            }
                        });
                        adapter.notifyDataSetChanged();

                        etDate.setText("");
                        etDescription.setText("");
                        etTransactionAmount.setText("00.00");
                        rbSpending.setChecked(true);

                        newTransactionDialog.dismiss();

                    }
                }
            });
        }

        newTransactionDialog.show();
    }
}