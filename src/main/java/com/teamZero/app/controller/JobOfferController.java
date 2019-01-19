package com.teamZero.app.controller;

import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.domain.user.UserRole;
import com.teamZero.app.service.JobOfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@SpringBootApplication
@RequestMapping("/job-offer-service")
public class JobOfferController {

    private static final Logger LOGGER = LogManager.getLogger(JobOfferController.class);

    @Resource
    private JobOfferService jobOfferService;

    @PostMapping("/create")
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOffer jobOffer){

        try{
            return ResponseEntity.status(200).body(jobOfferService.addJobOffer(jobOffer).get());
        }
        catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateJobOffer(@RequestBody JobOffer jobOffer){

        try{

            jobOfferService.updateJobOffer(jobOffer);

            return ResponseEntity.status(204).body(null);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }



    @DeleteMapping("/delete/{userId}/{jobOfferId}")
    public ResponseEntity deleteJobOffer(@PathVariable Long userId, @PathVariable Long jobOfferId){

        try{

            if (! isUserJobOfferPoster(userId, jobOfferId)){
                return ResponseEntity.status(403).body("You do not have the appropriate credential to complete this action");
            }

            jobOfferService.deleteJobOffer(jobOfferId);
            return ResponseEntity.status(204).body(null);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    private boolean isUserJobOfferPoster(Long userId, Long jobOfferId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {

            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()))) {

                return jobOfferService.isUserJobOfferPoster(userId, jobOfferId).get();
            } else {
                return true;
            }
        }
        return true;
    }


}
