package com.example.banknote.Models;

import com.kwabenaberko.newsapilib.models.Source;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Article {

    String title;
    String description;
    String urlToImage;
    String publishedAt;


    Article(com.kwabenaberko.newsapilib.models.Article article) {
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.urlToImage = article.getUrlToImage();
        this.publishedAt = article.getPublishedAt();
    }


    public static List<Article> fromNewsApiArticlesArray(List<com.kwabenaberko.newsapilib.models.Article> newsApiArticlesArray) {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < newsApiArticlesArray.size(); ++i) {
            articles.add(new Article(newsApiArticlesArray.get(i)));
        }
        return articles;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
}
