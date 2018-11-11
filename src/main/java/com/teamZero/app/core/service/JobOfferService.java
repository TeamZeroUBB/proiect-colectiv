package com.teamZero.app.core.service;

import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobOfferService {

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

    List<JobOffer> getAppliedToJobOffers(Long userId);

    void applyToJob(Long userId, Long jobId);

    void unapplyFromJob(Long userId, Long jobId);
}
