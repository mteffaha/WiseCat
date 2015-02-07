package org.polytech.unice.websem.wisecat.model;

import java.util.Date;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class Message {
    private String message;
    private Date postDate;

    public Message(String message, Date postDate) {
        this.message = message;
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
