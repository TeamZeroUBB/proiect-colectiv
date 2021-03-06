package com.teamZero.app.controller;


import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.dto.JobOfferList;
import com.teamZero.app.service.CompanyService;
import com.teamZero.app.service.JobOfferService;
import com.teamZero.app.service.LoginService;
import com.teamZero.app.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GuestController {

    private static final Logger LOGGER = LogManager.getLogger(GuestController.class);

    @Resource
    private UserService userService;

    @Resource
    private JobOfferService jobOfferService;

    @Resource
    private CompanyService companyService;

    @Resource
    private LoginService loginService;

    @GetMapping("/job-offers")
    public ResponseEntity<JobOfferList> getJobOffers(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "15") int limit){

        try{
            return ResponseEntity.status(200).body(jobOfferService.getJobOffers(start, limit).get());

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/job-offers/filter")
    public ResponseEntity<JobOfferList> filterOffers(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "15") int limit,
            @RequestParam(value = "startDate", defaultValue = "NaN") String startDate,
            @RequestParam(value = "endDate", defaultValue = "NaN") String endDate,
            @RequestParam(value = "jobType", defaultValue = "NaN") String jobType,
            @RequestParam(value = "startingSalary", defaultValue = "NaN") String startingSalary,
            @RequestParam(value = "title", defaultValue = "NaN") String title
    ){

        try{

            return ResponseEntity.status(200).body(
                    jobOfferService.filterJobOffers(start, limit, startDate, endDate, jobType, startingSalary, title).get()
            );

        }catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }


    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId){

        try{
            return ResponseEntity.status(200).body(userService.getUserById(userId).get());
        }

        catch (EmptyResultDataAccessException e1){
            return ResponseEntity.status(404).body("No user with the id: " + userId);
        }

        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity getCompanyByCompanyId(@PathVariable Long companyId){

        try{
            return ResponseEntity.status(200).body(companyService.getCompanyByCompanyId(companyId).get());
        }

        catch (EmptyResultDataAccessException e1){
            return ResponseEntity.status(404).body("No company with the id: " + companyId);
        }

        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/company-of-user/{userId}")
    public ResponseEntity getCompanyByUserId(@PathVariable Long userId){

        try{
            return ResponseEntity.status(200).body(companyService.getCompanyByUserId(userId).get());
        }

        catch (EmptyResultDataAccessException e1){
            return ResponseEntity.status(404).body("No company with the user id: " + userId);
        }

        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/job-types")
    public ResponseEntity getAllJobTypes(){

        return ResponseEntity.status(200).body(jobOfferService.getAllJobTypes());
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AppUser user) {

        String username = user.getUsername();
        String password = user.getPassword();

        try{
            AppUser appUser = loginService.authenticate(username, password);
            return ResponseEntity.status(200).body(appUser);

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Login failed");
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
