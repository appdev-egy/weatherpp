package com.example.android.sunshine.ui.main;

import android.util.Log;

import java.util.ArrayList;

public class City {
    private String mName;
    private int mtemp;

    public City(String name, int temp) {
        mName = name;
        mtemp = temp;
    }

    public String getName() {
        return mName;
    }

    public int getTemp() {
        return mtemp;
    }

    private static int lastCityId = 0;

    public static ArrayList<City> createCityList(int numcities) {
        ArrayList<City> cities = new ArrayList<City>();
        for (int i = 1; i <= numcities; i++) {
            cities.add(new City("City name" + ++lastCityId, i ));
            Log.d("cities", "City name" + ++lastCityId +" --  " + i +10);
        }

        return cities;
    }
}
