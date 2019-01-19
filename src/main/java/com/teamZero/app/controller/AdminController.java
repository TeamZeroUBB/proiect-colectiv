package com.teamZero.app.controller;

import com.teamZero.app.domain.user.UserRole;
import com.teamZero.app.service.UserRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin-service")
public class AdminController {

    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    @Resource
    private UserRoleService userRoleService;


    @GetMapping("/role/{username}")
    public ResponseEntity getRolesForUser(@PathVariable String username){

        try{
            return ResponseEntity.status(200).body(userRoleService.getRolesForUser(username));
        }
        catch (EmptyResultDataAccessException e1){
            return ResponseEntity.status(404).body("No user with the username: " + username);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Failed get roles for user");
        }

    }

    @PostMapping("/role/add/{userId}/{role}")
    public ResponseEntity addUserRole(@PathVariable Long userId, @PathVariable Integer role){

        try{

            userRoleService.addRole(userId, UserRole.values()[role]);
            return ResponseEntity.status(200).body("Added role");

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Failed to add role");
        }
    }


    @DeleteMapping("/role/remove/{userId}/{role}")
    public ResponseEntity removeRole(@PathVariable Long userId, @PathVariable Integer role){

        try{

            userRoleService.removeRole(userId, UserRole.values()[role]);
            return ResponseEntity.status(200).body("Removed role");

        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Failed to remove role");
        }
    }

}
