package com.balero.controllers;

import com.balero.services.ListFilesUtil;
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
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {


        String inputFileName = file.getOriginalFilename();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "webapps/media/uploads");
                if (!dir.exists())
                    dir.mkdirs();

                String[] ext = new String[9];
                // Add your extension here
                ext[0] = ".jpg";
                ext[1] = ".png";
                ext[2] = ".bmp";
                ext[3] = ".jpeg";

                int intIndex = inputFileName.indexOf("jpg");

                for(int i = 0; i < ext.length; i++) {
                    if(intIndex == -1) {
                        System.out.println("File extension is not valid");
                    } else {
                        // Create the file on server
                        File serverFile = new File(dir.getAbsolutePath()
                                + File.separator + inputFileName);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();
                    }
                }

                System.out.println("You successfully uploaded file");

            } catch (Exception e) {
                System.out.println("You failed to upload => " + e.getMessage());
            }
        } else {
            System.out.println("You failed to upload because the file was empty.");
        }

        return "redirect:/";

    }

    /**
     * This method loop containing file headers files
     * and then delete the selected file by number.
     * Example:
     *
     * Loop
     *      if sliderContainer = 2 Then
     *          Delete File Numer 2
     *      End if
     * End Loop
     *
     * @param sliderContainer
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam String sliderContainer) {

        // sliderContairner is a String, so convert to int
        int sliderContainerToInt = Integer.parseInt(sliderContainer);
        String rootPath = System.getProperty("catalina.home");

        int i = 0;
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        File[] fList = listFilesUtil.listFilesInArray();

        for (File file : fList){
            i++;
            if (sliderContainerToInt == i) {
                // Delete Header File
                new File(rootPath + File.separator + "webapps/media/uploads"
                        + File.separator + file.delete());
                // Debug
                System.out.println("Loop: " + i + " corresponding to file: " + file.getName());
                System.out.println("Finding:" + sliderContainer);
            }
        }

        return "redirect:/";

    }

}
