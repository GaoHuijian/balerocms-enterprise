package com.balero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Unit Test Controller Class
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
        String background = "eternity.png";
		model.addAttribute("background", background);
		return "index";
	}

}
