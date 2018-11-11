package com.teamZero.app.web.controller.noLoginRequired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET, path = "")
    public String showHomePage(Model model) {
        return "noLogin/homepage";
    }

}
