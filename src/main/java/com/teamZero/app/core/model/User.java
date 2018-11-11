package com.teamZero.app.core.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserStatus userStatus;

    @Enumerated(EnumType.STRING)
    protected UserType userType;

    public User() { }

    public User(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
