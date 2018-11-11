package com.teamZero.app.core.dao;

import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.core.model.JobOfferSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobOfferDaoImpl implements JobOfferDao{
    @Override
    public List<JobOffer> filterJobOffers(JobOfferSearch jobOfferSearch) {
        //TODO implent
        return null;
    }

    @Override
    public List<JobOffer> getAppliedToJobOffers(Long userId) {
        return null;
    }

    @Override
    public void applyToJob(Long userId, Long jobId) {

    }

    @Override
    public void unapplyFromJob(Long userId, Long jobId) {

    }

    @Override
    public List<JobOffer> getAll() {
        return null;
    }

    @Override
    public void add(JobOffer entity) {

    }

    @Override
    public void delete(JobOffer entity) {

    }

    @Override
    public void update(JobOffer entity) {

    }

    @Override
    public JobOffer getById(Long aLong) {
        return null;
    }

    @Override
    public JobOffer deleteById(Long aLong) {
        return null;
    }
}
