package com.teamZero.app.web.controller.client;


import com.teamZero.app.core.facade.JobOfferFacade;
import com.teamZero.app.core.model.JobOffer;
import com.teamZero.app.web.dto.JobOfferDto;
import com.teamZero.app.web.dto.converter.JobOfferConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/job-offer-user")
public class JobOfferClientUserController {

    @Autowired
    JobOfferFacade jobOfferFacade;

    @GetMapping("/{jobOfferId}")
    public JobOfferDto getJobOffer(@PathVariable Long jobOfferId){
        return JobOfferConverter.convertToDto(
                jobOfferFacade.getJobOffer(jobOfferId)
        );
    }


    @GetMapping("/all")
    public List<JobOfferDto> getAllJobOffers(){
        return JobOfferConverter.convertListToDto(
                jobOfferFacade.getAllJobOffers()
        );
    }

    @GetMapping("/applied-to/{userId}")
    public List<JobOfferDto> getJobsAppliedTo(@PathVariable Long userId){
        return JobOfferConverter.convertListToDto(
                jobOfferFacade.getAppliedToJobOffers(userId)
        );
    }

    @PostMapping("/apply/{userId}/{jobOfferId}")
    public ResponseEntity<String> applyForJob(@PathVariable Long userId, @PathVariable Long jobOfferId){

        jobOfferFacade.applyToJob(userId, jobOfferId);
        return ResponseEntity.ok("Successfully applied for the job");
    }


    @DeleteMapping("/unapply/{userId}/{jobOfferId}")
    public ResponseEntity<String> unapplyFromJob(@PathVariable Long userId, @PathVariable Long jobOfferId){

        jobOfferFacade.unapplyFromJob(userId, jobOfferId);
        return ResponseEntity.ok("Successfully unapplied from job");
    }



}
