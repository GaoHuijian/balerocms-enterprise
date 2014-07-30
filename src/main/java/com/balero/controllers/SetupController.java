/**
 * <pre>
 * Balero CMS Enterprise Edition is free and open source software under MIT License.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 <Balero CMS All Rights Reserved>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * <a href="http://www.balerocms.com">BaleroCMS.com</a>
 * </pre>
 *
 * @author      Anibal Gomez
 * @version     1.0
 * @since       1.0
 */

package com.balero.controllers;

import com.balero.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private com.balero.models.CommentsDAO CommentsDAO;

    @Autowired
    private Environment env;

    /**
     * Only the installer can change the
     * database properties connection
     *
     * @param model
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String setup(Model model) {

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

    /**
     *
     * @param model
     * @return String
     */
    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public String install(Model model) {

        System.out.println("Inseting data sample...");
        TestDAO.make();
        ContentDAO.make();
        FooterDAO.make();
        PagesDAO.make();
        UsersDAO.make();
        System.out.println("Inseting settings data...");
        SettingsDAO.make();
        System.out.println("Inseting comments data...");
        CommentsDAO.make();

        model.addAttribute("sucess", true);

        return "setup";

    }

}
