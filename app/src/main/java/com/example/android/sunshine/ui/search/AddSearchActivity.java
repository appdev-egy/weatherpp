package com.example.android.sunshine.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.android.sunshine.R;

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

        searchArrayList=new ArrayList<CityItem>();
        searchArrayList.add(new CityItem("ABC"));
        searchArrayList.add(new CityItem("ACB"));
        searchArrayList.add(new CityItem("BVF"));
        searchArrayList.add(new CityItem("BRT"));
        searchArrayList.add(new CityItem("ANM"));

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
