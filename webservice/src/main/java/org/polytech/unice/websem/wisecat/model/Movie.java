package org.polytech.unice.websem.wisecat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class Movie {
    private String movieID;
    private String title;
    private List<RankableString> genres = new ArrayList<RankableString>();
    private int duration;
    private String poster;
    private Date releaseDate;
    private String plot;
    private String language;
    private String imdbID;
    private List<String> subject = new ArrayList<String>();

    private List<RankableString> actors = new ArrayList<RankableString>();

    private List<RankableString> directors = new ArrayList<RankableString>();
    private List<RankableString> writers = new ArrayList<RankableString>();
    private List<RankableString> editors = new ArrayList<RankableString>();
    private List<String> songs = new ArrayList<String>();
    private List<RankableString> composers = new ArrayList<RankableString>();



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





    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<RankableString> getActors() {
        return actors;
    }

    public void setActors(List<RankableString> actors) {
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

    public List<RankableString> getDirectors() {
        return directors;
    }

    public void setDirectors(List<RankableString> directors) {
        this.directors = directors;
    }

    public List<RankableString> getWriters() {
        return writers;
    }

    public void setWriters(List<RankableString> writers) {
        this.writers = writers;
    }

    public List<RankableString> getEditors() {
        return editors;
    }

    public void setEditors(List<RankableString> editors) {
        this.editors = editors;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<RankableString> getComposers() {
        return composers;
    }

    public void setComposers(List<RankableString> composers) {
        this.composers = composers;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public void setGenres(List<RankableString> genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
