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

import com.balero.models.Footer;
import com.balero.models.Pages;
import com.balero.services.UsersAuth;
import com.balero.services.ListFilesUtil;
import com.balero.services.UAgentInfo;
import com.github.slugify.InitSlugifyTag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@Controller
public class PageController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @Autowired
    private com.balero.models.FooterDAO FooterDAO;

    @Autowired
    private com.balero.models.UsersDAO UsersDAO;

    @Autowired
    private com.balero.models.SettingsDAO SettingsDAO;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = Logger.getLogger(PageController.class);

    /**
     *
     * @param baleroAdmin
     * @param slug
     * @param model
     * @return String
     */
    @RequestMapping(value = "/page/{slug}", method = RequestMethod.GET)
    public String showPage(@CookieValue(value = "baleroAdmin",
                           defaultValue = "init") String baleroAdmin,
                           @PathVariable String slug,
                           Model model,
                           HttpServletRequest request,
                           Locale locale) {

        String background = "eternity.png";
        model.addAttribute("background", background);

        /**
         * Credentials
         */
        UsersAuth auth = new UsersAuth();
        List<com.balero.models.Users> users = UsersDAO.administrator();
        String username = null;
        String password = null;
        for(com.balero.models.Users obj: users) {
            username = obj.getUsername();
            password = obj.getPassword();
        }
        /**
         * Enable or Disable and
         * Check if Admin Elements will
         * be displayed
         */
        model.addAttribute("auth", auth.auth(baleroAdmin, username, password));

        ListFilesUtil listFilesUtil = new ListFilesUtil();
        String files = listFilesUtil.listFiles();

        // Footer content
        List<Footer> footer = FooterDAO.findAll();

        // Pages
        List<Pages> page = PagesDAO.findPageBySlug(slug);
        List<Pages> pages = PagesDAO.findAll(locale);

        /**
         * Variables
         */

        String pathCover =  "../webapps/media/default.jpg";
        File defaultCover = new File(pathCover);

        if(defaultCover.exists()) {
            model.addAttribute("defaultCover", "media/default.jpg");
        } else {
            model.addAttribute("defaultCover", "resources/images/eternity.png");
        }

        /**
         * Mobile Routine
         */
        model.addAttribute("mobile", false);
        String useragent = request.getHeader("user-agent");
        String accept = "Accept: */*";
        UAgentInfo agent = new UAgentInfo(useragent, accept);
        if(agent.detectMobileQuick() == true) {
            model.addAttribute("mobile", true);
        }

        model.addAttribute("pagename", PagesDAO.pageName(slug));
        model.addAttribute("settingsId", SettingsDAO.settingsId());
        model.addAttribute("sitename", SettingsDAO.siteName());
        model.addAttribute("slogan", SettingsDAO.siteSlogan());
        model.addAttribute("url", SettingsDAO.siteURL());
        model.addAttribute("files", files);
        model.addAttribute("page", page);
        model.addAttribute("pages", pages);
        model.addAttribute("footer", footer);

        return "page";

    }

    @RequestMapping(value = "/page/edit/{id}", method = RequestMethod.POST)
    public String editPage(@PathVariable int id,
                           @RequestParam String name,
                           @RequestParam String content,
                           @RequestParam String code,
                           RedirectAttributes redirectAttributes,
                           Locale locale,
                           @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        if(security.auth(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        if(name.isEmpty()) {
            name = "(No Title)";
        }

        // Slugify
        String slug = InitSlugifyTag.getSlugify().slugify(name);

        ResourceBundle messages =
                ResourceBundle.getBundle("messages", locale);

        String view = null;
        try {
            PagesDAO.updatePage(id, name, content, slug, code);
            view = "redirect:/page/" + slug;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", messages.getString("label.page.slugerror"));
            view = "redirect:/";
        }

        return view;

    }


    @RequestMapping(value = "/page/delete", method = RequestMethod.GET)
    public String deletePage(@RequestParam String id,
                             @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        if(security.auth(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        int intId = Integer.parseInt(id);
        PagesDAO.deletePage(intId);

        return "redirect:/";

    }

    @RequestMapping(value = "/page/new", method = RequestMethod.POST)
    public String newPage(@RequestParam("name") String name,
                          Model model,
                          RedirectAttributes redirectAttributes,
                          Locale locale,
                          @CookieValue(value = "baleroAdmin") String baleroAdmin) {

        /**
         * Security
         */
        UsersAuth security = new UsersAuth();
        if(security.auth(baleroAdmin, UsersDAO.usrAdmin(), UsersDAO.pwdAdmin()) == false) {
            return "hacking";
        }

        if(name.equals("")) {
            name = "(No Title)";
        }

        String html;

        html = "<p>\n" +
                "Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en demostraciones de tipografías o de borradores de diseño para probar el diseño visual antes de insertar el texto final.\n" +
                "\n" +
                "Aunque no posee actualmente fuentes para justificar sus hipótesis, el profesor de filología clásica Richard McClintock asegura que su uso se remonta a los impresores de comienzos del siglo XVI.1 Su uso en algunos editores de texto muy conocidos en la actualidad ha dado al texto lorem ipsum nueva popularidad.\n" +
                "\n" +
                "El texto en sí no tiene sentido, aunque no es completamente aleatorio, sino que deriva de un texto de Cicerón en lengua latina, a cuyas palabras se les han eliminado sílabas o letras.\n" +
                "</p>";

        logger.debug("UTF-8->name: " + name);

        /**
         * Referer:
         * lingobit.com/solutions/java/java_localization.html
         */
        ResourceBundle messages =
                ResourceBundle.getBundle("messages", locale);

        String slug = InitSlugifyTag.getSlugify().slugify(name);
        try {
            PagesDAO.addPage(name, html, slug, locale);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", messages.getString("label.page.slugerror"));
        }

        return "redirect:/";

    }

}
