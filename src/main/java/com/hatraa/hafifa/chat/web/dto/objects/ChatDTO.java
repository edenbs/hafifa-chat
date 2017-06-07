package com.hatraa.hafifa.chat.web.dto.objects;

import com.hatraa.hafifa.chat.model.User;

import java.util.List;

/**
 * Created by Ebens on 6/5/17.
 */
public class ChatDTO {
    private int id;
    private List<UserDTO> participants;
    private String name;
    private boolean isDirect;
    private List<MessageDTO> messages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDTO> participants) {
        this.participants = participants;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDirect() {
        return this.isDirect;
    }

    public void setIsDirect(boolean isDirect) {
        this.isDirect = isDirect;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
