package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * Upload controller
 *
 * Based on: http://www.journaldev.com/2573/spring-mvc-
 * file-upload-example-tutorial-single-and-multiple-files
 *
 * @author Anibal Gomez
 * @version 1.0
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private ServletContext servletContext;

    /**
     * Upload file on server
     *
     * @param name
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String upload(@RequestParam("name") String name,
                         @RequestParam("file") MultipartFile file) {


        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "webapps/media/uploads");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.println("Server File Location="
                        + serverFile.getAbsolutePath() + "You successfully uploaded file=" + name);

            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload " + name
                    + " because the file was empty.");
        }

        return "redirect:/";

    }

}
