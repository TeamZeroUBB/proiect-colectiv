package com.teamZero.app.service;

import com.teamZero.app.dao.AppUserDao;
import com.teamZero.app.dao.UserRoleDao;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import com.teamZero.app.dto.UserRoleDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private AppUserDao appUserDao;

    @Async
    public void addRole(Long userId, UserRole role){
        userRoleDao.addUserRole(userId, role);
    }

    @Async
    public void removeRole(Long userId, UserRole role){
        userRoleDao.removeUserRole(userId, role);
    }

    @Async
    public CompletableFuture<List<String>> getRolesForUser(String username){

        AppUser appUser = appUserDao.getOneByUsername(username);

        List<UserRole> userRoles = userRoleDao.getRolesForUser(appUser.getId());

        return CompletableFuture.completedFuture(

                userRoles.stream()
                    .map(Enum::name)
                    .map(x -> x.substring("ROLE_".length()))
                    .collect(Collectors.toList())
        );
    }

}
