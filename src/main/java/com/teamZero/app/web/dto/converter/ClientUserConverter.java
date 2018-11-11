package com.teamZero.app.web.dto.converter;

import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.web.dto.ClientUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClientUserConverter {

    public static ClientUserDto convertToDto(ClientUser clientUser){

        //TODO add or remove data if necessary

        ClientUserDto clientUserDto = new ClientUserDto();

        clientUserDto.setEmail(clientUser.getEmail());
        clientUserDto.setFirstName(clientUser.getFirstName());
        clientUserDto.setLastName(clientUser.getLastName());
        clientUserDto.setTown(clientUser.getTown());
        clientUserDto.setAddress(clientUser.getAddress());
        clientUserDto.setZipCode(clientUser.getZipCode());
        clientUserDto.setPhoneNumber(clientUser.getPhoneNumber());

        return clientUserDto;

    }

    public static ClientUser convertFromDto(ClientUserDto clientUserDto){

        //TODO add or remove data if necessary

        ClientUser clientUser = new ClientUser();

        clientUser.setEmail(clientUserDto.getEmail());
        clientUser.setFirstName(clientUserDto.getFirstName());
        clientUser.setLastName(clientUserDto.getLastName());
        clientUser.setTown(clientUserDto.getTown());
        clientUser.setAddress(clientUserDto.getAddress());
        clientUser.setZipCode(clientUserDto.getZipCode());
        clientUser.setPhoneNumber(clientUserDto.getPhoneNumber());

        return clientUser;

    }

    public static List<ClientUserDto> convertListToDto(List<ClientUser> clientUsers){
        return clientUsers.stream()
                .map(ClientUserConverter::convertToDto)
                .collect(Collectors.toList());
    }


}


