package com.example.cris.tvseriesapp.model;

/**
 * Created by Cris on 11/6/2017.
 */

public class TVSerie {
    private String title;
    private String description;
    private Integer image;

    public TVSerie(String title, String description, Integer image) {
        this.title = title;
        this.description = description;
        this.image = image;
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
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
