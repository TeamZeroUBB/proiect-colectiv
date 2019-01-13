package com.teamZero.app.domain;

import com.teamZero.app.domain.job.JobOffer;

import java.util.List;

public class Company {

    private Long companyId;
    private String name;
    private String description;

    private String email;
    private String phoneNumber;

    private List<JobOffer> postedJobOffers;

    //The id of the ClientUser account which can edit the employer data
    private Long userId;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private List<JobOffer> jobOffers;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public List<JobOffer> getPostedJobOffers() {
        return postedJobOffers;
    }

    public void setPostedJobOffers(List<JobOffer> postedJobOffers) {
        this.postedJobOffers = postedJobOffers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
