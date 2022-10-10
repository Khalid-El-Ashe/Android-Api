package com.example.retrofitapi.restsetApi;

import android.content.Context;
import android.widget.Toast;

import com.example.retrofitapi.R;
import com.example.retrofitapi.listener.OnFetchDataListener;
import com.example.retrofitapi.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class RequestApi {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(OnFetchDataListener listener, String category, String query) {

        // i need to make object of class have query to get category
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);

        Call<NewsApiResponse> call = callNewsApi.callHeadLines("us", category, query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "have a problem !", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchDataListener(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("The Request is Failed!!!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestApi(Context context) {
        this.context = context;
    }
}
