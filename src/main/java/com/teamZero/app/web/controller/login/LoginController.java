package com.teamZero.app.web.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, path = "/loginForm")
    public String loginForm(Model model) {
        return "misc/login";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/logout")
    public String logoutButton(Model model) {
        return "misc/logout";
    }
}
