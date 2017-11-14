package com.example.cris.tvseriesapp.repository;

import com.example.cris.tvseriesapp.R;
import com.example.cris.tvseriesapp.model.TVSerie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 11/6/2017.
 */

public class TVSerieRepository {
    private List<TVSerie> tvSeriesList;

    public TVSerieRepository(List<TVSerie> tvSeriesList) {
        this.tvSeriesList = tvSeriesList;
    }

    public TVSerieRepository(){
        this.tvSeriesList = new ArrayList<>();

        TVSerie tvSerie1 = new TVSerie("Game of Thrones", "Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.", R.drawable.game_of_thrones);

        TVSerie tvSerie2 = new TVSerie("Sherlock", "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.", R.drawable.sherlock);

        TVSerie tvSerie3 = new TVSerie("House", "An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.", R.drawable.house);

        TVSerie tvSerie4 = new TVSerie("Stranger Things", "When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.", R.drawable.stranger_things);

        TVSerie tvSerie5 = new TVSerie("The Walking Dead", "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.", R.drawable.the_walking_dead);

        tvSeriesList.add(tvSerie1);
        tvSeriesList.add(tvSerie2);
        tvSeriesList.add(tvSerie3);
        tvSeriesList.add(tvSerie4);
        tvSeriesList.add(tvSerie5);

    }

    public int findOne(String title){
        for (int i = 0; i < tvSeriesList.size(); i++){
            if (tvSeriesList.get(i).getTitle().equals(title))
                return i;
        }
        return -1;
    }

    public List<TVSerie> getTvSeriesList(){
        return tvSeriesList;
    }

    public void setTvSeriesList(List<TVSerie> tvSeriesList) {
        this.tvSeriesList = tvSeriesList;
    }

    public void updateTVSeries(String title, String description){
        int i = findOne(title);
        if( i != -1) {
            this.getTvSeriesList().get(i).setDescription(description);
        }
    }
}

