package com.balero.controllers;

import com.balero.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Setup Controller
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/setup")
public class SetupController {

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.TestDAO TestDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;
    private Model model;

    @Value( "${jdbc.username}" )
    private String jdbcUsername;

    @Value( "${jdbc.password}" )
    private String jdbcPassword;

    @Autowired
    private Environment env;

    private int step = 1;
    private int installed = 0;

    /**
     * Only the installer can change the
     * database properties connection
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String setup(Model model) {

        // get dbuser and dbname
        jdbcUsername = env.getProperty("jdbc.username");
        jdbcPassword = env.getProperty("jdbc.password");

        model.addAttribute("sucess", false);
        model.addAttribute("dbuser", jdbcUsername);
        model.addAttribute("dbpass", jdbcPassword);
        model.addAttribute("CATAINA_HOME", env.getProperty("CATALINA_HOME"));

        try {

            List<Test> users = TestDAO.findAll();
            model.addAttribute("users", users);

            if(users.isEmpty()) {
                throw new Exception();
            } else {
                return "installed";
            }


        } catch (Exception e) {

            return "setup";

        }


    }


    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public String install(Model model) {

        System.out.println("Inseting data sample...");
        TestDAO.make();
        ContentDAO.make();
        FooterDAO.make();
        PagesDAO.make();
        UsersDAO.make();

        model.addAttribute("sucess", true);

        return "setup";

    }

}
