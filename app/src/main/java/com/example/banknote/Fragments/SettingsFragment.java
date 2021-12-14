package com.example.banknote.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.banknote.Activities.LoginActivity;
import com.example.banknote.MainActivity;
import com.example.banknote.Models.AccountUser;
import com.example.banknote.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private Preference prefLogOut;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        prefLogOut = getPreferenceManager().findPreference("log_out");
        prefLogOut.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AccountUser.logOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                return true;
            }
        });

    }


}