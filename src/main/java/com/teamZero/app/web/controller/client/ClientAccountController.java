package com.teamZero.app.web.controller.client;

import com.teamZero.app.core.facade.UserFacade;
import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.web.dto.ClientUserDto;
import com.teamZero.app.web.dto.converter.ClientUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/client/account")
public class ClientAccountController {
    private final UserFacade userFacade;

    @Autowired
    public ClientAccountController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<String> registerUser(@ModelAttribute("clientUserDto")ClientUserDto clientUserDto/*, Model model*/, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body("Error binding model");
        } else {
            Boolean result = userFacade.registerClient(clientUserDto);
            if (result) {
                return ResponseEntity.ok("Success");
            } else {
                return ResponseEntity.badRequest().body("Could not register");
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/activate")
    public ResponseEntity<String> activateUser(@RequestParam(value = "token", required = false) String token, Model model) {
        Boolean result = userFacade.activateClient(token);
        if(result)
        {
            return ResponseEntity.ok("Success");
        }
        else
        {
            return ResponseEntity.badRequest().body("Bad token");
        }
    }


    @ResponseBody
    @GetMapping("{userId}")
    public ClientUserDto getClientUser(@PathVariable Long userId){
        return ClientUserConverter.convertToDto(
                userFacade.getClientUser(userId)
        );
    }

    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<String> updateClientUser(@RequestBody ClientUserDto clientUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return ResponseEntity.unprocessableEntity().body("Error binding model");
        }

        userFacade.updateClientUser(
                ClientUserConverter.convertFromDto(clientUserDto)
        );
        return ResponseEntity.ok("Success");
    }

    @ResponseBody
    @GetMapping("/all")
    public List<ClientUserDto> getAllClientUsers(){
        return ClientUserConverter.convertListToDto(
                userFacade.getAllClientUsers()
        );
    }

    @ResponseBody
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteClientUser(@PathVariable Long userId){
        userFacade.deleteClientUser(userId);
        return ResponseEntity.noContent().build();
    }

}
