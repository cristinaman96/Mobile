package com.example.cris.tvseriesapp.repository;

import com.example.cris.tvseriesapp.MainActivity;
import com.example.cris.tvseriesapp.R;
//import com.example.cris.tvseriesapp.model.AppDatabase;
import com.example.cris.tvseriesapp.model.TVSerie;
import com.example.cris.tvseriesapp.utils.Observer;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cris on 11/6/2017.
 */

public class TVSerieRepository extends Observer {
    private List<TVSerie> tvSeriesList = new ArrayList<TVSerie>();
    private List<Observer> observerList = new ArrayList<Observer>();
    private int id = 1;

//    private final AppDatabase db;
    private DatabaseReference db;

    public TVSerieRepository(DatabaseReference reference) {
//        this.tvSeriesList = tvSeriesList;
        TVSerie tvSerie1 = new TVSerie();
        tvSerie1.setTvsid(id);
        tvSerie1.setTitle("Game of Thrones");
        tvSerie1.setDescription("Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.");
        tvSerie1.setRating(9);
        tvSerie1.setImage(R.drawable.game_of_thrones);

        id++;
        TVSerie tvSerie2 = new TVSerie();
        tvSerie2.setTvsid(id);
        tvSerie2.setTitle("Sherlock");
        tvSerie2.setDescription("Nine noble families fight for control over the mythical lands of Westeros, while a forgotten race returns after being dormant for thousands of years.");
        tvSerie2.setRating(9);
        tvSerie2.setImage(R.drawable.sherlock);

        id++;
        TVSerie tvSerie3 = new TVSerie();
        tvSerie3.setTvsid(id);
        tvSerie3.setTitle("House");
        tvSerie3.setDescription("An antisocial maverick doctor who specializes in diagnostic medicine does whatever it takes to solve puzzling cases that come his way using his crack team of doctors and his wits.");
        tvSerie3.setRating(8);
        tvSerie3.setImage(R.drawable.house);

        id++;
        TVSerie tvSerie4 = new TVSerie();
        tvSerie4.setTvsid(id);
        tvSerie4.setTitle("Stranger Things");
        tvSerie4.setDescription("When a young boy disappears, his mother, a police chief, and his friends must confront terrifying forces in order to get him back.");
        tvSerie4.setRating(6);
        tvSerie4.setImage(R.drawable.stranger_things);

        id++;
        TVSerie tvSerie5 = new TVSerie();
        tvSerie5.setTvsid(id);
        tvSerie5.setTitle("The Walking Dead");
        tvSerie5.setDescription("Sheriff Deputy Rick Grimes wakes up from a coma to learn the world is in ruins, and must lead a group of survivors to stay alive.");
        tvSerie5.setRating(5);
        tvSerie5.setImage(R.drawable.the_walking_dead);

        id++;
        TVSerie tvSerie6 = new TVSerie();
        tvSerie6.setTvsid(id);
        tvSerie6.setTitle("The Crown");
        tvSerie6.setDescription("Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the 20th century.");
        tvSerie6.setRating(6);
        tvSerie6.setImage(R.drawable.the_crown);


//        db = Room.databaseBuilder(context, AppDatabase.class, "tvSeries-database")
//                .allowMainThreadQueries()
////                .addMigrations(AppDatabase.MIGRATION_1_2)
//                .build();

        this.db = reference;
//        db.child(String.valueOf(tvSerie1.getTvsid())).setValue(tvSerie1);
//        db.child(String.valueOf(tvSerie2.getTvsid())).setValue(tvSerie2);
//        db.child(String.valueOf(tvSerie3.getTvsid())).setValue(tvSerie3);
//        db.child(String.valueOf(tvSerie4.getTvsid())).setValue(tvSerie4);
//        db.child(String.valueOf(tvSerie5.getTvsid())).setValue(tvSerie5);
//        db.child(String.valueOf(tvSerie6.getTvsid())).setValue(tvSerie6);


        this.attach(this);
//        tvSeriesList.add(tvSerie1);
//        tvSeriesList.add(tvSerie2);
//        tvSeriesList.add(tvSerie3);
//        tvSeriesList.add(tvSerie4);
//        tvSeriesList.add(tvSerie5);

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


//
//    }

    public TVSerie findOne(int id){
//        for (int i = 0; i < tvSeriesList.size(); i++){
//            if (tvSeriesList.get(i).getTitle().equals(title))
//                return i;
//        }
//        return -1;
//        return db.TVSeriesDao().findByID(id);
        return this.tvSeriesList.get(id);
    }

    public void addTVSerie(TVSerie tvSerie){
        this.tvSeriesList.add(tvSerie);
    }

    public List<TVSerie> getTvSeriesList(){
        return this.tvSeriesList;
    }

    public void setId(int id){
        this.id = id;
    }

//    public void setTvSeriesList(List<TVSerie> tvSeriesList) {
//        this.tvSeriesList = tvSeriesList;
//    }

    public void insert(String title, String description, Integer rating)
    {
        id++;
        TVSerie tvSerie = new TVSerie();
        tvSerie.setTitle(title);
        tvSerie.setDescription(description);
        tvSerie.setRating(rating);
        tvSerie.setTvsid(id);
        db.child(String.valueOf(tvSerie.getTvsid())).setValue(tvSerie);
        notifyAllObservers();
    }

    public void updateTVSeries(TVSerie tvSerie){
//        int i = findOne(title);
//        if( i != -1) {
//            this.getTvSeriesList().get(i).setDescription(description);
//        }
//        db.TVSeriesDao().update(tvSerie);
        db.child(String.valueOf(tvSerie.getTvsid())).setValue(tvSerie);
        notifyAllObservers();
    }

    public void delete(TVSerie tvSerie) {
//        db.TVSeriesDao().delete(tvSerie);
        db.child(String.valueOf(tvSerie.getTvsid())).removeValue();
        notifyAllObservers();
    }

    public void clear(){
        this.tvSeriesList.clear();
    }

    public void attach(Observer observer){
        observerList.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer obs: observerList){
            obs.update();
        }
    }

    @Override
    public void update() {
        MainActivity.showRepositoryUpdate();
    }
}

