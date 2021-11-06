package com.example.banknote.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Account extends ParseObject {

    public static final String KEY_ACCOUNT_NAME = "accountName";
    public static final String KEY_BANK_NAME = "bankName";
    public static final String KEY_USER = "user";
    public static final String KEY_BALANCE = "balance";

    public String getAccountName() {
        return getString(KEY_ACCOUNT_NAME);
    }

    public void setAccountName(String accountName) {
        put(KEY_ACCOUNT_NAME, accountName);
    }

    public String getBankName() {
        return getString(KEY_BANK_NAME);
    }

    public void setBankName(String bankName) {
        put(KEY_BANK_NAME, bankName);
    }

    public double getBalance() {
        return getDouble(KEY_BALANCE);
    }

    public void setBalance(double balance) {
        put(KEY_BALANCE, balance);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

}
