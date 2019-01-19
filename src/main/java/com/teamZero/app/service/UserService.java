package com.teamZero.app.service;

import com.teamZero.app.dao.CompanyDao;
import com.teamZero.app.dao.JobOfferDao;
import com.teamZero.app.dao.AppUserDao;
import com.teamZero.app.dao.UserRoleDao;
import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService{


    @Resource
    private AppUserDao appUserDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private JobOfferDao jobOfferDao;

    @Resource
    private CompanyDao companyDao;


    private void addJobsToUser(AppUser appUser){

        List<JobOffer> savedJobOffers = jobOfferDao.getFavoriteJobOffersForUser(appUser.getId());
        List<JobOffer> postedJobOffers = jobOfferDao.getJobOffersPostedByUser(appUser.getId());

        appUser.setFavoriteJobOffers(savedJobOffers);
        appUser.setPostedJobOffers(postedJobOffers);
    }

    @Async
    public void addCvToUser(Long userId, byte[] cv){
        appUserDao.addCvToUser(userId, cv);
    }

    @Async
    public CompletableFuture<AppUser> getUserById(Long userId){

        AppUser appUser = appUserDao.getOneById(userId);
        addJobsToUser(appUser);

        return CompletableFuture.completedFuture(appUser);
    }

    @Async
    public CompletableFuture<AppUser> getUserByEmail(String email){

        AppUser appUser = appUserDao.getOneByEmail(email);
        addJobsToUser(appUser);
        return CompletableFuture.completedFuture(appUser);
    }

    @Async
    public CompletableFuture<AppUser> getUserByUserName(String username){

        AppUser appUser = appUserDao.getOneByUsername(username);
        addJobsToUser(appUser);

        return CompletableFuture.completedFuture(appUser);
    }

    @Async
    public void updateUser(AppUser user){
        appUserDao.update(user);
    }

    @Async
    @Transactional
    public void addUser(AppUser appUser){
        AppUser appUserWithId = appUserDao.add(appUser);
        userRoleDao.addUserRole(appUserWithId.getId(), UserRole.ROLE_USER);
    }

    @Async
    @Transactional
    public void deleteUser(String username){

        AppUser appUser = appUserDao.getOneByUsername(username);
        Long userId = appUser.getId();

        appUserDao.delete(userId);
        userRoleDao.removeAllRolesFromUser(userId);
        companyDao.deleteByUserId(userId);
        jobOfferDao.deleteByUserId(userId);
    }

    @Async
    @Transactional
    public void registerCompany(Long userId, Company company){

        AppUser appUser = appUserDao.getOneById(userId);
        appUser.setCompanyId(company.getCompanyId());

        appUserDao.update(appUser);
        companyDao.add(company);

    }

}
