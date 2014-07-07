package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/footer")
public class FooterController {

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String save(HttpServletRequest request,
            @RequestParam("fid") String id,
            @RequestParam("fContainer") String dataContainer)  {

        int intId = Integer.parseInt(id);
        FooterDAO.updateFooter(intId, dataContainer, "en");

        return "redirect:/";

    }

}
