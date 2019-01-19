package com.teamZero.app.service;

import com.teamZero.app.dao.CompanyDao;
import com.teamZero.app.dao.JobOfferDao;
import com.teamZero.app.dao.AppUserDao;
import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.user.AppUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private JobOfferDao jobOfferDao;

    @Resource
    private AppUserDao appUserDao;


    private void addJobOffersToCompany(Company company){
        company.setPostedJobOffers(jobOfferDao.getJobOffersPostedByCompany(company.getCompanyId()));
    }


    @Async
    public CompletableFuture<Company> getCompanyByCompanyId(Long companyId){

        Company company = companyDao.getOneByCompanyId(companyId);
        addJobOffersToCompany(company);
        return CompletableFuture.completedFuture(company);
    }

    @Async
    public CompletableFuture<Company> getCompanyByUserId(Long userId){

        Company company = companyDao.getOneByUserId(userId);
        addJobOffersToCompany(company);
        return CompletableFuture.completedFuture(company);
    }

    @Async
    public CompletableFuture<List<Company>> getAllCompanies(){
        return CompletableFuture.completedFuture(companyDao.getAll());
    }

    @Async
    @Transactional
    public void add(Company company, String userEmail){

        companyDao.add(company);

        AppUser appUser = appUserDao.getOneByEmail(userEmail);
        appUser.setCompanyId(company.getCompanyId());

        appUserDao.update(appUser);

    }

    @Async
    public void delete(Long companyId){
        companyDao.delete(companyId);
        jobOfferDao.deleteByCompanyId(companyId);
    }

    @Async
    public CompletableFuture<Boolean> isUserCompanyCreator(Long userId, Long companyId){

        Company company = companyDao.getOneByCompanyId(companyId);

        return CompletableFuture.completedFuture(company.getUserId().equals(userId));
    }

    @Async
    public void update(Company company){
        companyDao.update(company);
    }
}

