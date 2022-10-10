package com.example.retrofitapi.listener;

import com.example.retrofitapi.models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchDataListener(List<NewsHeadlines> list, String message);

    void onError(String message);
}
