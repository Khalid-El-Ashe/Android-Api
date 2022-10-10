package com.example.retrofitapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapi.R;
import com.example.retrofitapi.listener.SelectListener;
import com.example.retrofitapi.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;

    private SelectListener selectListener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.selectListener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_list_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        NewsHeadlines newsHeadlines = headlines.get(position);
        holder.text_title.setText(newsHeadlines.getTitle());
        holder.text_source.setText(newsHeadlines.getSource().getName());
        if (newsHeadlines.getUrlToImage() != null) {
            Picasso.get().load(newsHeadlines.getUrlToImage()).into(holder.img_headline);
        } else {
            holder.img_headline.setImageResource(R.drawable.not_found);
        }

        holder.main_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.OnNewsClicked(newsHeadlines);
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
