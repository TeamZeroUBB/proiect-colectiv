package com.teamZero.app.web.controller.backoffice;

import com.teamZero.app.core.facade.BackofficeAccountFacade;
import com.teamZero.app.core.model.BackofficeUser;
import com.teamZero.app.web.dto.BackofficeUserDto;
import com.teamZero.app.web.dto.converter.BackofficeUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(path = "/backoffice/account")
public class BackofficeAccountController {
    private final BackofficeAccountFacade backofficeAccountFacade;

    @Autowired
    public BackofficeAccountController(BackofficeAccountFacade backofficeAccountFacade) {
        this.backofficeAccountFacade = backofficeAccountFacade;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/register")
    public String showCreateAccountForm(Model model) {

        model.addAttribute("backofficeUserDto", new BackofficeUserDto());
        return "backoffice/backofficeRegister";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public String createAccount(@ModelAttribute("backofficeUserDto") BackofficeUserDto backofficeUserDto, Model model, BindingResult bindingResult) {

        try {
            if(bindingResult.hasErrors()) {
                model.addAttribute("message", "account.nameOrPassword.error");
                model.addAttribute("success", false);
            } else {
                backofficeAccountFacade.register(backofficeUserDto);
                model.addAttribute("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "server.fail.error");
            model.addAttribute("success", false);
        }

        return "backoffice/backofficeRegister";
    }


    @ResponseBody
    @GetMapping("{userId}")
    public BackofficeUserDto getBackOfficeUser(@PathVariable Long userId){
        return BackofficeUserConverter.convertToDto(
                backofficeAccountFacade.getBackofficeUser(userId)
        );
    }

    @ResponseBody
    @GetMapping("/all")
    public List<BackofficeUserDto> getAllBackOfficeUsers(){
        return BackofficeUserConverter.convertListToDto(
                backofficeAccountFacade.getAllBackofficeUsers()
        );
    }


    @ResponseBody
    @PutMapping("/update")
    public ResponseEntity<String> getAllBackOfficeUsers(@RequestBody BackofficeUserDto backofficeUserDto){
        backofficeAccountFacade.updateBackofficeUser(
                BackofficeUserConverter.convertFromDto(backofficeUserDto)
        );
        return ResponseEntity.noContent().build();
    }

    @ResponseBody
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> getAllBackOfficeUsers(@PathVariable Long userId){
        backofficeAccountFacade.deleteBackofficeUser(userId);
        return ResponseEntity.noContent().build();
    }
}
