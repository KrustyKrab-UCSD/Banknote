package com.example.banknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.banknote.Activities.LoginActivity;
import com.example.banknote.Fragments.AccountsFragment;
import com.example.banknote.Fragments.NewsFragment;
import com.example.banknote.Fragments.SettingsFragment;
import com.example.banknote.Fragments.SpendingAnalysisFragment;
import com.example.banknote.Models.AccountUser;
import com.example.banknote.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // So app doesn't start on a blank screen
        Fragment fragment = new AccountsFragment();
        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();

        bottomNavigationView = binding.bottomNavigation;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new AccountsFragment();
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        fragment = new SettingsFragment();
                        break;
                    case R.id.action_analysis:
                        fragment = new SpendingAnalysisFragment();
                        break;
                    case R.id.action_news:
                        fragment = new NewsFragment();
                        break;
                    default:
                        fragment = new AccountsFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
    }
}