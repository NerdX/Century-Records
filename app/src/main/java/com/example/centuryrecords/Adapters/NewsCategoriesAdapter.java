package com.example.centuryrecords.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centuryrecords.BusinessNews;
import com.example.centuryrecords.EntertainmentNews;
import com.example.centuryrecords.GeneralNews;
import com.example.centuryrecords.HealthNews;
import com.example.centuryrecords.R;
import com.example.centuryrecords.ScienceNews;
import com.example.centuryrecords.SportsNews;
import com.example.centuryrecords.TechnologyNews;

import java.util.List;

public class NewsCategoriesAdapter extends RecyclerView.Adapter {

    private List<String> category_names;
    private List<Integer> images;
    private LayoutInflater layoutInflater;
    private Context context;

    public NewsCategoriesAdapter(List<String> category_names, List<Integer> images, Context context) {
        this.category_names = category_names;
        this.images = images;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.news_category_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.imageView.setImageResource(images.get(position));
        viewHolder.category_name.setText(category_names.get(position));

        final String temp_CATEGORY_NAME = category_names.get(position);

        viewHolder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                switch(temp_CATEGORY_NAME){

                    case "General":
                        Intent intent_general_news = new Intent(context, GeneralNews.class);
                        intent_general_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_general_news);
                        break;

                    case "Business":
                        Intent intent_business_news = new Intent(context, BusinessNews.class);
                        intent_business_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_business_news);
                        break;

                    case "Sports":
                        Intent intent_sports_news = new Intent(context, SportsNews.class);
                        intent_sports_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_sports_news);
                        break;

                    case "Health":
                        Intent intent_health_news = new Intent(context, HealthNews.class);
                        intent_health_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_health_news);
                        break;

                    case "Science":
                        Intent intent_science_news = new Intent(context, ScienceNews.class);
                        intent_science_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_science_news);
                        break;

                    case "Technology":
                        Intent intent_technology_news = new Intent(context, TechnologyNews.class);
                        intent_technology_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_technology_news);
                        break;

                    case "Entertainment":
                        Intent intent_entertainment_news = new Intent(context, EntertainmentNews.class);
                        intent_entertainment_news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent_entertainment_news);
                        break;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return category_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView category_name;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            imageView = itemView.findViewById (R.id.news_category_image_view);
            category_name = itemView.findViewById (R.id.news_category_name);
        }
    }
}
