package org.polytech.unice.websem.wisecat.model;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class Person {
    private String name;
    private String photoURL;
    private String imdbPage;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getImdbPage() {
        return imdbPage;
    }

    public void setImdbPage(String imdbPage) {
        this.imdbPage = imdbPage;
    }
}
