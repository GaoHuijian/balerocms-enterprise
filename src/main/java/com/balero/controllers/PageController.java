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
        PagesDAO.updatePage(intId, name, content, "test-web-page", "en");

        return "redirect:/page/" + id;

    }

}
