package com.hatraa.hafifa.chat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@SequenceGenerator(name = "CHAT_SEQ", sequenceName = "CHAT_SEQ", allocationSize = 1)
@Table(name = "CHATS")
@Entity
public class Chat implements Serializable {
    private int id;
    private List<User> participants;
    private String name;
    private boolean isDirect;
    private List<Message> messages;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @OneToMany(targetEntity = User.class)
    @JoinTable(
            name = "USER_CHAT",
            joinColumns = { @JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID", unique = true) }
    )
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> users) {
        this.participants = users;
    }

    @Column(nullable = false)
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IS_DIRECT")
    public boolean getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(boolean isDirect) {
        this.isDirect = isDirect;
    }

    @OneToMany(targetEntity = Message.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")
    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        message.setChatID(this.id);
    }

    // ???
    public void removeMessage(Message message) {
        this.messages.remove(message);
    }
}
