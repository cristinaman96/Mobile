package com.example.cris.tvseriesapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Cris on 11/6/2017.
 */
//@Entity(tableName = TVSerie.TABLE_NAME)
@IgnoreExtraProperties
public class TVSerie {
//    public static final String TABLE_NAME = "tvseries";

//    @PrimaryKey(autoGenerate = true)
    private int tvsid;

//    @ColumnInfo(name = "title")
    private String title;

//    @ColumnInfo(name = "description")
    private String description;

//    @ColumnInfo(name = "rating")
    private Integer rating;

//    @ColumnInfo(name = "image")
    private Integer image;

//    public TVSerie(String title, String description, Integer image) {
//        this.title = title;
//        this.description = description;
//        this.image = image;
//    }

    public TVSerie() {
        rating = 0;
        image = 0;
    }
    public int getTvsid() {
        return tvsid;
    }

    public void setTvsid(int tvsid) {
        this.tvsid = tvsid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "TVSerie{" +
                "id=" + tvsid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
