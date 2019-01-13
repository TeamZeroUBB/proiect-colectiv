package com.teamZero.app.domain.user;

public enum UserRole {

    ROLE_USER(0),
    ROLE_ADMIN(1),
    ROLE_UNKNOWN(2);

    private int code;

    public int getCode() {
        return code;
    }

    UserRole(int code){
        this.code = code;
    }
}
