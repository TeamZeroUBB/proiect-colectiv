package com.teamZero.app.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@SpringBootApplication
@RequestMapping(path = "/")
public class ErrorController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "400: Bad Request";
                break;
            }
            case 401: {
                errorMsg = "401: Unauthorized";
                break;
            }
            case 403: {
                errorMsg = "403: Forbidden";
                break;
            }
            case 404: {
                errorMsg = "404: Resource not found";
                break;
            }
            case 500: {
                errorMsg = "500: Internal Server Error";
                break;
            }
        }
        model.addAttribute("error", errorMsg);

        if (httpErrorCode < 500) {
            model.addAttribute("isServerError", false);
        }
        else {
            model.addAttribute("isServerError", true);
        }

        return "misc/errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

}
