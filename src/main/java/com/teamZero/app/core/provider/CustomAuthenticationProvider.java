package com.teamZero.app.core.provider;

import com.teamZero.app.core.facade.LoginFacade;
import com.teamZero.app.core.model.UserType;
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

//import LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final LoginFacade loginFacade;

    @Autowired
    public CustomAuthenticationProvider(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (username == null || password == null)
            throw new BadCredentialsException("Invalid username/password");

        UserType userType = loginFacade.login(username, password);

        if (userType == UserType.ROLE_UNKNOWN)
            throw new BadCredentialsException("Invalid username/password");

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userType.toString()));
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);

        return auth;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

