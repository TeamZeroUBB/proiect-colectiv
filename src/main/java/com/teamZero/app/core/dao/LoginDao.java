package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.UserType;

public interface LoginDao {
    UserType login(String username, String password);
}
