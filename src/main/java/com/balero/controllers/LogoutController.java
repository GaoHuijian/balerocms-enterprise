package com.balero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * Logout controller
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * Simple, set cookie's content to 'init'
     *
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String logout(HttpServletResponse response) {

        // create cookie and set it in response
        Cookie cookie = new Cookie("baleroAdmin", "init");
        response.addCookie(cookie);

        return "redirect:/";

    }

}
