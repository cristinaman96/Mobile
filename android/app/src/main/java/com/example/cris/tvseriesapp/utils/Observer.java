package com.example.cris.tvseriesapp.utils;

import com.example.cris.tvseriesapp.repository.TVSerieRepository;

/**
 * Created by Cris on 1/8/2018.
 */

public abstract class Observer {
    protected TVSerieRepository tvSerieRepository;

    public abstract void update();
}
