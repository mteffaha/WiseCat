package org.polytech.unice.websem.wisecat.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class User {
    private String name;

    private String id;

    private List<Message> messages = new ArrayList<Message>();


    public User() {
    }

    public User(String name, String id) {
        this.name = name;
        this.id = id;
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
