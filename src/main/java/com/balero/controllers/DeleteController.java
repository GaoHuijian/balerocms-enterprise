package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String delete(@RequestParam int id) {

        System.out.println("id: " + id);
        ContentDAO.deletePost(id);

        return "redirect:/";

    }

}
