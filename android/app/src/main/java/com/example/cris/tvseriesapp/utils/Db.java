package com.example.cris.tvseriesapp.utils;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Cris on 1/8/2018.
 */

public class Db {
    private static FirebaseDatabase database;

    public static FirebaseDatabase getDatabase(){
        if(database == null){
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }
}
