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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class NewAccountActivity extends AppCompatActivity {

    private EditText etAccountName;
    private EditText etBankName;
    private EditText etBalance;
    private Button btnCreateAccount;
    public static final String TAG = "NewAccountActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        etAccountName = findViewById(R.id.etAccountName);
        etBankName = findViewById(R.id.etBankName);
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

                String bankName = etBankName.getText().toString();
                if (bankName.isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "Bank name can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String balanceString = etBalance.getText().toString();
                double balance = 0.00;

                if (!balanceString.isEmpty()) {
                    try {
                        balance = Double.parseDouble(balanceString);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(NewAccountActivity.this, "Balance is invalid!", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error in parseing balance", e);
                        return;
                    }
                }

                ParseUser currentUser = ParseUser.getCurrentUser();
                saveAccount(accountName, bankName, balance, currentUser);
                goMainActivity();
            }
        });

    }

    private void saveAccount(String accountName, String bankName, double balance, ParseUser currentUser) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setBankName(bankName);
        account.setBalance(balance);
        account.setUser(currentUser);
        account.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(NewAccountActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Account save was successful!");
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(NewAccountActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}