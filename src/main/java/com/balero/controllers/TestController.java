package com.balero.controllers;

import com.balero.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Unit Test Controller Class
 */
@Controller
@RequestMapping("/")
public class TestController {
	
	@Autowired private com.balero.models.TestDAO TestDAO;
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String list(Model model) {
		List<Test> users = TestDAO.findAll();
		model.addAttribute("users", users);
		return "test";
	}

    @RequestMapping(value = "add")
    public String addUser() {
        TestDAO.add();
        return "redirect:/test";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        TestDAO.delete(id);
        return "redirect:/test";
    }

}
