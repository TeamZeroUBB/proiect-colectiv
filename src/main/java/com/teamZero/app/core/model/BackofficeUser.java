package com.teamZero.app.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BackofficeUser extends User {
    @Column(unique = true)
    private String username;

    public BackofficeUser() {
        userStatus = UserStatus.AWAITING_APPROVAL;
        userType = UserType.ROLE_ADMIN;
    }

    public BackofficeUser(String username, String password) {
        super(password);
        this.username = username;
        userStatus = UserStatus.AWAITING_APPROVAL;
        userType = UserType.ROLE_ADMIN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
