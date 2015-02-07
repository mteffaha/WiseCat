package org.polytech.unice.websem.wisecat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class Movie {
    private String movieID;
    private String title;
    private List<String> genres = new ArrayList<String>();
    private int duration;
    private String poster;
    private String plot;
    private List<Person> actors = new ArrayList<Person>();

    private List<Person> directors = new ArrayList<Person>();
    private List<Person> writers = new ArrayList<Person>();
    private List<Person> editors = new ArrayList<Person>();
    private List<String> songs = new ArrayList<String>();
    private List<Person> composers = new ArrayList<Person>();


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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public List<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Person> directors) {
        this.directors = directors;
    }

    public List<Person> getWriters() {
        return writers;
    }

    public void setWriters(List<Person> writers) {
        this.writers = writers;
    }

    public List<Person> getEditors() {
        return editors;
    }

    public void setEditors(List<Person> editors) {
        this.editors = editors;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<Person> getComposers() {
        return composers;
    }

    public void setComposers(List<Person> composers) {
        this.composers = composers;
    }
}
