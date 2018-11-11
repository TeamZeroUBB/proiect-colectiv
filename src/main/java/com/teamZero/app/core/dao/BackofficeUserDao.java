package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.BackofficeUser;

public interface BackofficeUserDao extends Dao<Long, BackofficeUser> {
    BackofficeUser getByUsername(String username);
}
