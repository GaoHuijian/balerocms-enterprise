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

        response.addCookie(new Cookie("baleroAdmin", "init"));

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("cookies: " + cookie.getName() + " : " + cookie.getValue());
            }
        }

        String message = "Hola prueba";
        ModelAndView model = new ModelAndView("WelcomePage");
        model.addObject("message", message);
        return model;
    }

}

