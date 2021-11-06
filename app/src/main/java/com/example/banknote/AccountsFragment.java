package com.example.banknote;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.banknote.Models.Account;
import com.example.banknote.databinding.FragmentAccountsBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment {

    public static final String TAG = "AccountsFragment";
    private FragmentAccountsBinding binding;

    private AccountsAdapter adapter;

    private RecyclerView rvAccounts;
    private Button btnAddAccount;
    private List<Account> allAccounts;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public AccountsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountsFragment newInstance(String param1, String param2) {
        AccountsFragment fragment = new AccountsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountsBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvAccounts = view.findViewById(R.id.rvAccounts);
        allAccounts = new ArrayList<>();
        btnAddAccount = view.findViewById(R.id.btnAddAccount);
        adapter = new AccountsAdapter(getContext(), allAccounts);

        rvAccounts.setAdapter(adapter);
        rvAccounts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryAccounts();

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), NewAccountActivity.class);
                startActivity(i);
            }
        });
    }

    protected void queryAccounts() {
        ParseQuery<Account> query = ParseQuery.getQuery(Account.class);
        query.findInBackground(new FindCallback<Account>() {
            @Override
            public void done(List<Account> accounts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting accounts", e);
                    return;
                }
                // allAccounts is input for an adapter
                allAccounts.addAll(accounts);
                // adapter in onViewCreated() for AccountsFragment.java
                Log.d(TAG, allAccounts.get(0).getAccountName());
                adapter.notifyDataSetChanged();
            }
        });
    }
}