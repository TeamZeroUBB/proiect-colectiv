package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;

import java.util.List;

public interface JobOfferDao extends Dao<Long, JobOffer>{
    //TODO implement dao

    List<JobOffer> filterJobOffers(JobOfferSearch jobOfferSearch);

    //Could make a separate userHasJobDao. I'll let you decide :)
    List<JobOffer> getAppliedToJobOffers(Long userId);

    void applyToJob(Long userId, Long jobId);

    void unapplyFromJob(Long userId, Long jobId);
}
