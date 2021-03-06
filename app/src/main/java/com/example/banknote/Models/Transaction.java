package com.example.banknote.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Transaction")
public class Transaction extends ParseObject {

    public static final String KEY_IS_SPENDING = "isSpending";
    public static final String KEY_DATE = "date";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_USER = "user";

    public boolean getIsSpending() { return getBoolean(KEY_IS_SPENDING); }

    public void setIsSpending(boolean isSpending) { put(KEY_IS_SPENDING, isSpending); }

    public Date getDate() { return getDate(KEY_DATE); }

    public void setDate(Date date) { put(KEY_DATE, date); }

    public Double getTransactionAmount() { return getDouble(KEY_BALANCE); }

    public void setTransactionAmount(Double balance) { put(KEY_BALANCE, balance); }

    public ParseObject getAccount() { return getParseObject(KEY_ACCOUNT); }

    public void setAccount(ParseObject account) { put(KEY_ACCOUNT, account); }

    public String getDescription() { return getString(KEY_DESCRIPTION); }

    public void setDescription(String description) { put(KEY_DESCRIPTION, description); }

    public ParseObject getUser() { return getParseObject(KEY_USER); }

    public void setUser(ParseObject account) { put(KEY_USER, account); }

}
