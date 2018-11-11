package com.teamZero.app.web.dto;

public class BackofficeUserDto extends UserDto {
    private String username;

    public BackofficeUserDto() {
    }

    public BackofficeUserDto(String username, String password, String repeatPassword) {
        super(password, repeatPassword);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
