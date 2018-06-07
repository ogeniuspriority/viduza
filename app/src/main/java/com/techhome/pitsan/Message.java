package com.techhome.pitsan;

import java.util.Date;

public class Message {
    private String name, message;
    private Date date;


    public Message(String name, String message, Date date) {
        this.name = name;
        this.message = message;
        this.date = date;
    }


    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
