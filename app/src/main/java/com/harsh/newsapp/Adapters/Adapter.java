package com.harsh.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.harsh.newsapp.Details;
import com.harsh.newsapp.NewsModels.Articles;
import com.harsh.newsapp.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;
    String country;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Articles a = articles.get(position);

        String imageUrl = a.getUrlToImage();
        String url = a.getUrl();
        String time = dateTime(a.getPublishedAt());

        Picasso.with(context).load(imageUrl).into(holder.imageView);

        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(time);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Details.class);
                intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvtitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.back_image);
            cardView = itemView.findViewById(R.id.card_View);
        }
    }

    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry("")));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public String getCountry(String s){
        switch (s) {
            case "UK": {
                Locale locale = Locale.getDefault();
                country = locale.UK.getCountry();
                return country.toLowerCase();
            }
            case "US": {
                Locale locale = Locale.getDefault();
                country = locale.US.getCountry();
                return country.toLowerCase();
            }
            case "CANADA": {
                Locale locale = Locale.getDefault();
                country = locale.CANADA.getCountry();
                return country.toLowerCase();
            }
            case "GERMANY": {
                Locale locale = Locale.getDefault();
                country = locale.GERMANY.getCountry();
                return country.toLowerCase();
            }
            case "JAPAN": {
                Locale locale = Locale.getDefault();
                country = locale.JAPAN.getCountry();
                return country.toLowerCase();
            }
            case "FRANCE": {
                Locale locale = Locale.getDefault();
                country = locale.FRANCE.getCountry();
                return country.toLowerCase();
            }
            case "CHINA": {
                Locale locale = Locale.getDefault();
                country = locale.CHINA.getCountry();
                return country.toLowerCase();
            }
            case "ITALY": {
                Locale locale = Locale.getDefault();
                country = locale.ITALY.getCountry();
                return country.toLowerCase();
            }
            case "KOREA": {
                Locale locale = Locale.getDefault();
                country = locale.KOREA.getCountry();
                return country.toLowerCase();
            }
            default: {
                Locale locale = Locale.ENGLISH;
                country = locale.getCountry();
                return country.toLowerCase();
            }
        }
    }
}