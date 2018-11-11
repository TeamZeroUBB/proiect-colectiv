package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.UserType;

public interface LoginFacade {
    UserType login(String username, String password);
}
