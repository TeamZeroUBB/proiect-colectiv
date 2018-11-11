package com.teamZero.app.web.controller.backoffice;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/backoffice")
public class BackofficeHomePageController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String showBackofficeHomePage(Model model) {
        return "backoffice/backofficeHomepage";
    }

}