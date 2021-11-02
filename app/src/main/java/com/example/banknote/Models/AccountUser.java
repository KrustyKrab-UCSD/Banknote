package com.example.banknote.Models;

import com.parse.ParseFile;
import com.parse.ParseUser;

public class AccountUser extends ParseUser {
    public static final String KEY_PUSH_NOTIFS = "pushNotifs";

    public Boolean getPushNotifs() {
        return getBoolean(KEY_PUSH_NOTIFS);
    }

    public void setPushNotifs(boolean b) {
        put(KEY_PUSH_NOTIFS, b);
    }
}
