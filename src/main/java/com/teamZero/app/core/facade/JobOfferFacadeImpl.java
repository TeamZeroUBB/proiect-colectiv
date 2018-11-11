package com.teamZero.app.core.facade;

import com.teamZero.app.core.dao.JobOfferDao;
import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;
import com.teamZero.app.core.service.JobOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobOfferFacadeImpl implements JobOfferFacade {

    @Autowired
    private JobOfferService jobOfferService;


    @Override
    public void addJobOffer(JobOffer jobOffer) {
        jobOfferService.addJobOffer(jobOffer);
    }

    @Override
    public void deleteJobOffer(Long jobOfferId) {
        jobOfferService.deleteJobOffer(jobOfferId);
    }

    @Override
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferService.updateJobOffer(jobOffer);
    }

    @Override
    public JobOffer getJobOffer(Long jobOfferId) {
        return jobOfferService.getJobOffer(jobOfferId);
    }

    @Override
    public List<JobOffer> filterJobOffers(JobOfferSearch jobOfferSearch) {
        return jobOfferService.filterJobOffers(jobOfferSearch);
    }

    @Override
    public List<JobOffer> getAllJobOffers(){
        return jobOfferService.getAllJobOffers();
    }

    @Override
    public List<JobOffer> getAppliedToJobOffers(Long userId){
        return jobOfferService.getAppliedToJobOffers(userId);
    }

    @Override
    public void applyToJob(Long userId, Long jobId) {
        jobOfferService.applyToJob(userId, jobId);
    }

    @Override
    public void unapplyFromJob(Long userId, Long jobId) {
        jobOfferService.unapplyFromJob(userId, jobId);
    }
}
