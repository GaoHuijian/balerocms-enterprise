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
import com.balero.services.Administrator;
import com.balero.services.ListFilesUtil;
import com.balero.services.UAgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class ContentController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private com.balero.models.CommentsDAO CommentsDAO;

    /**
     *
     * @param baleroAdmin
     * @param id
     * @param model
     * @param more
     * @return
     */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String showFull(@CookieValue(value = "baleroAdmin",
                           defaultValue = "init") String baleroAdmin,
                           @PathVariable int id, Model model,
                           @RequestParam(value = "more", required = false) Integer more,
                           HttpServletRequest request) {

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

        List<Content> content = ContentDAO.findContent(id);
        List<Pages> pages = PagesDAO.findAll();
        List<Comments> comments = CommentsDAO.findById(id);

        if(more == null || more != 1) {
            more = 0;
        } else {
            more = 1;
        }

        /**
         * Variables
         */
        String pathCover =  "media/default.jpg";
        File defaultCover = new File(System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + pathCover);

        if(defaultCover.exists()) {
            model.addAttribute("defaultCover", pathCover);
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

        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("comments", comments);
        model.addAttribute("admin", admin.getAccess());
        model.addAttribute("files", files);
        model.addAttribute("content", content);
        model.addAttribute("more", more);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "post";

    }

    /**
     *
     * @param id
     * @param content
     * @param full
     * @return
     */
    @RequestMapping(value = "/post/edit", method = RequestMethod.POST)
    public String editFull(@RequestParam String id,
                           @RequestParam String content,
                           @RequestParam String full,
                           @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        Administrator security = new Administrator();
        if(security.isAdmin(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        if(full.isEmpty()) {
            full = "";
        }

        int intId = Integer.parseInt(id);
        ContentDAO.updatePost(intId, content, full, "welcome-test-post-slug");

        return "redirect:/post/" + id;

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/post/delete", method = RequestMethod.GET)
    public String deleteFull(@RequestParam String id,
                             @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        Administrator security = new Administrator();
        if(security.isAdmin(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        ContentDAO.deletePost(intId);

        return "redirect:/";

    }

    /**
     *
     * @return String
     */
    @RequestMapping(value = "/post/add", method = RequestMethod.GET)
    public String add(@CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        Administrator security = new Administrator();
        if(security.isAdmin(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        String html;

        html = "<h1>\n" +
                "    <img alt=\"Image\" class=\"left\" src=\"/resources/images/nopic.png\"/>\n" +
                "    Title\n" +
                "</h1>\n" +
                "<hr />\n" +
                "<h3>SubTitle</h3>\n" +
                "<p>\n" +
                "    Content\n" +
                "</p>";

        ContentDAO.addPost(html, null, "welcome-test-post");

        return "redirect:/";

    }

    /**
     *
     * @param request
     * @param id
     * @param dataContainer
     * @return String
     */
    @RequestMapping(value = "/post/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,
                       @RequestParam("id") String id,
                       @RequestParam("dataContainer") String dataContainer,
                       @CookieValue(value = "baleroAdmin") String baleroAdmin)  {

        /**
         * Security
         */
        Administrator security = new Administrator();
        if(security.isAdmin(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        ContentDAO.updatePost(intId, dataContainer, "fullpost", "welcome-test-post");

        return "redirect:/";

    }

    /**
     *
     * @param id
     * @param name
     * @param comment
     * @return String
     */
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable String id,
                       @RequestParam("name") String name,
                       @RequestParam("comment") String comment)  {

        int intId = Integer.parseInt(id);
        String nameNoHtml = HtmlUtils.htmlEscape(name);
        String commentNoHtml = HtmlUtils.htmlEscape(comment);
        CommentsDAO.addComment(nameNoHtml, commentNoHtml, intId);

        return "redirect:/post/{id}";

    }

    /**
     *
     * @param id
     * @param postId
     * @return String
     */
    @RequestMapping(value = "/comments/delete/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable String id,
                                @RequestParam(value = "postId") int postId,
                                @CookieValue(value = "baleroAdmin") String baleroAdmin)  {

        /**
         * Security
         */
        Administrator security = new Administrator();
        if(security.isAdmin(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        CommentsDAO.deleteComment(intId);

        return "redirect:/post/" + postId;

    }

}
