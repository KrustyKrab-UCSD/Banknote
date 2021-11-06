package com.example.banknote.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Account")
public class Account extends ParseObject {

    public static final String KEY_ACCOUNT_NAME = "accountName";
    public static final String KEY_BANK_NAME = "bank";
    public static final String KEY_USER = "user";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_PASSWORD = "accountPassword";
    public static final String KEY_ACCOUNT_NUMBER = "accountNumber";

    public String getAccountName() {
        return getString(KEY_ACCOUNT_NAME);
    }

    public void setAccountName(String accountName) {
        put(KEY_ACCOUNT_NAME, accountName);
    }

    public Long getAccountNumber() { return getLong(KEY_ACCOUNT_NUMBER); }

    public void setAccountNumber(Long accountNumber) { put(KEY_ACCOUNT_NUMBER, accountNumber); }

    public String getPassword() { return getString(KEY_PASSWORD); }

    public void setPassword(String password) { put(KEY_PASSWORD, password); }

    public String getBankName() {
        return getString(KEY_BANK_NAME);
    }

    public void setBankName(String bankName) {
        put(KEY_BANK_NAME, bankName);
    }

    public Long getBalance() {
        return getLong(KEY_BALANCE);
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
