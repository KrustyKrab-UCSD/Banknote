package com.example.banknote.Fragments;

import android.os.Build;
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

import com.example.banknote.Adapters.NewsAdapter;
import com.example.banknote.BuildConfig;
import com.example.banknote.Models.Article;
import com.example.banknote.R;
import com.google.gson.JsonObject;
//import com.kwabenaberko.newsapilib.NewsApiClient;
//import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
//import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    public static final String TAG = "NewsFragment";
    List<Article> articles;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articles = new ArrayList<>();
        RecyclerView rvArticles = view.findViewById(R.id.rvArticles);
        NewsAdapter adapter = new NewsAdapter(view.getContext(), articles);
        rvArticles.setAdapter(adapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(view.getContext()));

//        NewsApiClient newsApiClient = new NewsApiClient(BuildConfig.NEWS_API_KEY);
//        newsApiClient.getTopHeadlines(
//                new TopHeadlinesRequest.Builder()
//                        .category("business").country("us").build(),
//                new NewsApiClient.ArticlesResponseCallback() {
//                    @Override
//                    public void onSuccess(ArticleResponse articleResponse) {
//                        List<com.kwabenaberko.newsapilib.models.Article> news = articleResponse.getArticles();
//                        articles.addAll(Article.fromNewsApiArticlesArray(news));
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        Log.e(TAG, "getTopHeadlines: OnFailure", throwable);
//                    }
//                }
//        );
    }
}