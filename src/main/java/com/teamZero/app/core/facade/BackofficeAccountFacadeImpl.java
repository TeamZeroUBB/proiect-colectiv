package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.core.populator.BackofficeUserPopulator;
import com.teamZero.app.core.service.UserService;
import com.teamZero.app.web.dto.BackofficeUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.List;


@Component
public class BackofficeAccountFacadeImpl implements BackofficeAccountFacade {
    private final UserService userService;
    private final BackofficeUserPopulator backofficeUserPopulator;

    @Autowired
    public BackofficeAccountFacadeImpl(UserService userService, BackofficeUserPopulator backofficeUserPopulator) {
        this.userService = userService;
        this.backofficeUserPopulator = backofficeUserPopulator;
    }

    @Override
    public void register(BackofficeUserDto backofficeUserDto) throws ValidationException {
        userService.saveBackofficeUser(backofficeUserPopulator.populate(backofficeUserDto));
    }

    @Override
    public BackofficeUser getBackofficeUser(Long userId){
        return userService.getBackofficeUser(userId);
    }

    @Override
    public List<BackofficeUser> getAllBackofficeUsers() {
        return userService.getAllBackofficeUsers();
    }

    @Override
    public void deleteBackofficeUser(Long backOfficeUserId) {
        userService.deleteBackofficeUser(backOfficeUserId);
    }

    @Override
    public void updateBackofficeUser(BackofficeUser backofficeUser) {
        userService.updateBackofficeUser(backofficeUser);
    }

}
