package com.teamZero.app.core.populator;

import com.teamZero.app.web.dto.BackofficeUserDto;
import com.teamZero.app.core.model.BackofficeUser;
import org.springframework.stereotype.Component;

@Component
public class BackofficeUserPopulator implements Populator<BackofficeUserDto, BackofficeUser> {

    @Override
    public BackofficeUser populate(BackofficeUserDto backofficeUserDto) {
        return new BackofficeUser(backofficeUserDto.getUsername(), backofficeUserDto.getPassword());
    }
}
