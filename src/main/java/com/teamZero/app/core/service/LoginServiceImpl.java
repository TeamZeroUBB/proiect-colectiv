package com.teamZero.app.core.service;

import com.teamZero.app.core.dao.LoginDao;
import com.teamZero.app.core.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;

    @Autowired
    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public UserType login(String username, String password) {
        return loginDao.login(username, password);
    }
}
