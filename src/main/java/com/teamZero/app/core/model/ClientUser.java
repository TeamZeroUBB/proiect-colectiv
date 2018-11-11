package com.teamZero.app.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ClientUser extends User {
    @Column(nullable = false, unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String town;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String registrationToken;
    private LocalDateTime registrationTokenExpiration;


    public ClientUser() {
        userStatus = UserStatus.AWAITING_ACTIVATION;
        userType = UserType.ROLE_USER;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public LocalDateTime getRegistrationTokenExpiration() {
        return registrationTokenExpiration;
    }

    public void setRegistrationTokenExpiration(LocalDateTime registrationTokenExpiration) {
        this.registrationTokenExpiration = registrationTokenExpiration;
    }
}
