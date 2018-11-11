package com.teamZero.app.core.service;

import com.teamZero.app.core.dao.JobOfferDao;
import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobOfferServiceImpl implements JobOfferService{

    @Autowired
    private JobOfferDao jobOfferDao;


    @Override
    public void addJobOffer(JobOffer jobOffer) {
        jobOfferDao.add(jobOffer);
    }

    @Override
    public void deleteJobOffer(Long jobOfferId) {
        jobOfferDao.deleteById(jobOfferId);
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDao.update(jobOffer);
    }

    @Override
    public JobOffer getJobOffer(Long jobOfferId) {
        return jobOfferDao.getById(jobOfferId);
    }

    @Override
    public List<JobOffer> filterJobOffers(JobOfferSearch jobOfferSearch) {
        return jobOfferDao.filterJobOffers(jobOfferSearch);
    }

    @Override
    public List<JobOffer> getAllJobOffers(){
        return jobOfferDao.getAll();
    }

    @Override
    public List<JobOffer> getAppliedToJobOffers(Long userId){
        return jobOfferDao.getAppliedToJobOffers(userId);
    }

    @Override
    public void applyToJob(Long userId, Long jobId){
        jobOfferDao.applyToJob(userId, jobId);
    }


    @Override
    public void unapplyFromJob(Long userId, Long jobId){
        jobOfferDao.unapplyFromJob(userId, jobId);
    }

}
