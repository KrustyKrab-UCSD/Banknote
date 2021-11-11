package com.example.banknote;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.banknote.Models.Bank;

import java.util.List;

public class BankSelectionAdapter extends RecyclerView.Adapter<BankSelectionAdapter.ViewHolder> {

    private Context context;
    private List<Bank> banks;
    private static Intent intent = new Intent();
    private int selectedPos = RecyclerView.NO_POSITION;

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
        if (selectedPos == position) {
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_recyclerview_item_dark));
            holder.tvBankName.setTextColor(Color.WHITE);
        } else {
            holder.itemView.setBackground(ContextCompat.getDrawable(context, R.drawable.background_recyclerview_item));
            holder.tvBankName.setTextColor(Color.BLACK);
        }

        holder.bind(bank);
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    public static Intent getIntent() {
        return intent;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBankName;
        private ImageView ivBankImage;
        private RelativeLayout itemBankContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBankName = itemView.findViewById(R.id.tvBankName);
            ivBankImage = itemView.findViewById(R.id.ivBankImage);
            itemBankContainer = itemView.findViewById(R.id.itemBankContainer);
        }

        public void bind(Bank bank) {
            tvBankName.setText(bank.getName());
            Glide.with(context).load(bank.getImage().getUrl()).into(ivBankImage);

            itemBankContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyItemChanged(selectedPos);
                    selectedPos = getAdapterPosition();
                    notifyItemChanged(selectedPos);
                    // Will take selected Bank object and store it in the adapter
                    intent.putExtra("bank", bank.getName());
                }
            });
        }
    }

}
