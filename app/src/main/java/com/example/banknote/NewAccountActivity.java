package com.example.banknote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.banknote.Models.Account;
import com.example.banknote.Models.Bank;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewAccountActivity extends AppCompatActivity {

    private EditText etAccountName;
    private EditText etBankName;
    private EditText etBalance;
    private EditText etAccountPassword;
    private EditText etAccountNumber;
    private Button btnCreateAccount;
    public static final String TAG = "NewAccountActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        etAccountName = findViewById(R.id.tvAccountName);
        etAccountPassword = findViewById(R.id.etAccountPassword);
        etAccountNumber = findViewById(R.id.etAccountNumber);
//        etBankName = findViewById(R.id.etBankName);
        etBalance = findViewById(R.id.etBalance);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

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

                String bankName = etBankName.getText().toString();
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
                Bank bank = new Bank(); // for now
                saveAccount(accountName, bank, accountPassword, accountNumber, balance, currentUser);
            }
        });

    }

    private void saveAccount(String accountName, ParseObject bank, String accountPassword, Long accountNumber, Long balance, ParseUser currentUser) {
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