package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.core.model.UserStatus;
import com.teamZero.app.core.model.UserType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserType login(String username, String password) {
        Session session = sessionFactory.getCurrentSession();

        String hqlTest = "FROM ClientUser";
        Query test = session.createQuery( hqlTest );

        List<ClientUser> l= test.list();

        String hqlForNormalClient = "FROM ClientUser AS cu WHERE cu.email = :email AND cu.password = :clientPassword " +
                "AND cu.userStatus = :userStatus";
        String hqlForAdmin = "FROM BackofficeUser AS bu WHERE bu.username = :username AND bu.password = :adminPassword " +
                "AND bu.userStatus = :userStatus";

        Query queryClient = session.createQuery(hqlForNormalClient);
        Query queryAdmin = session.createQuery(hqlForAdmin);
        queryClient.setParameter("email", username);
        queryClient.setParameter("clientPassword", password);
        queryClient.setParameter("userStatus", UserStatus.ACTIVE);

        queryAdmin.setParameter("username", username);
        queryAdmin.setParameter("adminPassword", password);
        queryAdmin.setParameter("userStatus", UserStatus.APPROVED);


        if (queryClient.list().size() > 0)
            return UserType.ROLE_USER;

        if (queryAdmin.list().size() > 0)
            return UserType.ROLE_ADMIN;

        return UserType.ROLE_UNKNOWN;
    }

}
