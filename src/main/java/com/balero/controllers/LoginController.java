/**
 * <pre>
 * Balero CMS Enterprise Edition is free and open source software under MIT License.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 <Balero CMS All Rights Reserved>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * <a href="http://www.balerocms.com">BaleroCMS.com</a>
 * </pre>
 *
 * @author      Anibal Gomez
 * @version     1.0
 * @since       1.0
 */

package com.balero.controllers;

import com.balero.models.Users;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Inputs
    private String username = null;
    private String password = null;

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    private static final Logger logger = Logger.getLogger(LoginController.class);

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
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        // Inputs
        String inputUsername = request.getParameter("inputUsername");
        String inputPassword = request.getParameter("inputPassword");

        // Debug
        logger.debug("param user: " + request.getParameter("inputUsername"));
        logger.debug("param pwd: " + request.getParameter("inputPassword"));
        logger.debug("cookie: " + baleroAdmin);

        // Init 'Users'
        List<Users> users;
        // Case
        switch (inputUsername) {
            // Admin
            case "admin":
                users = UsersDAO.administrator();
                break;

            // Users
                default:
                    users = UsersDAO.user();
        }

        // Catch unregistered user
        try {
            if(users.isEmpty()) {
                throw new Exception("User do not exists!.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }

        for (Users obj : users) {
            // Remote
            username = obj.getUsername();
            password = obj.getPassword();
            // Find register
            if(username.equals(inputUsername)) {
                if (password.equals(inputPassword)) {
                    // create cookie and set it in response
                    Cookie cookie = new Cookie("baleroAdmin", inputUsername + ":" + inputPassword);
                    response.addCookie(cookie);
                    logger.debug("Cookie Value: " + baleroAdmin);
                } else {
                    redirectAttributes.addFlashAttribute("message", "Login failed! Wrong password.");
                }
            }
        }

        return "redirect:/";

    }

}
