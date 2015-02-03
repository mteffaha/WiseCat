package org.polytech.unice.websem.wisecat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class Movie {
    private String title;
    private List<String> genres = new ArrayList<String>();
    private int duration;
    private String poster;
    private String plot;
    private List<Person> actors = new ArrayList<Person>();

    public Movie() {
    }

    public Movie( String title,int duration,String plot,String poster) {
        this.duration = duration;
        this.title = title;
        this.plot = plot;
        this.poster = poster;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Person> getActors() {
        return actors;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }
}
