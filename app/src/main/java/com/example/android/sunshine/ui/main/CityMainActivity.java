package com.example.android.sunshine.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.android.sunshine.R;
import com.example.android.sunshine.ui.list.MainActivity;
import com.example.android.sunshine.ui.search.AddSearchActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class CityMainActivity extends Activity {
    Button btnTest;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<City> cities;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_city_main);

        btnTest = (Button)findViewById(R.id.buttontest);


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CityMainActivity.this, AddSearchActivity.class);
                startActivity(intent);

//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_cities);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);



        cities = City.createCityList(20);
        Log.d("cities", Arrays.toString(cities.toArray()));
        // Create adapter passing in the sample user data
        CityAdapter adapter = new CityAdapter(this,cities);
        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }


}
