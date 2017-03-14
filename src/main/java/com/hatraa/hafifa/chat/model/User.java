package com.hatraa.hafifa.chat.model;

import org.hibernate.mapping.UniqueKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import org.hibernate.validator.constraints.*;

@javax.persistence.SequenceGenerator(
        name="USER_SEQ",
        sequenceName = "USER_SEQ",
        allocationSize = 1
)

@Entity
@Table(name="USERS")
public class User implements Serializable{
    private int id;
    private String name;
    private String email;

    public User() {
        id = 0;
    }

    public User(int id, String username, String email) {
        this.id = id;
        this.name = username;
        this.email = email;
    }

    //region Getters/Setters
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="EMAIL", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User)obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id= " + id + ", username = " + name + ", email= " + email + "]";
    }
}
