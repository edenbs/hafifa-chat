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
@NamedQueries({
        @NamedQuery(name = "getByEmail", query = "from User where email=:email")
})
public class User implements Serializable{
    private int id;
    private String name;
    private String password;
    private String salt;
    private String email;

    public User() {
        id = 0;
    }

    public User(String username, String password, String salt, String email) {
        this.id = 0;
        this.name = username;
        this.password = password;
        this.salt = salt;
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

    @Column(name="PASSWORD", nullable = false)
    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Column(name="SALT", nullable = false)
    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

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
