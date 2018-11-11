package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.BackofficeUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackofficeUserDaoImpl extends AbstractDao<BackofficeUser, Long> implements BackofficeUserDao {

    /**
     * @return the backofficeUser which have the given username or null if he doesn't exists
     */
    @Override
    public BackofficeUser getByUsername(String username) {
        List<BackofficeUser> backofficeUsers = getSession().createQuery("FROM BackofficeUser AS bu " +
                "WHERE bu.username = :username").setParameter("username", username).getResultList();
        if (backofficeUsers.size() == 0) {
            return null;
        }
        return backofficeUsers.get(0);
    }
}
