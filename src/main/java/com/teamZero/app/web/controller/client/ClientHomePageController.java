package com.teamZero.app.web.controller.client;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/user")
public class ClientHomePageController {

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String shoHomePage(Model model) {
        return "client/clientHomepage";
    }

}