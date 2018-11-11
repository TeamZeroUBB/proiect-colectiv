package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.web.dto.BackofficeUserDto;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface BackofficeAccountFacade {
    void register(BackofficeUserDto userDto) throws ValidationException;

    BackofficeUser getBackofficeUser(Long userId);
    List<BackofficeUser> getAllBackofficeUsers();
    void deleteBackofficeUser(Long backOfficeUserId);
    void updateBackofficeUser(BackofficeUser backofficeUser);

}
