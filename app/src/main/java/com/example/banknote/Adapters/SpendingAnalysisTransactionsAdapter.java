package com.example.banknote.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banknote.Models.Transaction;
import com.example.banknote.R;

import java.util.List;

public class SpendingAnalysisTransactionsAdapter extends RecyclerView.Adapter<SpendingAnalysisTransactionsAdapter.ViewHolder> {

    Context context;
    List<Transaction> transactions;

    public SpendingAnalysisTransactionsAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_analysis_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAnalysisAccountName;
        TextView tvAnalysisDate;
        TextView tvAnalysisTransactionAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnalysisAccountName = itemView.findViewById(R.id.tvAnalysisAccountName);
            tvAnalysisDate = itemView.findViewById(R.id.tvAnalysisDate);
            tvAnalysisTransactionAmount = itemView.findViewById(R.id.tvAnalysisTransactionAmount);
        }

        public void bind(Transaction transaction) {
            String sign = "-";
            tvAnalysisDate.setText(transaction.getDate().toString());
            if (!transaction.getIsSpending()) {
                tvAnalysisTransactionAmount.setTextColor(Color.parseColor("#118C4F"));
                sign = "";
            }
            tvAnalysisTransactionAmount.setText(sign + "$" + transaction.getTransactionAmount().toString());
            tvAnalysisAccountName.setText(transaction.getAccount().getString("accountName"));
        }
    }
}
