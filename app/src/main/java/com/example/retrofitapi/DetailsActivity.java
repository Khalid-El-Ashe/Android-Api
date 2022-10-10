package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofitapi.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private NewsHeadlines headlines;

    private TextView text_title, text_author, text_time, text_details, text_content;
    private ImageView img_detail_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        text_title = findViewById(R.id.text_detail_title);
        text_author = findViewById(R.id.text_detail_author);
        text_time = findViewById(R.id.text_detail_time);
        text_details = findViewById(R.id.text_detail_details);
        text_content = findViewById(R.id.text_detail_content);
        img_detail_news = findViewById(R.id.img_detail_news);

        text_title.setText(headlines.getTitle());
        text_author.setText(headlines.getAuthor());
        text_time.setText(headlines.getPublishedAt());
        text_details.setText(headlines.getDescription());
        text_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_detail_news);
    }
}