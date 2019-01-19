package com.teamZero.app.service;

import com.teamZero.app.dao.JobOfferDao;
import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.dto.JobOfferList;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class JobOfferService{

    @Resource
    private JobOfferDao jobOfferDao;

    @Async
    public CompletableFuture<JobOffer> addJobOffer(JobOffer jobOffer) {
        return CompletableFuture.completedFuture(jobOfferDao.add(jobOffer));
    }

    @Async
    public void deleteJobOffer(Long jobOfferId) {
        jobOfferDao.delete(jobOfferId);
    }

    @Async
    public void updateJobOffer(JobOffer jobOffer) {
        jobOfferDao.update(jobOffer);
    }

    @Async
    public CompletableFuture<List<JobOffer>> getAllJobOffers(){
        return CompletableFuture.completedFuture(jobOfferDao.getAll());
    }

    @Async
    public CompletableFuture<JobOfferList> getJobOffers(int start, int limit){

        int totalSize = jobOfferDao.getAll().size();

        start = start < 0 ? 0 : start;
        start = start > totalSize ? totalSize - 1 : start;

        JobOfferList list = new JobOfferList();

        int limit1 = start + limit > totalSize ? totalSize - start : limit;

        list.setList(jobOfferDao.getPagedJobOffers(start, limit1));
        list.setTotalSize(totalSize);

        return CompletableFuture.completedFuture(list);
    }

    @Async
    public CompletableFuture<Boolean> isUserJobOfferPoster(Long userId, Long jobOfferId){

        JobOffer jobOffer = jobOfferDao.getOneById(jobOfferId);
        return CompletableFuture.completedFuture(userId.equals(jobOffer.getUserId()));
    }


    @Async
    public CompletableFuture<JobOfferList> filterJobOffers(int start, int limit,
                                                           String startDate,
                                                           String endDate,
                                                           String jobType,
                                                           String startingSalary){


        List<JobOffer> jobOffers = jobOfferDao.filterJobOffers(startDate, endDate, jobType, startingSalary);

        start = start < 0 ? 0 : start;
        start = start > jobOffers.size() ? jobOffers.size() - 1 : start;


        int end = start + limit > jobOffers.size() ? jobOffers.size() : start + limit;

        JobOfferList jobOfferList = new JobOfferList();

        jobOfferList.setTotalSize(jobOffers.size());
        jobOfferList.setList(jobOffers.subList(start, end));

        return CompletableFuture.completedFuture(jobOfferList);

    }

    public List<String> getAllJobTypes(){
        return jobOfferDao.getAllJobTypes();
    }

}
