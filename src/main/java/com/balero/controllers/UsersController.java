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

import com.balero.models.Footer;
import com.balero.models.Pages;
import com.balero.services.UsersAuth;
import com.balero.services.ListFilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RedirectAttributes redirectAttributes,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("name") String lastname,
                                @RequestParam("email") String email) {

        UsersDAO.register(username, password, lastname, email, "user");

        ResourceBundle bundle = ResourceBundle.getBundle("messages");

        redirectAttributes.addFlashAttribute("message",
                bundle.getString("label.login.registerok"));

        return "redirect:/";

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String form(@CookieValue(value = "baleroAdmin",
                       defaultValue = "init") String baleroAdmin,
                       Model model,
                       Locale locale) {

        String background = "eternity.png";
        model.addAttribute("background", background);

        String pathCover =  "../webapps/media/default.jpg";
        File defaultCover = new File(pathCover);

        if(defaultCover.exists()) {
            model.addAttribute("defaultCover", "media/default.jpg");
        } else {
            model.addAttribute("defaultCover", "resources/images/eternity.png");
        }

        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String files = listFilesUtil.listFiles();
        List<Footer> footer = FooterDAO.findAll();
        List<Pages> pages  = PagesDAO.findAll(locale);

        UsersAuth admin = new UsersAuth();
        /**
         * Credentials
         */
        UsersAuth auth = new UsersAuth();
        List<com.balero.models.Users> users = UsersDAO.administrator();
        String username = null;
        String password = null;
        for(com.balero.models.Users obj: users) {
            username = obj.getUsername();
            password = obj.getPassword();
        }
        /**
         * Enable or Disable and
         * Check if Admin Elements will
         * be displayed
         */
        model.addAttribute("auth", auth.auth(baleroAdmin, username, password));

        model.addAttribute("title", "Register");
        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("files", files);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "register";

    }

    /**
     *
     * @param id
     * @param sitename
     * @param slogan
     * @param url
     * @return String
     */
    @RequestMapping(value = "/administrator", method = RequestMethod.POST)
    public String administrator(@RequestParam("id") int id,
            @RequestParam("sitename") String sitename,
            @RequestParam("slogan") String slogan,
            @RequestParam("url") String url) {
            //@RequestParam("lang") String lang)  {

        // Require user id
        // God Admin = 1
        SettingsDAO.save(id, sitename, slogan, url);

        return "redirect:/";

    }

    /**
     *
     * @param pwd1
     * @param pwd2
     * @return String
     */
    @RequestMapping(value = "/god", method = RequestMethod.POST)
    public String god(@RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2) {

        UsersDAO.administratorCredentials(pwd1);

        return "redirect:/";

    }

}
