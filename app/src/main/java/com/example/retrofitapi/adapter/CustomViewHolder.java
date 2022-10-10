package com.example.retrofitapi.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitapi.R;
import com.example.retrofitapi.models.NewsHeadlines;

public class CustomViewHolder extends RecyclerView.ViewHolder {
     CardView main_container;
     TextView text_title, text_source;
     ImageView img_headline;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        main_container = itemView.findViewById(R.id.main_container);
        text_title = itemView.findViewById(R.id.text_title);
        text_source = itemView.findViewById(R.id.text_source);
        img_headline = itemView.findViewById(R.id.img_headLine);
    }
}
