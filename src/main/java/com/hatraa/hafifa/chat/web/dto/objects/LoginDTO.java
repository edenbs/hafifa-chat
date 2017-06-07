package com.hatraa.hafifa.chat.web.dto.objects;

import java.io.Serializable;

/**
 * Created by Ebens on 4/25/17.
 */
public class LoginDTO{
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
