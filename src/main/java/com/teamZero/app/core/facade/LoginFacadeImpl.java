package com.teamZero.app.core.facade;

import com.teamZero.app.core.service.LoginService;
import com.teamZero.app.core.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeImpl implements LoginFacade {

    private final LoginService loginService;

    @Autowired
    public LoginFacadeImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserType login(String username, String password) {
       return loginService.login(username, password);
    }
}
