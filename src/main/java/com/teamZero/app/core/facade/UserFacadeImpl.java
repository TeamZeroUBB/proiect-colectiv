package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.service.UserService;
import com.teamZero.app.web.dto.ClientUserDto;
import com.teamZero.app.core.model.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {
    private UserService userService;

    @Autowired
    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean registerClient(ClientUserDto clientUserDto) {
        ClientUser clientUser = new ClientUser();

        clientUser.setPassword(clientUserDto.getPassword());

        clientUser.setEmail(clientUserDto.getEmail());
        clientUser.setFirstName(clientUserDto.getFirstName());
        clientUser.setLastName(clientUserDto.getLastName());
        clientUser.setTown(clientUserDto.getTown());
        clientUser.setAddress(clientUserDto.getAddress());
        clientUser.setZipCode(clientUserDto.getZipCode());
        clientUser.setPhoneNumber(clientUserDto.getPhoneNumber());

        return userService.saveClientUser(clientUser);
    }

    @Override
    public Boolean activateClient(String token) {
       return userService.activateClientUser(token);
    }

    @Override
    public Long getUserId(String email) {

        return userService.findClientUserByEmail(email).getId();

    }

    @Override
    public ClientUser getClientUser(Long userId){
        return userService.getClientUser(userId);
    }

    @Override
    public List<ClientUser> getAllClientUsers() {
        return userService.getAllClientUsers();
    }

    @Override
    public void deleteClientUser(Long userId) {
        userService.deleteClientUser(userId);
    }

    @Override
    public void updateClientUser(ClientUser clientUser) {
        userService.updateClientUser(clientUser);
    }


}
