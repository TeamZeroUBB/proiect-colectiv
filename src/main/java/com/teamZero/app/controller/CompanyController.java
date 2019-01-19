package com.teamZero.app.controller;

import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import com.teamZero.app.service.CompanyService;
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
@RequestMapping("/company-service")
public class CompanyController {

    private static final Logger LOGGER = LogManager.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody Company company){

        try{

            companyService.update(company);
            return ResponseEntity.status(204).body(null);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{userId}/{deletedCompanyId}")
    public ResponseEntity deleteCompany(@PathVariable Long userId, @PathVariable Long deletedCompanyId){

        try{

            if (! isUserCompanyCreator(userId, deletedCompanyId)){
                return ResponseEntity.status(403).body("You do not have the appropriate credential to complete this action");
            }

            companyService.delete(deletedCompanyId);
            return ResponseEntity.status(204).body(null);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }





    private boolean isUserCompanyCreator(Long userId, Long companyId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {

            if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()))) {

                return companyService.isUserCompanyCreator(userId, companyId).get();
            } else {
                return true;
            }
        }
        return true;
    }

}

