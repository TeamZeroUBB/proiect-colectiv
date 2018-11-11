package com.teamZero.app.core.service;

import com.teamZero.app.core.dao.BackofficeUserDao;
import com.teamZero.app.core.dao.ClientUserDao;
import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.core.model.ClientUser;
import com.teamZero.app.core.model.UserStatus;
import com.teamZero.app.core.util.EMailUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private BackofficeUserDao backofficeUserDao;
    private ClientUserDao clientUserDao;

    private void assignNewToken(String email) {
        ClientUser clientUser = clientUserDao.findByEmail(email);

        LocalDateTime expirationDate = LocalDateTime.now();
        expirationDate = expirationDate.plusDays(1);
        clientUser.setRegistrationTokenExpiration(expirationDate);
        String token = RandomStringUtils.randomAlphanumeric(10);
        clientUser.setRegistrationToken(token);
        clientUserDao.update(clientUser);

        try {
            String link = "http://localhost:8080/LayerApp/client/account/activate?token="+token;
            String message = "Hello!\nA new account was just registered using this e-mail adress." +
                    " Please click the link below to activate your account:\n"+
                    link +
                    "\n The link is only valid for 24 hours.\n If you did not create the account, feel free to ignore" +
                    " this e-mail.\n Have a nice day!";
            EMailUtil.sendMail(clientUser.getEmail(),"App registration token",message);
        }
        catch (MessagingException m) {

        }
    }

    @Autowired
    public UserServiceImpl(BackofficeUserDao backofficeUserDao, ClientUserDao clientUserDao)
    {
        this.backofficeUserDao=backofficeUserDao;
        this.clientUserDao=clientUserDao;
    }

    /**
     * Adds the given backoffice user in DB.
     * @throws ValidationException if in the DB already exists a backoffice user with the same username.
     */
    @Override
    public void saveBackofficeUser(BackofficeUser backofficeUser) throws ValidationException {
        if (backofficeUserDao.getByUsername(backofficeUser.getUsername()) != null) {
            throw new ValidationException("The backoffice user with the username \"" + backofficeUser.getUsername() + "\" already exists.");
        }
        backofficeUserDao.add(backofficeUser);
    }

    @Override
    public Boolean saveClientUser(ClientUser clientUser) {
        Boolean result = clientUserDao.save(clientUser);
        assignNewToken(clientUser.getEmail()); // a new token is always assigned, regardless of the user's existence
        return result;
    }

    @Override
    public Boolean activateClientUser(String token) {
        ClientUser clientUser = clientUserDao.findByToken(token);

        if(clientUser == null)
            return false;

        Boolean isTokenValid =  clientUser.getRegistrationTokenExpiration().isAfter(LocalDateTime.now());
        if(isTokenValid) {
            clientUser.setUserStatus(UserStatus.ACTIVE);
            clientUserDao.update(clientUser);
        }
        else {
            assignNewToken(clientUser.getEmail());
        }

        return isTokenValid;
    }

    @Override
    public ClientUser findClientUserByEmail(String email) {
        return clientUserDao.findByEmail(email);
    }

    @Override
    public BackofficeUser getBackofficeUser(Long userId){
        return backofficeUserDao.getById(userId);
    }

    @Override
    public ClientUser getClientUser(Long clientId){
        return clientUserDao.getById(clientId);
    }


    @Override
    public List<ClientUser> getAllClientUsers() {
        return clientUserDao.getAll();
    }

    @Override
    public List<BackofficeUser> getAllBackofficeUsers() {
        return backofficeUserDao.getAll();
    }

    @Override
    public void deleteClientUser(Long clientUserId) {
        clientUserDao.deleteById(clientUserId);
    }

    @Override
    public void deleteBackofficeUser(Long backOfficeUserId) {
        backofficeUserDao.deleteById(backOfficeUserId);
    }

    @Override
    public void updateClientUser(ClientUser clientUser) {
        clientUserDao.update(clientUser);
    }

    @Override
    public void updateBackofficeUser(BackofficeUser backofficeUser) {
        backofficeUserDao.update(backofficeUser);
    }


}
