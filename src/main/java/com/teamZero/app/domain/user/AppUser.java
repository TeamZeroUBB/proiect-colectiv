package com.teamZero.app.domain.user;

import com.teamZero.app.domain.job.JobOffer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AppUser implements Serializable {

    private Long id;
    private String username;
    private String password;

    private String email;
    private String firstName;
    private String lastName;
    private String city;

    private String phoneNumber;

    //Job offers added to favorites
    private List<JobOffer> savedOffers;
    //Job offers posted by this user;
    private List<JobOffer> postedJobOffers;

    //The id of the company associated with this user;
    private Long companyId;

    private byte[] cv;

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }

    public List<JobOffer> getPostedJobOffers() {
        return postedJobOffers;
    }

    public void setPostedJobOffers(List<JobOffer> postedJobOffers) {
        this.postedJobOffers = postedJobOffers;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<JobOffer> getSavedOffers() {
        return savedOffers;
    }

    public void setSavedOffers(List<JobOffer> savedOffers) {
        this.savedOffers = savedOffers;
    }

    public AppUser() { }

    public AppUser(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", savedOffers=" + savedOffers +
                ", postedJobOffers=" + postedJobOffers +
                ", companyId=" + companyId +
                '}';
    }
}
