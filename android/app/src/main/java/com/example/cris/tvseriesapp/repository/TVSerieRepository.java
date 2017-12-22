package com.example.cris.tvseriesapp.repository;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;

import com.example.cris.tvseriesapp.R;
import com.example.cris.tvseriesapp.model.AppDatabase;
import com.example.cris.tvseriesapp.model.TVSerie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 11/6/2017.
 */

public class TVSerieRepository {
//    private List<TVSerie> tvSeriesList;

    private final AppDatabase db;

    public TVSerieRepository(Context context) {
//        this.tvSeriesList = tvSeriesList;
        TVSerie tvSerie1 = new TVSerie();
        tvSerie1.setTitle("Game of Thrones");
        tvSerie1.setDescription("Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.");
        tvSerie1.setRating(9);
        tvSerie1.setImage(R.drawable.game_of_thrones);

        TVSerie tvSerie2 = new TVSerie();
        tvSerie2.setTitle("Sherlock");
        tvSerie2.setDescription("Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.");
        tvSerie2.setRating(9);
        tvSerie2.setImage(R.drawable.sherlock);

        TVSerie tvSerie3 = new TVSerie();
        tvSerie3.setTitle("House");
        tvSerie3.setDescription("An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.");
        tvSerie3.setRating(8);
        tvSerie3.setImage(R.drawable.house);

        TVSerie tvSerie4 = new TVSerie();
        tvSerie4.setTitle("Stranger Things");
        tvSerie4.setDescription("When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.");
        tvSerie4.setRating(6);
        tvSerie4.setImage(R.drawable.stranger_things);

        TVSerie tvSerie5 = new TVSerie();
        tvSerie5.setTitle("The Walking Dead");
        tvSerie5.setDescription("Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.");
        tvSerie5.setRating(5);
        tvSerie5.setImage(R.drawable.the_walking_dead);

        TVSerie tvSerie6 = new TVSerie();
        tvSerie6.setTitle("The Crown");
        tvSerie6.setDescription("Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the 20th century.");
        tvSerie6.setRating(6);
        tvSerie6.setImage(R.drawable.the_crown);


        db = Room.databaseBuilder(context, AppDatabase.class, "tvSeries-database")
                .allowMainThreadQueries()
//                .addMigrations(AppDatabase.MIGRATION_1_2)
                .build();


//        db.TVSeriesDao().insert(tvSerie1);
//        db.TVSeriesDao().insert(tvSerie2);
//        db.TVSeriesDao().insert(tvSerie3);
//        db.TVSeriesDao().insert(tvSerie4);
//        db.TVSeriesDao().insert(tvSerie5);
//        db.TVSeriesDao().insert(tvSerie6);

    }

//    public TVSerieRepository(){
//        this.tvSeriesList = new ArrayList<>();
//
//        TVSerie tvSerie1 = new TVSerie("Game of Thrones", "Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.", R.drawable.game_of_thrones);
//
//        TVSerie tvSerie2 = new TVSerie("Sherlock", "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.", R.drawable.sherlock);
//
//        TVSerie tvSerie3 = new TVSerie("House", "An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.", R.drawable.house);
//
//        TVSerie tvSerie4 = new TVSerie("Stranger Things", "When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.", R.drawable.stranger_things);
//
//        TVSerie tvSerie5 = new TVSerie("The Walking Dead", "Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.", R.drawable.the_walking_dead);
//
//        tvSeriesList.add(tvSerie1);
//        tvSeriesList.add(tvSerie2);
//        tvSeriesList.add(tvSerie3);
//        tvSeriesList.add(tvSerie4);
//        tvSeriesList.add(tvSerie5);
//
//    }

    public TVSerie findOne(int id){
//        for (int i = 0; i < tvSeriesList.size(); i++){
//            if (tvSeriesList.get(i).getTitle().equals(title))
//                return i;
//        }
//        return -1;
        return db.TVSeriesDao().findByID(id);
    }

    public List<TVSerie> getTvSeriesList(){
        return db.TVSeriesDao().getAll();
    }

//    public void setTvSeriesList(List<TVSerie> tvSeriesList) {
//        this.tvSeriesList = tvSeriesList;
//    }

    public void insert(String title, String description, Integer rating)
    {
        TVSerie tvSerie = new TVSerie();
        tvSerie.setTitle(title);
        tvSerie.setDescription(description);
        tvSerie.setRating(rating);
        db.TVSeriesDao().insert(tvSerie);
    }

    public void updateTVSeries(TVSerie tvSerie){
//        int i = findOne(title);
//        if( i != -1) {
//            this.getTvSeriesList().get(i).setDescription(description);
//        }
        db.TVSeriesDao().update(tvSerie);
    }

    public void delete(TVSerie tvSerie){
        db.TVSeriesDao().delete(tvSerie);
    }
}

