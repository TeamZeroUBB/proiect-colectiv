package com.teamZero.app.service;

import com.teamZero.app.dao.AppUserDao;
import com.teamZero.app.dao.UserRoleDao;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginService{

    @Resource
    private AppUserDao appUserDao;

    @Resource
    private UserRoleDao userRoleDao;

    public AppUser authenticate(String username, String password) throws AuthenticationException {

        System.out.println("Login: " + username + " " + password);

        if (username == null || password == null)
            throw new BadCredentialsException("Invalid username/password");

        Optional<Long> appUserIdOptional = appUserDao.login(username, password);

        if (!appUserIdOptional.isPresent()){
            throw new BadCredentialsException("Invalid username/password");
        }

        List<UserRole> userRoles = userRoleDao.getRolesForUser(appUserIdOptional.get());
        AppUser appUser = appUserDao.getOneById(appUserIdOptional.get());

        if (userRoles.contains(UserRole.ROLE_ADMIN)){
            appUser.setIsAdmin(true);
        }else{
            appUser.setIsAdmin(false);
        }

        return appUser;
    }

}
