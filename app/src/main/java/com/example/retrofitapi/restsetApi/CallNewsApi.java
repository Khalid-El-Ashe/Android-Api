package com.example.retrofitapi.restsetApi;

import com.example.retrofitapi.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallNewsApi {

    // this is the type of method api
    @GET("top-headlines")

    // this is to call the api and get the data
    Call<NewsApiResponse> callHeadLines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
