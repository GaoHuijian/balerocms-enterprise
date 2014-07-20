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
import com.balero.models.Users;
import com.balero.services.Administrator;
import com.balero.services.ListFilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PageController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public String showPage(@CookieValue(value = "baleroAdmin", defaultValue = "init") String baleroAdmin, @PathVariable int id, Model model) {

        String background = "eternity.png";
        model.addAttribute("background", background);

        Administrator admin = new Administrator();
        if(!baleroAdmin.equals("init")) {
            // Set credentials
            String[] credentials = baleroAdmin.split(":");

            // Extract cookie credentials
            admin.setLocalUsername(credentials[0]);
            admin.setLocalPassword(credentials[1]);

            List<Users> users = UsersDAO.administrator();

            for(Users obj: users) {
                admin.setRemoteUsername(obj.getUsername());
                admin.setRemotePassword(obj.getPassword());
            }

            admin.allowAccess();
        } else {
            admin.denyAccess();
        }

        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String files = listFilesUtil.listFiles();

        // Footer content
        List<Footer> footer = FooterDAO.findAll();

        // Pages
        List<Pages> page = PagesDAO.findPage(id);
        List<Pages> pages = PagesDAO.findAll();

        /**
         * Variables
         */
        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("admin", admin.getAccess());
        model.addAttribute("files", files);
        model.addAttribute("page", page);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "page";

    }

    @RequestMapping(value = "/page/edit", method = RequestMethod.POST)
    public String editPage(@RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String content) {
                           //@RequestParam String slug,
                           //@RequestParam String lang) {

        if(name.isEmpty()) {
            name = "(No Title)";
        }

        int intId = Integer.parseInt(id);
        PagesDAO.updatePage(intId, name, content, "en");

        return "redirect:/page/" + id;

    }


    @RequestMapping(value = "/page/delete", method = RequestMethod.GET)
    public String deletePage(@RequestParam String id) {

        int intId = Integer.parseInt(id);
        PagesDAO.deletePage(intId);

        return "redirect:/";

    }

}
