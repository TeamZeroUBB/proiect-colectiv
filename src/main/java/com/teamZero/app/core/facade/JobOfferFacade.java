package com.teamZero.app.core.facade;

import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;

import java.util.List;

public interface JobOfferFacade {

    void addJobOffer(JobOffer jobOffer);

    void deleteJobOffer(Long jobOfferId);

    void updateJobOffer(JobOffer jobOffer);

    JobOffer getJobOffer(Long jobOfferId);

    /*
        JobOfferSearch is a POJO containing which fields to be displayed
        the search keyword, etc etc
    */
    //filter job offers and search for job offers are basically the same thing, so I'll just implement a single method
    List<JobOffer> filterJobOffers(JobOfferSearch jobOfferSearch);

    List<JobOffer> getAllJobOffers();

    void applyToJob(Long userId, Long jobId);

    void unapplyFromJob(Long userId, Long jobId);

    List<JobOffer> getAppliedToJobOffers(Long userId);

}
