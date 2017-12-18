package com.example.cris.tvseriesapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.cris.tvseriesapp.R;

/**
 * Created by Cris on 12/8/2017.
 */

@Database(entities = {TVSerie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TVSeriesDao TVSeriesDao();

    private static AppDatabase INSTANCE;
    public static AppDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tvseries - database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    private void populateInitialData(){
        if (TVSeriesDao().count() == 0){
            TVSerie tvSerie1 = new TVSerie();
            tvSerie1.setTitle("The Crown");
            tvSerie1.setDescription("Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the 20th century.");
            tvSerie1.setImage(R.drawable.the_crown);
            beginTransaction();
            try {
                TVSeriesDao().insert(tvSerie1);
                setTransactionSuccessful();
            }finally {
                endTransaction();
            }
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
