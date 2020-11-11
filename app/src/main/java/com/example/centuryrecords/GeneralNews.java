package com.example.centuryrecords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.centuryrecords.Adapters.NewsItemsAdapter;
import com.example.centuryrecords.Models.NewsModelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

 public class GeneralNews extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ArrayList<NewsModelClass> news_models;
    private NewsItemsAdapter newsItemsAdapter;
    private static final String URL = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_general_news);

        //CUSTOM - TOOLBAR: -
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_general);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout_general_news);

        recyclerView = findViewById (R.id.recycler_view_general_news);
        recyclerView.setHasFixedSize(true);
        news_models = new ArrayList<>();
        proceed();

        swipeRefreshLayout.setOnRefreshListener (new SwipeRefreshLayout.OnRefreshListener () {
            @Override
            public void onRefresh() {
                proceed();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void proceed(){

        getGeneralNews();
        recyclerView.setLayoutManager (new LinearLayoutManager (getApplicationContext ()));
        newsItemsAdapter = new NewsItemsAdapter (getApplicationContext (),news_models);
        recyclerView.setAdapter (newsItemsAdapter);

    }

     private void getGeneralNews() {

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (Request.Method.GET, URL, null, new Response.Listener<JSONObject> () {
             @Override
             public void onResponse(JSONObject response) {

                 try {

                     JSONArray jsonArray = response.getJSONArray("articles");

                     for(int i = 0 ; i < jsonArray.length () ; i++){

                         JSONObject jsonObject = jsonArray.getJSONObject(i);

                         NewsModelClass newsModelClass = new NewsModelClass ();
                         newsModelClass.setTitle(jsonObject.getString ("title"));
                         newsModelClass.setAuthor(jsonObject.getString ("author"));
                         newsModelClass.setUrl(jsonObject.getString ("url"));
                         newsModelClass.setImageUrl (jsonObject.getString ("urlToImage"));

                         news_models.add(newsModelClass);
                     }

                 } catch (JSONException e) {
                     e.printStackTrace ();
                 }
             }
         }, new Response.ErrorListener () {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Log.d("tag", "onErrorResponse: " + error.getMessage());
             }
         });

         MySingleton.getInstance(getApplicationContext ()).addToRequestQueue(jsonObjectRequest);

     }

     @Override
     public void onBackPressed() {
         Intent intent =  new Intent(getApplicationContext(), NewsCategories.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(intent);
     }

 }
