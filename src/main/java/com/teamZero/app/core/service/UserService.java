package com.teamZero.app.core.service;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService {
    void saveBackofficeUser(BackofficeUser backofficeUser) throws ValidationException;
    Boolean saveClientUser (ClientUser clientUser);
    Boolean activateClientUser(String token);
    ClientUser findClientUserByEmail (String email);

    ClientUser getClientUser(Long userId);
    BackofficeUser getBackofficeUser(Long userId);

    List<ClientUser> getAllClientUsers();
    List<BackofficeUser> getAllBackofficeUsers();

    void deleteClientUser(Long clientUserId);
    void deleteBackofficeUser(Long backOfficeUserId);

    void updateClientUser(ClientUser clientUser);
    void updateBackofficeUser(BackofficeUser backofficeUser);

}
