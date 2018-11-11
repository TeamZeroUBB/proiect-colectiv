package com.teamZero.app.web.dto.converter;

import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.web.dto.BackofficeUserDto;
import com.teamZero.app.web.dto.ClientUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class BackofficeUserConverter {


    public static BackofficeUserDto convertToDto(BackofficeUser backofficeUser){

        //TODO add or remove data if necessary

        BackofficeUserDto dto = new BackofficeUserDto();
        dto.setUsername(backofficeUser.getUsername());

        return dto;
    }

    public static BackofficeUser convertFromDto(BackofficeUserDto backofficeUserDto){

        //TODO add or remove data if necessary

        BackofficeUser backofficeUser = new BackofficeUser();
        backofficeUser.setUsername(backofficeUserDto.getUsername());

        return backofficeUser;
    }


    public static List<BackofficeUserDto> convertListToDto(List<BackofficeUser> clientUsers){
        return clientUsers.stream()
                .map(BackofficeUserConverter::convertToDto)
                .collect(Collectors.toList());
    }

}
