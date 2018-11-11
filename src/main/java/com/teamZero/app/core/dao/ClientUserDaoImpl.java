package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.ClientUser;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

@Repository
public class ClientUserDaoImpl extends AbstractDao<ClientUser, Long> implements ClientUserDao{
    @Override
    public ClientUser findByToken(String token) {
        String hql = "FROM ClientUser AS cu WHERE cu.registrationToken = ?1";
        Query query = super.getSession().createQuery(hql).setParameter(1, token);
        return (ClientUser) query.uniqueResult();
    }

    @Override
    public Boolean save(ClientUser clientUser) {
        if(findByEmail(clientUser.getEmail())!=null)
        {
            return false;
        }
        else
        {
            super.add(clientUser);
            return true;
        }
    }

    @Override
    public ClientUser findByEmail(String email) {
        String hql = "FROM ClientUser AS cu WHERE cu.email = :myEmail";
        Query query = super.getSession().createQuery(hql).setParameter("myEmail", email);
        return (ClientUser) query.uniqueResult();
    }
}
