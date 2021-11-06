package com.example.banknote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banknote.Models.Account;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private Context context;
    private List<Account> accounts;

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAccountName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAccountName = itemView.findViewById(R.id.etAccountName);
        }

        public void bind(Account account) {
            tvAccountName.setText(account.getAccountName());
        }
    }

    public AccountsAdapter(Context context, List<Account> accounts) {
        this.context = context;
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = this.accounts.get(position);
        holder.bind(account);
    }

    @Override
    public int getItemCount() {
        return this.accounts.size();
    }


}
