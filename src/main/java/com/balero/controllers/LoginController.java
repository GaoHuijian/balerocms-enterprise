package com.balero.controllers;

import com.balero.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Login controller
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private Boolean admin = false;
    private String username = null;
    private String password = null;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    /**
     * LoginController
     *
     * @param baleroAdmin Cookie
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(
            @CookieValue(value = "baleroAdmin", defaultValue = "init") String baleroAdmin,
            HttpServletResponse response,
            Model model,
            HttpServletRequest request) {

        String view = "redirect:/";
        String inputUsername = request.getParameter("inputUsername");
        String inputPassword = request.getParameter("inputPassword");

        System.out.println("param user: " + request.getParameter("inputUsername"));
        System.out.println("param pwd: " + request.getParameter("inputPassword"));
        System.out.println("cookie: " + baleroAdmin);

       List<Users> users = UsersDAO.administrator();

       for(Users obj: users) {
           username = obj.getUsername();
           password = obj.getPassword();
       }

        if((username.equals(inputUsername) && (password.equals(inputPassword)))) {
            // create cookie and set it in response
            Cookie cookie = new Cookie("baleroAdmin", inputUsername + ":" + inputPassword);
            response.addCookie(cookie);
            admin = true;
            view = "redirect:/";
        } else {
            admin = false;
            model.addAttribute("admin", admin);
            model.addAttribute("message", "Login failed! Wrong credentials.");
            view = "index";
        }

        return view;

    }

}
