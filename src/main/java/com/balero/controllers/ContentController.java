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
import com.balero.services.UsersAuth;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    private static final Logger logger = Logger.getLogger(LoginController.class);

    /**
     * Show post content
     *
     * @param baleroAdmin Admmin cookie
     * @param id Post ID
     * @param model MVC Model
     * @param more bool active or disable link
     * @param request HTTP Request
     * @param locale System locale
     * @return
     */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String showFull(@CookieValue(value = "baleroAdmin",
                           defaultValue = "init") String baleroAdmin,
                           @PathVariable int id,
                           Model model,
                           @RequestParam(value = "more", required = false) Integer more,
                           HttpServletRequest request,
                           Locale locale) {

        String background = "eternity.png";
        model.addAttribute("background", background);

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

        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String files = listFilesUtil.listFiles();

        // Footer content
        List<Footer> footer = FooterDAO.findAll();

        List<Content> content = ContentDAO.findContent(id);
        List<Pages> pages = PagesDAO.findAll(locale);
        List<Comments> comments = CommentsDAO.findById(id);

        if(more == null || more != 1) {
            more = 0;
        } else {
            more = 1;
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

        model.addAttribute("title", ContentDAO.postTitle(id));
        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("comments", comments);
        model.addAttribute("files", files);
        model.addAttribute("content", content);
        model.addAttribute("more", more);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "post";

    }

    /**
     * Post edit
     *
     * @param id int Post ID
     * @param content String Post content
     * @param full String Post full content
     * @param code String System locale
     * @param baleroAdmin Cookie administrator
     * @return
     */
    @RequestMapping(value = "/post/edit/{id}", method = RequestMethod.POST)
    public String editFull(@PathVariable int id,
                           @RequestParam String content,
                           @RequestParam String full,
                           @RequestParam String code,
                           @RequestParam String status,
                           @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        // Security
        UsersAuth security = new UsersAuth();
        if(security.auth(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        // DB Settings
        List<Content> dbcontent = ContentDAO.findContent(id);
        String fileSettings = null;
        for(Content obj: dbcontent) {
            // file setting from database
            fileSettings = obj.getFile();
        }

        if(full.isEmpty()) {
            full = "";
        }

        ContentDAO.updatePost(id, content, full, "welcome-test-post-slug",
                code, fileSettings, status);

        return "redirect:/post/" + id;

    }

    /**
     * Delete post
     *
     * @param id String post ID
     * @param baleroAdmin Cookie administrato
     * @return
     */
    @RequestMapping(value = "/post/delete", method = RequestMethod.GET)
    public String deleteFull(@RequestParam String id,
                             @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        // Security
        UsersAuth security = new UsersAuth();
        if(security.auth(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        ContentDAO.deletePost(intId);

        return "redirect:/";

    }

    /**
     * Add new post
     *
     * @param baleroAdmin
     * @param locale
     * @return
     */
    @RequestMapping(value = "/post/add", method = RequestMethod.GET)
    public String add(@CookieValue(value = "baleroAdmin") String baleroAdmin,
                      Locale locale) {

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        security.setCredentials(baleroAdmin, UsersDAO);
        if(security.auth(baleroAdmin, security.getLocalUsername(),
                security.getLocalPassword()) == false)
            return "hacking";

        String html;

        html = "<h3>Lorem ipsum</h3>\n" +
                "<p>Lorem Ipsum is text that is commonly used in graphic" +
                "design typefaces demonstrations or draft design to test the" +
                "visual design before inserting the final text.</p>\n" +
                "<p>Although currently has no sources to justify ótesis hip," +
                "professor of classical philology Richard McClintock says its" +
                "use dates back to the early printers XVI.1 century Its use in" +
                "some text editors well known in today has given the new popularity lorem ipsum.</p>\n" +
                "<p>The text itself no sense, although it is not completely" +
                "random, but derives from a text by Cicero in Latin language," +
                "whose words have been removed them syllables or letters." +
                "The meaning of the text does not matter, since it is just a test demostracióno,</p>\n";

            // ERRROR HTTP 500 NULL (check if error exists)
            String status = "pending";
            switch (security.getLocalUsername()) {
                case "admin":
                    status = "published";
                    break;
            }

            ContentDAO.addPost(html, null, "welcome-test-post",
                    locale, security.getLocalUsername(), status);

        return "redirect:/";

    }

    /**
     * Save post content
     *
     * @param id int Post ID
     * @param file MultipartFile[] file content
     * @param dataContainer String post content
     * @param code System locale
     * @param baleroAdmin Cookie administrato
     * @return
     * @throws IOException Stop Loop and show message log
     */
    @RequestMapping(value = "/post/save/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id") int id,
                       @RequestParam(value = "file", required = false) MultipartFile[] file,
                       @RequestParam("dataContainer") String dataContainer,
                       @RequestParam("code") String [] code,
                       @RequestParam(value = "status", required = false) String status,
                       @CookieValue(value = "baleroAdmin") String baleroAdmin) throws IOException {

        if(status == null) {
            status = "pending";
        }

        logger.debug("First data container: " + dataContainer);


        // DB Settings
        List<Content> content = ContentDAO.findContent(id);
        String fileSettings = null;
        String full = null;
        for(Content obj: content) {
            // file setting from database
            fileSettings = obj.getFile();
            full = obj.getFull();
        }

        //int intId = Integer.parseInt(id);

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        security.setCredentials(baleroAdmin, UsersDAO);
        if(security.auth(baleroAdmin, security.getLocalUsername(),
                security.getLocalPassword()) == false)
                    return "hacking";

        /**
         * File upload
         */

        String inputFileName = null;

        try {

            logger.debug("Try...{");

            for (int j = 0; j < file.length; j++) {

                logger.debug("file name: " + file[j].getOriginalFilename());

                if(file[j].getOriginalFilename().isEmpty() ||
                        file[j].getOriginalFilename().equals("") ||
                        file[j].getOriginalFilename() == null) {
                    logger.debug("file name is empty");
                    // Model
                    ContentDAO.updatePost(id, dataContainer, full,
                            "welcome-test-post", code[j], fileSettings, status);
                    throw new Exception("Saved without image");
                }

                logger.debug("For......{");

                logger.debug("Data container value: " + dataContainer);

                if (!file[j].isEmpty() && !dataContainer.isEmpty()) {

                    logger.debug("Uploading file... " + file[j]);
                    inputFileName = file[j].getOriginalFilename();

                    byte[] bytes = file[j].getBytes();

                    // Creating the directory to store file
                    //String rootPath = System.getProperty("catalina.home");
                    File dir = new File("../webapps/media/backgrounds");
                    if (!dir.exists())
                        dir.mkdirs();

                    String[] ext = new String[9];
                    // Add your extension here
                    ext[0] = ".jpg";
                    ext[1] = ".png";
                    ext[2] = ".bmp";
                    ext[3] = ".jpeg";

                    for (int i = 0; i < ext.length; i++) {

                        int intIndex = inputFileName.indexOf(ext[i]);

                        if (intIndex == -1) {

                            System.out.println("File extension is not valid");

                        } else {

                            /**
                             * Create file and save  content
                             * to the database
                             */

                            // Create the file on server
                            File serverFile = new File(dir.getAbsolutePath()
                                    + File.separator + inputFileName);
                            BufferedOutputStream stream = new BufferedOutputStream(
                                    new FileOutputStream(serverFile));
                            stream.write(bytes);
                            stream.close();

                            // Model
                            ContentDAO.updatePost(id, dataContainer, full,
                                    "welcome-test-post", code[j], inputFileName, status);

                            throw new Exception("Loop: " + j + ":" + i +
                                    " - Data saved And Upload Sucess!");

                        }
                    }

                }

            } // for

        } catch (Exception e) {

            logger.debug("Debug error: " + e.getMessage());

        }

        return "redirect:/";

    }

    /**
     * Add comment
     *
     * @param id
     * @param name
     * @param comment
     * @return
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
     * Delete post
     *
     * @param id
     * @param postId
     * @param baleroAdmin
     * @return
     */
    @RequestMapping(value = "/comments/delete/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable String id,
                                @RequestParam(value = "postId") int postId,
                                @CookieValue(value = "baleroAdmin") String baleroAdmin)  {

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        //security.init(baleroAdmin);
        if(security.auth(baleroAdmin, security.getLocalUsername(),
                security.getLocalPassword()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        CommentsDAO.deleteComment(intId);

        return "redirect:/post/" + postId;

    }

}
