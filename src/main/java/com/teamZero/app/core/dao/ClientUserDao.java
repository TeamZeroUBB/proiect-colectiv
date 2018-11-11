package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.ClientUser;

public interface ClientUserDao extends Dao<Long, ClientUser> {
    ClientUser findByToken(String token);
    Boolean save(ClientUser clientUser);
    ClientUser findByEmail(String email);
}
