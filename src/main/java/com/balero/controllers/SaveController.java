package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/save")
public class SaveController {

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String save(HttpServletRequest request,
            @RequestParam("id") String id,
            @RequestParam("dataContainer") String dataContainer)  {

        String body = request.getParameter("body");
        int intId = Integer.parseInt(id);
        ContentDAO.updatePost(intId, dataContainer, "fullpost", "welcome-test-post", "en");

        return "redirect:/";

    }

}
