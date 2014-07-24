package com.balero.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Commit: i18n & L10n Unit test class
 */
public class ExampleController extends AbstractController{

    Cookie baleroAdmin;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        Cookie[] cookies = request.getCookies();

        for(int loopIndex = 0; loopIndex < cookies.length; loopIndex++) {
            baleroAdmin = cookies[loopIndex];
            if (baleroAdmin.getName().equals("baleroAdmin")) {
                System.out.println("baleroAdmin = " + baleroAdmin.getValue());
            }
        }

        String message = "Hola prueba";
        ModelAndView model = new ModelAndView("WelcomePage");
        model.addObject("message", message);
        return model;
    }

}

