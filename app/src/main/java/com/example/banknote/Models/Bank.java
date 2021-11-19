package com.example.banknote.Models;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.parceler.Parcel;

@ParseClassName("Bank")
public class Bank extends ParseObject implements Parcelable {

   /*  Bank is a Read-Only model
    *  We are the ones of implementing OAuth in the future (one day...)
    *  not the user. Consequently, they shouldn't be able to add Bank models.
    *
    *  Updating the Bank class will be the job of a hypothetical database administrator
    */

    public static final String KEY_NAME = "name";
    public static final String KEY_IMAGE = "image";

    public String getName() {
        return getString(KEY_NAME);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
}
