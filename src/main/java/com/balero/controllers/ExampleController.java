package com.balero.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Commit: i18n & L10n Unit test class
*/
public class ExampleController extends AbstractController{

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        String message = "Hola prueba";
        ModelAndView model = new ModelAndView("WelcomePage");
        model.addObject("message", message);
        return model;
    }

}

