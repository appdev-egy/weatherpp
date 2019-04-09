package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    // Weather forecast the user is looking at
    private final LiveData<List<WeatherEntry>> mForecast;

    private final SunshineRepository mRepository;

    public MainActivityViewModel(SunshineRepository repository) {
        mRepository = repository;
        mForecast = mRepository.getCurrentWeatherForecasts();;
    }

    public LiveData<List<WeatherEntry>> getForecast() {
        return mForecast;
    }
}
