package com.teamZero.app.controller;

import com.teamZero.app.domain.Company;
import com.teamZero.app.domain.job.JobOffer;
import com.teamZero.app.domain.user.AppUser;
import com.teamZero.app.domain.user.UserRole;
import com.teamZero.app.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController("/user-service")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody AppUser user){

        try{

            userService.updateUser(user);
            return ResponseEntity.status(204).body(null);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{userId}/{deletedUserId}")
    public ResponseEntity deleteUser(@PathVariable Long userId, @PathVariable Long deletedUserId){

        try{

            userService.deleteUser(userId);
            return ResponseEntity.status(204).body(null);

        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/register-company/{userId}")
    public ResponseEntity registerCompany(@PathVariable Long userId, @RequestBody Company company){

        try{
            userService.registerCompany(userId, company);
            return ResponseEntity.status(204).body("Company registered successfully");
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }

    @PutMapping("/add-cv/{userId}")
    public ResponseEntity addCv(@PathVariable Long userId, @RequestBody byte[] cv){

        try{
            userService.addCvToUser(userId, cv);
            return ResponseEntity.status(204).body("Cv added successfully");
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body(e.getMessage());

        }
    }

    @GetMapping("/download-cv/{userId}")
    public void downloadCv(HttpServletResponse response, @PathVariable Long userId){

        try {
            AppUser appUser = userService.getUserById(userId).get();

            InputStream is = new ByteArrayInputStream(appUser.getCv());

            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
    }

    @GetMapping("/test")
    public String test(){
        return "YAY";
    }


}
