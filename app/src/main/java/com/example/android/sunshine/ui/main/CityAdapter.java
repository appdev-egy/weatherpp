package com.example.android.sunshine.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.R;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    // Store a member variable for the cities
    private List<City> mcities, mcities_copy;
    private final Context mContext;
    City city;
    public CityAdapter(@NonNull Context context, List<City> cities) {
        mcities = cities;
        mcities_copy = cities;
        mContext = context;
    }


    @NonNull
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        int layoutId = R.layout.list_item_city;
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        view.setFocusable(true);
        return new CityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int i) {
        city = mcities.get(i);


        TextView cityname = cityViewHolder.city_name_textView;
        cityname.setText(city.getName());

        TextView citytemp = cityViewHolder.city_temp_textView;
        citytemp.setText(String.valueOf(city.getTemp()));

    }

    @Override
    public int getItemCount() {
        return mcities.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        final TextView city_name_textView;
        final TextView city_temp_textView;

        CityViewHolder(View view) {
            super(view);

            city_name_textView = view.findViewById(R.id.city_name);
            city_temp_textView = view.findViewById(R.id.city_temperature);



        }


    }



}