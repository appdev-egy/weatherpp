package com.example.android.sunshine.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.android.sunshine.R;
import com.example.android.sunshine.ui.list.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

public class AddSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private SearchView mSearchView;
    private ListView mListView;
    private ArrayList<CityItem> searchArrayList;
    private CitySearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_search);
        mSearchView=(SearchView) findViewById(R.id.searchView);
        mListView=(ListView) findViewById(R.id.listview_cities);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityItem xx = (CityItem) mListView.getItemAtPosition(position);

                Log.d("selectedcity", "Selected -> " + xx.getName());
                Intent intent = new Intent(AddSearchActivity.this, MainActivity.class);
                intent.putExtra("cityname",xx.getName());
                startActivity(intent);
            }
        });

        searchArrayList=new ArrayList<CityItem>();
        InputStream is = getResources().openRawResource(R.raw.cities);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = writer.toString();

        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
            JSONArray jArray = json.getJSONArray("city");
            for(int i=0; i<jArray.length(); i++){
                JSONObject json_data = jArray.getJSONObject(i);
                String city_name =json_data.getString("name");
                searchArrayList.add(new CityItem(city_name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchAdapter=new CitySearchAdapter(AddSearchActivity.this, searchArrayList);
        mListView.setAdapter(searchAdapter);

        mListView.setTextFilterEnabled(true);
        setupSearchView();
    }

    private void setupSearchView(){
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText.toLowerCase());
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }
}
