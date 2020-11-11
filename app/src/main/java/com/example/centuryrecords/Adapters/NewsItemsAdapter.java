package com.example.centuryrecords.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centuryrecords.Models.NewsModelClass;
import com.example.centuryrecords.R;
import com.example.centuryrecords.ViewNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsItemsAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<NewsModelClass> items;
    private LayoutInflater layoutInflater;

    public NewsItemsAdapter(Context context, ArrayList<NewsModelClass> items) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from (context);
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate (R.layout.news_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.title.setText(items.get (position).getTitle ());
        viewHolder.author.setText(items.get (position).getAuthor ());
        Picasso.get().load(items.get(position).getImageUrl()).into(viewHolder.imageView);

        viewHolder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewNews.class);
                intent.putExtra("News_Item_URL",items.get(position).getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,author;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            title = (TextView) itemView.findViewById(R.id.news_title);
            author = (TextView) itemView.findViewById(R.id.news_author);
            imageView = (ImageView)itemView.findViewById (R.id.image_view_news_item);
        }
    }
}
