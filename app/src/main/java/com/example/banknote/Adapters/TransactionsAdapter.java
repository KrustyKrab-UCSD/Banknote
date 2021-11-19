package com.example.banknote.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banknote.Models.Transaction;
import com.example.banknote.R;

import java.util.Date;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    private Context context;
    private List<Transaction> transactions;
    private OnClickListener onClickListener;


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        //private TextView tvBalance;
        private TextView tvDate;
        private TextView tvTransactionAmount;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvDescription = itemView.findViewById(R.id.tvDescription);
            //tvBalance = itemView.findViewById(R.id.tvBalance);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTransactionAmount = itemView.findViewById(R.id.tvTransactionAmount);
        }

        public void bind(Transaction transaction) {
            //tvBalance.setText("$  " + transaction.getTransactionAmount());
            Date date = transaction.getDate();
            tvDate.setText("" + (date.getMonth() + 1) + "/" + date.getDay() + "/" + (date.getYear() % 100));
            tvDescription.setText(transaction.getDescription());
            tvTransactionAmount.setText("$" + transaction.getTransactionAmount());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClicked(getAdapterPosition());
                    //return true;
                }
            });

        }
    }

    public TransactionsAdapter(Context context, OnClickListener onCLickListener, List<Transaction> transactions) {
        this.onClickListener = onCLickListener;
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.bind(transaction);
    }

    @Override
    public int getItemCount() {
        return this.transactions.size();
    }
}
