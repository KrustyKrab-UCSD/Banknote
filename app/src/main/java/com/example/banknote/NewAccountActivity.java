package com.example.banknote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.banknote.Models.Account;
import com.example.banknote.Models.Bank;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewAccountActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 20;

    private EditText etAccountName;
    private TextView tvBankName;
    private EditText etBalance;
    private EditText etAccountPassword;
    private EditText etAccountNumber;
    private Button btnCreateAccount;
    public static final String TAG = "NewAccountActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        etAccountName = findViewById(R.id.etAccountName);
        etAccountPassword = findViewById(R.id.etAccountPassword);
        etAccountNumber = findViewById(R.id.etAccountNumber);
        tvBankName = findViewById(R.id.tvBankName);
        etBalance = findViewById(R.id.etBalance);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        tvBankName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewAccountActivity.this, BankSelectionActivity.class);
                     startActivityForResult(intent, REQUEST_CODE);
                }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountName = etAccountName.getText().toString();
                if (accountName.isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "Account name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String accountPassword = etAccountPassword.getText().toString();
                if (accountPassword.isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "Account password can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String bankName = tvBankName.getText().toString();
                if (bankName.isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "Bank name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String accountNumberString = etAccountNumber.getText().toString();
                if (accountNumberString.isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "Account number can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Long accountNumber;
                try {
                    accountNumber = Long.parseLong(accountNumberString);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(NewAccountActivity.this, "Account number is invalid!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error in parsing account number", e);
                    return;
                }

                String balanceString = etBalance.getText().toString();
                Long balance = (long) 0;

                if (!balanceString.isEmpty()) {
                    try {
                        balance = Long.parseLong(balanceString);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(NewAccountActivity.this, "Balance is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error in parsing balance", e);
                        return;
                    }
                }

                ParseUser currentUser = ParseUser.getCurrentUser();
                Log.i(TAG, "accountName: " + accountName);
                Log.i(TAG, "accountPassword: " + accountPassword);
                Log.i(TAG, "accountNumber: " + accountNumber);
                Log.i(TAG, "balance: " + balance);
                Log.i(TAG, "bankName: " + bankName);

                saveAccount(accountName, bankName, accountPassword, accountNumber, balance, currentUser);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            tvBankName.setText(data.getExtras().getString("bank"));
        }
    }

    private void saveAccount(String accountName, String bank, String accountPassword, Long accountNumber, Long balance, ParseUser currentUser) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setBank(bank);
        account.setAccountNumber(accountNumber);
        account.setAccountPassword(accountPassword);
        account.setBalance(balance);
        account.setUser(currentUser);
        account.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(NewAccountActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "Account save was successful!");
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(NewAccountActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}