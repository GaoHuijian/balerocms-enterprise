package com.balero.controllers;

import com.balero.config.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Logout controller
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

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    public String setup(Model model) {

        MyDataSource dataSource = new MyDataSource();

        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setUsername("newValue");

        model.addAttribute("sucess", false);
        model.addAttribute("jdbc.username", dataSource.getUsername());
        model.addAttribute("jdbc.password", dataSource.getPassword());

        return "setup";

    }


    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public String install(@RequestParam("dbuser") String dbuser,
                          @RequestParam("dbpass") String dbpass,
                          Model model) {

        MyDataSource dataSource = new MyDataSource();

        // data from form
        //dataSource.setUsername(dbuser);
        //dataSource.setPassword(dbpass);

        System.out.println("connecting username: " + dataSource.getUsername());
        System.out.println("connecting password: " + dataSource.getPassword());

        System.out.println("creating table test");
        TestDAO.make();
        System.out.println("creating table content");
        ContentDAO.make();
        System.out.println("creating table footer");
        FooterDAO.make();
        System.out.println("creating tables pages");
        PagesDAO.make();
        System.out.println("creating table users");
        UsersDAO.make();

        model.addAttribute("sucess", true);

        return "setup";

    }

}
