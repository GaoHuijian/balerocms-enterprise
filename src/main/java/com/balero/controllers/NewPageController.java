package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/new")
public class NewPageController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String newPage() {

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

        PagesDAO.addPage(html, "test-page", "en");

        return "redirect:/";

    }

}
