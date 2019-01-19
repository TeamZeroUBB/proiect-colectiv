package com.teamZero.app.config.auth;

import com.teamZero.app.dao.AppUserDao;
import com.teamZero.app.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private AppUserDao appUserDao;

    private UserRoleDao userRoleDao;

    @Autowired
    public CustomAuthenticationProvider(AppUserDao appUserDao, UserRoleDao userRoleDao) {
        this.appUserDao = appUserDao;
        this.userRoleDao = userRoleDao;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

      return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

