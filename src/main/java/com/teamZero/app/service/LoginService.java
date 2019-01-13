package com.teamZero.app.service;

import com.teamZero.app.dao.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginService{

    @Resource
    private AppUserDao appUserDao;

    @Autowired
    public LoginService(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }



}
