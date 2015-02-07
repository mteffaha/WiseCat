package org.polytech.unice.websem.wisecat.model;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class User {
    private String id;
    private String name;


    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
