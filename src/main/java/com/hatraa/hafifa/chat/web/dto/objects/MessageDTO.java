package com.hatraa.hafifa.chat.web.dto.objects;

import java.util.Date;

/**
 * Created by Ebens on 6/5/17.
 */
public class MessageDTO {
    private int id;
    private Date sentTime;
    private UserDTO sender;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }
}
