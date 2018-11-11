package com.teamZero.app.core.service;

import com.teamZero.app.core.model.UserType;

public interface LoginService {
    UserType login(String username, String password);
}
