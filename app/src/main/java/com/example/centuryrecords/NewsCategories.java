package com.example.centuryrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.centuryrecords.Adapters.NewsCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsCategories extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> category_names;
    private List<Integer> images;
    private NewsCategoriesAdapter newsCategoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_news_categories);

        //CUSTOM - TOOLBAR: -
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_categories);
        setSupportActionBar(toolbar);

        VerifyPermissions_INTERNET_ACCESS();

        recyclerView = (RecyclerView)findViewById (R.id.recycler_view_news_categories);
        recyclerView.setHasFixedSize(true);

        category_names = new ArrayList<>();
        images = new ArrayList<> ();

        category_names.add("General");
        images.add(R.drawable.general);

        category_names.add("Business");
        images.add(R.drawable.business);

        category_names.add("Sports");
        images.add(R.drawable.sports);

        category_names.add("Health");
        images.add(R.drawable.health);

        category_names.add("Science");
        images.add(R.drawable.science);

        category_names.add("Technology");
        images.add(R.drawable.technology);

        category_names.add("Entertainment");
        images.add(R.drawable.entertainment);

        newsCategoriesAdapter = new NewsCategoriesAdapter (category_names,images,getApplicationContext());
        newsCategoriesAdapter.notifyDataSetChanged();

        GridLayoutManager gridLayoutManager = new GridLayoutManager (getApplicationContext (),2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(newsCategoriesAdapter);
    }

    //Verifying Permissions:

    private Boolean VerifyPermissions_INTERNET_ACCESS() {

        // This will return the current Status
        int permissionInternetAccess = ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (permissionInternetAccess != PackageManager.PERMISSION_GRANTED) {

            String[] INTERNET_PERMISSIONS = {Manifest.permission.INTERNET};
            // If permission not granted then ask for permission real time.
            ActivityCompat.requestPermissions(this, INTERNET_PERMISSIONS, 1);
            return false;
        }

        return true;

    }

    //ON_BACK_PRESSED() - METHOD: -
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        builder.setMessage("Do You Want To Close This App?")

                .setCancelable(false)

                //CODE FOR POSITIVE(YES) BUTTON: -
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ACTION FOR "YES" BUTTON: -
                        finish();
                    }
                })

                //CODE FOR NEGATIVE(NO) BUTTON: -
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ACTION FOR "NO" BUTTON: -
                        dialog.cancel();

                    }
                });

        //CREATING A DIALOG-BOX: -
        AlertDialog alertDialog = builder.create();
        //SET TITLE MAUALLY: -
        alertDialog.setTitle("Exit");
        alertDialog.show();

    }
}
