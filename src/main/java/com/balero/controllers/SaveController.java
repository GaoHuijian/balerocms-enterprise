package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Save controller
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/save")
public class SaveController {

    @Autowired
    private com.balero.models.ContentDAO ContentDAO;
    /**
     *
     * @param postContainer
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveContent(@RequestParam("postContainer") String postContainer) {

        ContentDAO.save(postContainer, "content", "en");
        return "redirect:/";

    }

}
