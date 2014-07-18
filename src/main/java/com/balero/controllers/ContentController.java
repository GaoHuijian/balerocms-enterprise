package com.balero.controllers;

import com.balero.models.Content;
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
public class ContentController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @RequestMapping(value = "/full/{id}", method = RequestMethod.GET)
    public String showFull(@CookieValue(value = "baleroAdmin", defaultValue = "init") String baleroAdmin, @PathVariable int id, Model model, @RequestParam(value = "more", required = false) Integer more) {

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

        if(more == null || more != 1) {
            more = 0;
        } else {
            more = 1;
        }

        /**
         * Variables
         */
        model.addAttribute("admin", admin.getAccess());
        model.addAttribute("files", files);
        model.addAttribute("content", content);
        model.addAttribute("more", more);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "full";

    }

    @RequestMapping(value = "/full/edit", method = RequestMethod.POST)
    public String editFull(@RequestParam String id,
                           @RequestParam String content,
                           @RequestParam String full) {
        //@RequestParam String slug,
        //@RequestParam String lang) {

        if(full.isEmpty()) {
            full = "";
        }

        int intId = Integer.parseInt(id);
        ContentDAO.updatePost(intId, content, full, "welcome-test-post-slug", "en");

        return "redirect:/full/" + id;

    }


    @RequestMapping(value = "/full/delete", method = RequestMethod.GET)
    public String deleteFull(@RequestParam String id) {

        int intId = Integer.parseInt(id);
        ContentDAO.deletePost(intId);

        return "redirect:/";

    }

}
