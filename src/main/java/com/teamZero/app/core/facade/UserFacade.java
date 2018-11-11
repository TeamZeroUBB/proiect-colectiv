package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.web.dto.ClientUserDto;

import java.util.List;

public interface UserFacade {
    Boolean registerClient(ClientUserDto clientUserDto);
    Boolean activateClient(String token);
    Long getUserId(String email);

    ClientUser getClientUser(Long userId);
    List<ClientUser> getAllClientUsers();
    void deleteClientUser(Long backOfficeUserId);
    void updateClientUser(ClientUser clientUser);

}
