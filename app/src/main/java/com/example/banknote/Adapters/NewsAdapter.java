package com.example.banknote.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.banknote.Models.Article;
import com.example.banknote.R;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    List<Article> articles;

    public NewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View articleView = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivArticleImage;
        TextView tvTitle;
        //TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            //tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(Article article) {
            Glide.with(context).load(article.getUrlToImage()).transform(new RoundedCornersTransformation(30, 0)).into(ivArticleImage);
            tvTitle.setText(article.getTitle());
            //tvDescription.setText(article.getDescription());
        }
    }
}
