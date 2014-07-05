package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/save")
public class SaveController {

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestParam("id") String id, @RequestParam("dataContainer") String dataContainer) {

        int intId = Integer.parseInt(id);
        ContentDAO.updatePost(intId, dataContainer, "test array", "content", "en");

        return "redirect:/";

    }

}
