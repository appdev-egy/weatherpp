package com.example.android.sunshine.ui.main;

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
        // Dummy Data
            cities.add(new City("London, UK", 0 ));
            cities.add(new City("Vancouver, CA", 0 ));
            cities.add(new City("Alisal, US", 0 ));
            cities.add(new City("Cap-Haitien, HT", 0 ));
            cities.add(new City("Seattle, US", 0 ));



        return cities;
    }
}
