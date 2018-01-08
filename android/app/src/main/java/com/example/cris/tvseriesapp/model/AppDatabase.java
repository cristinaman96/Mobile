package com.example.cris.tvseriesapp.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.cris.tvseriesapp.R;

/**
 * Created by Cris on 12/8/2017.
 */

//@Database(entities = {TVSerie.class}, version = 2)
//public abstract class AppDatabase extends RoomDatabase {
//    public abstract TVSeriesDao TVSeriesDao();
//
//    public static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE 'tvseries' ADD COLUMN 'rating' INTEGER");
//
//        }
//    };
//
//}
