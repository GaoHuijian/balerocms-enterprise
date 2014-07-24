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

import com.balero.models.Content;
import com.balero.models.Footer;
import com.balero.models.Pages;
import com.balero.models.Users;
import com.balero.services.Administrator;
import com.balero.services.ListFilesUtil;
import com.balero.services.ScreenSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class HomeController extends AbstractController {

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

    private boolean adminElements = false;
    private int i = 0;

    private String baleroAdmin;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        ModelAndView model = new ModelAndView("index");

        String background = "eternity.png";
		model.addObject("background", background);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("baleroAdmin")) {
                    baleroAdmin = cookie.getValue();
                }
                System.out.println("cookies: " + cookie.getName() + " : " + cookie.getValue());
            }
        }

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

        // Home Post's
        List<Content> rows = ContentDAO.findAll();

        // Footer content
        List<Footer> footer = FooterDAO.findAll();

        List<Pages> pages  = PagesDAO.findAll();

        /**
         * Enable or Disable and
         * Check if Admin Elements will
         * be displayed
         */
        model.addObject("admin", admin.getAccess());

        /**
         * System variables
         */

        String pathCover =  "media/default.jpg";
        File defaultCover = new File(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + pathCover);

        if(defaultCover.exists()) {
            model.addObject("defaultCover", pathCover);
        } else {
            model.addObject("defaultCover", "resources/images/eternity.png");
        }

        ScreenSize screen = new ScreenSize();
        if(screen.getWidth() <= 1920) {
            model.addObject("mobile", true);
        } else {
            model.addObject("mobile", false);
        }

        model.addObject("settingsId", SettingsDAO.settingsId());
        model.addObject("sitename", SettingsDAO.siteName());
        model.addObject("slogan", SettingsDAO.siteSlogan());
        model.addObject("url", SettingsDAO.siteURL());
        model.addObject("files", files);
        model.addObject("rows", rows);
        model.addObject("pages", pages);
        model.addObject("footer", footer);

		return model;
	}

}
