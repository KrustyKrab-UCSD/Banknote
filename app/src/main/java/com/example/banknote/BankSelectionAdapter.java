package com.example.banknote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.banknote.Models.Bank;

import java.util.List;

public class BankSelectionAdapter extends RecyclerView.Adapter<BankSelectionAdapter.ViewHolder> {

    private Context context;
    private List<Bank> banks;

    public BankSelectionAdapter(Context context, List<Bank> banks) {
        this.context = context;
        this.banks = banks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bank bank = banks.get(position);
        holder.bind(bank);
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBankName;
        private ImageView ivBankImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBankName = itemView.findViewById(R.id.etBankName);
            ivBankImage = itemView.findViewById(R.id.ivBankImage);
        }

        public void bind(Bank bank) {
            tvBankName.setText(bank.getName());
            Glide.with(context).load(bank.getImage().getUrl()).into(ivBankImage);
        }
    }

}
