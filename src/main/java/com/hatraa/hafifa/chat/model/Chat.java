package com.hatraa.hafifa.chat.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    public Chat() {
        this.participants = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(
            name = "USER_CHAT",
            joinColumns = { @JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
            uniqueConstraints = { @UniqueConstraint(columnNames = {"USER_ID", "CHAT_ID"})}
    )
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> users) {
        this.participants = users;
    }

    @Column(name="NAME", nullable = true)
    public String getName(){
        return this.name;
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
        message.setChatID(null);
    }

    public boolean addParticipant(User user) {
        if (isDirect && this.getParticipants().size() == 2) {
            return false;
        }

        this.participants.add(user);
        user.getChats().add(this);

        return true;
    }

    //TODO: If this.participants empty - delete chat
    public boolean removeParticipant(User user) {
        if (isDirect) {
            return false;
        }

        this.participants.remove(user);
        user.removeChat(this);

        return true;
    }
}
