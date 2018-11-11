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

@Controller
@RequestMapping(path = "/client/account")
public class ClientAccountController {
    private final UserFacade userFacade;

    @Autowired
    public ClientAccountController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public String showRegisterUser(Model model) {
        model.addAttribute("ClientUserDto", new ClientUserDto());
        return "client/register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public String registerUser(@ModelAttribute("ClientUserDto")ClientUserDto clientUserDto, Model model, BindingResult bindingResult) {

        String page;

        if(bindingResult.hasErrors()) {
            model.addAttribute("message","register.usernameOrPassword.error");
            page = "client/register";
        } else {
            Boolean result = userFacade.registerClient(clientUserDto);
            if (result) {
                model.addAttribute("message", "registration.success.message");
                page = "misc/genericSuccess";
            } else {
                model.addAttribute("message", "register.emailTaken.error");
                page = "client/register";
            }
        }
        return page;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/activate")
    public String activateUser(@RequestParam(value = "token", required = false) String token, Model model) {

        String page;

        Boolean result = userFacade.activateClient(token);
        if(result)
        {
            model.addAttribute("message","register.accountActive.message");
            page = "misc/registerSuccess";
        }
        else
        {
            model.addAttribute("message","register.tokenExpired.error");
            page = "register";
        }
        return page;
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
    public ResponseEntity<String> updateClientUser(@RequestBody ClientUserDto clientUserDto){
        userFacade.updateClientUser(
                ClientUserConverter.convertFromDto(clientUserDto)
        );
        return ResponseEntity.noContent().build();
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
