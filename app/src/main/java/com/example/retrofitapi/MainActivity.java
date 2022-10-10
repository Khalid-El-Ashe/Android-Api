package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retrofitapi.adapter.CustomAdapter;
import com.example.retrofitapi.listener.OnFetchDataListener;
import com.example.retrofitapi.listener.SelectListener;
import com.example.retrofitapi.models.NewsApiResponse;
import com.example.retrofitapi.models.NewsHeadlines;
import com.example.retrofitapi.restsetApi.RequestApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private ProgressDialog dialog;

    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);

        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);

        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);

        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);

        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);

        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);

        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Searching..." + query);
                dialog.show();

                // i need to make object of the class have the category
                RequestApi requestApi = new RequestApi(MainActivity.this);

                requestApi.getNewsHeadlines(listener, "technology", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                dialog.setTitle("Searching..." + newText);
//                dialog.show();
//
//                // i need to make object of the class have the category
//                RequestApi requestApi = new RequestApi(MainActivity.this);
//
//                requestApi.getNewsHeadlines(listener, "technology", newText);

                return true;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles...");
        dialog.show();

        // i need to make object of the class have the category
        RequestApi requestApi = new RequestApi(this);

        requestApi.getNewsHeadlines(listener, "technology", null);

    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchDataListener(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(MainActivity.this, "no data come", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "something is wrong", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines newsHeadlines) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("data", newsHeadlines);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();

        dialog.setTitle("Fetching news articles of " + category);
        dialog.show();

        // i need to make object of the class have the category
        RequestApi requestApi = new RequestApi(this);

        requestApi.getNewsHeadlines(listener, category, null);

    }
}
