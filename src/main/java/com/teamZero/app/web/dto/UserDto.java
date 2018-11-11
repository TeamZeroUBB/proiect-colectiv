package com.teamZero.app.web.dto;

//todo: remove unused import
import javax.validation.constraints.Size;

public abstract class UserDto {

    //todo: remove irrelevant comments
    //@Size(min=8)
    protected String password;
    //@Size(min=8)
    protected String repeatPassword;

    public UserDto() {
    }

    public UserDto(String password, String repeatPassword) {
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
