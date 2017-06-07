package com.hatraa.hafifa.chat.web.dto.objects;

import com.hatraa.hafifa.chat.model.Chat;

import java.util.List;

/**
 * Created by Ebens on 6/5/17.
 */
public class UserDTO {
    private int id;
    private String name;
    private String email;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
