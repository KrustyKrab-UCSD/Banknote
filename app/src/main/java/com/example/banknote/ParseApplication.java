package com.example.banknote;

import android.app.Application;

import com.example.banknote.Models.Account;
import com.example.banknote.Models.AccountUser;
import com.example.banknote.Models.Bank;
import com.example.banknote.Models.Transaction;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        // Use for troubleshooting -- remove this line for production
//        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
//
//        // Use for monitoring Parse OkHttp traffic
//        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
//        // See https://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        builder.networkInterceptors().add(httpLoggingInterceptor);

        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax

        ParseUser.registerSubclass(AccountUser.class);
        ParseObject.registerSubclass(Account.class);
        ParseObject.registerSubclass(Bank.class);
        ParseObject.registerSubclass(Transaction.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("GJ0NAqhqnj7PMOsBhOaC4c0Sf0hJaSMLmCui9wr0")
                .clientKey("sV0WFiQyp3F4BRwXTMTK1vBm8rw7TY18ZCUIgYwA")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
