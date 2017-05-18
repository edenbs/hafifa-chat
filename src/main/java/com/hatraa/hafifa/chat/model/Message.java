package com.hatraa.hafifa.chat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SequenceGenerator(name = "MESSAGE_SEQ", sequenceName = "MESSAGE_SEQ", allocationSize = 1)

@Table(name = "MESSAGES")
@Entity
public class Message implements Serializable {
    private int id;
    private Date sentTime;
    private User sender;
    private String text;
    private int chatID;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQ")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SENT_TIME", nullable = false)
    public Date getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    @Column(name = "SENDER", nullable = false)
    public User getSender() {
        return this.sender;
    }

    public void setSender(User user) {
        this.sender = user;
    }

    @Column(name = "TEXT")
    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "CHAT_ID", nullable = false)
    public int getChatID() {
        return this.chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }
}