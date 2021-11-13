package com.example.banknote.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banknote.Activities.IndividualAccountActivity;
import com.example.banknote.Models.Account;
import com.example.banknote.databinding.ItemAccountBinding;

import org.parceler.Parcels;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private Context context;
    private List<Account> accounts;

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemAccountBinding binding;

        public ViewHolder(@NonNull ItemAccountBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Account account) {
            binding.tvAccountName.setText(account.getAccountName());
            binding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, account.getAccountName() + " clicked!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, IndividualAccountActivity.class);
                    i.putExtra("account", account);
                    context.startActivity(i);
                }
            });
        }
    }

    public AccountsAdapter(Context context, List<Account> accounts) {
        this.context = context;
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAccountBinding binding = ItemAccountBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
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
