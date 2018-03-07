package com.example.user.meetthecity.util;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.user.meetthecity.data.City;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * Created by User on 3/7/2018.
 */

public class FileProcessingUtils {
    Context mContext;

    public FileProcessingUtils(Context appContext) {
        this.mContext = appContext;
    }

    @SuppressLint("TimberArgCount")
    public List<City> parseCitiesCSV() {
        Timber.v("Parsing cities CSV");
        List<City> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(
                    new InputStreamReader(mContext.getAssets().open("worldcities.csv")));
            for (; ; ) {
                String[] next = reader.readNext();

                if (next != null) {
                    City city = City.builder()
                            .withCityName(next[0])
                            .withLat(Double.valueOf(next[2]))
                            .withLon(Double.valueOf(next[3]))
                            .withCountry(next[5])
                            .withIso2(next[6])
                            .build();
                    list.add(city);
                } else {
                    break;
                }
            }

            return list;
        } catch (IOException e) {
            Timber.e("Failed to parse Cities CSV file ", e);
            return Collections.emptyList();
        }
    }
}