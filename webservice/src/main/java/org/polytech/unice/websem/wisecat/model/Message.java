package org.polytech.unice.websem.wisecat.model;

import java.util.Date;

/**
 * Created by mtoffaha on 07/02/2015.
 */
public class Message {

    private String message;
    private Date date;

    public Message(String message) {
        this.message = message;
        this.date = new Date(); // We sate the message date, as the current date
        // TODO : Check time zones and make sure the time fits
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
