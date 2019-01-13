package com.teamZero.app.controller;


import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.dto.JobOfferList;
import com.teamZero.app.service.CompanyService;
import com.teamZero.app.service.JobOfferService;
import com.teamZero.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GuestController {

    private static final Logger LOGGER = LogManager.getLogger(GuestController.class);

    @Resource
    private UserService userService;

    @Resource
    private JobOfferService jobOfferService;

    @Resource
    private CompanyService companyService;

    @GetMapping("/job-offers")
    public ResponseEntity<JobOfferList> getJobOffers(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "15") int limit){

        try{
            return ResponseEntity.status(200).body(jobOfferService.getJobOffers(start, limit).get());

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<JobOfferList> filterOffers(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam(value = "startDate", defaultValue = "NaN") String startDate,
            @RequestParam(value = "endDate", defaultValue = "NaN") String endDate,
            @RequestParam(value = "jobType", defaultValue = "NaN") String jobType
    ){

        try{

            return ResponseEntity.status(200).body(
                    jobOfferService.filterJobOffers(start, limit, startDate, endDate, jobType).get()
            );

        }catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long userId){

        try{
            return ResponseEntity.status(200).body(userService.getUserById(userId).get());
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Company> getCompany(@PathVariable Long companyId){

        try{
            return ResponseEntity.status(200).body(companyService.getCompanyByCompanyId(companyId).get());
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }



    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AppUser appUser){

        try{
            userService.addUser(appUser);
            return ResponseEntity.status(200).body("Account created");
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }

    }


}
