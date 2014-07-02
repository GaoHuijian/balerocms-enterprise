package com.balero.controllers;

import com.balero.models.Users;
import com.balero.services.Administrator;
import com.balero.services.ListFilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Front-end controller
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    private boolean adminElements = false;

    /**
     * Front-end Main Controller
     *
     * @param baleroAdmin Cookie
     * @param model Model Layout
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
	public String home(@CookieValue(value = "baleroAdmin", defaultValue = "init") String baleroAdmin, Model model) {
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

        /**
         * Enable or Disable and
         * Check if Admin Elements will
         * be displayed
         */
        model.addAttribute("admin", admin.getAccess());
        model.addAttribute("files", files);

		return "index";
	}

}
