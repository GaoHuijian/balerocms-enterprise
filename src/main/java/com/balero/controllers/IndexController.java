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

import com.balero.models.*;
import com.balero.services.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private com.balero.models.CommentsDAO CommentsDAO;

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private String username = null;
    private String password = null;

    /**
     * Front-end Main Controller
     *
     * @param baleroAdmin Cookie
     * @param model Model Layout
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@CookieValue(value = "baleroAdmin",
            defaultValue = "init") String baleroAdmin,
                       Model model,
                       HttpServletRequest request,
                       Locale locale) {


        logger.debug("Test debug");

        String view = "index";

        String background = "eternity.png";
		model.addAttribute("background", background);

        /**
         * Get Post Rows
         */
        List<Content> rows;

        /**
         * Credentials
         */
        UsersAuth auth = new UsersAuth();
        auth.setCredentials(baleroAdmin, UsersDAO);
        if(auth.auth(baleroAdmin, auth.getLocalUsername(), auth.getLocalPassword())) {
            switch (auth.getLocalUsername()) {
                case "admin":
                    // Get Post Rows By All
                    rows = ContentDAO.findAllAdmin();
                    break;

                default:
                    // Get Post Rows By Author
                    rows = ContentDAO.findAllAuthor(auth.getLocalUsername());
            }
            // Admin Elements
            model.addAttribute("auth", true);
        } else {
            // Get Post Rows
            rows = ContentDAO.findAllUser(locale);
            model.addAttribute("auth", false);
        }

        logger.debug("Hierarchy Test: " + auth.getHierarchy());
        model.addAttribute("hierarchy", auth.getHierarchy());

        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String files = listFilesUtil.listFiles();

        // DAO

        List<Footer> footer = FooterDAO.findAll();
        List<Pages> pages  = PagesDAO.findAll(locale);
        List<Comments> comments = CommentsDAO.findAll();

        // Installer
        if(rows.isEmpty()
                && pages.isEmpty()
                && footer.isEmpty()
                && comments.isEmpty()) {
            model.addAttribute("sucess", false);
            view = "setup";
        }


        /**
         * System variables
         */
        String pathCover =  "../webapps/media/default.jpg";
        File defaultCover = new File(pathCover);

        if(defaultCover.exists()) {
            model.addAttribute("defaultCover", "media/default.jpg");
        } else {
            model.addAttribute("defaultCover", "resources/images/eternity.png");
        }

        /**
         * Mobile Routine
         */
        model.addAttribute("mobile", false);
        String useragent = request.getHeader("user-agent");
        String accept = "Accept: */*";
        UAgentInfo agent = new UAgentInfo(useragent, accept);
        if(agent.detectMobileQuick() == true) {
            model.addAttribute("mobile", true);
        }

        model.addAttribute("username", auth.getLocalUsername());
        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("files", files);
        model.addAttribute("rows", rows);
        model.addAttribute("comments", comments);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);


		return view;
	}

}
