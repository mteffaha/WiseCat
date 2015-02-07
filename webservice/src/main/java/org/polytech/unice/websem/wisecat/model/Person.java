package org.polytech.unice.websem.wisecat.model;

/**
 * Created by mtoffaha on 03/02/2015.
 */
public class Person {
    private String personID;
    private String name;

    public Person(String personID,String name) {
        this.name = name;
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
