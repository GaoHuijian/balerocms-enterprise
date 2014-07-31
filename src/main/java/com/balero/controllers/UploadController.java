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

import com.balero.services.FileManager;
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
                //String rootPath = System.getProperty("catalina.home");
                File dir = new File("./media/uploads");
                if (!dir.exists())
                    dir.mkdirs();

                String[] ext = new String[9];
                // Add your extension here
                ext[0] = ".jpg";
                ext[1] = ".png";
                ext[2] = ".bmp";
                ext[3] = ".jpeg";

                for(int i = 0; i < ext.length; i++) {
                    int intIndex = inputFileName.indexOf(ext[i]);
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
        //String rootPath = System.getProperty("catalina.home");

        int i = 0;
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        File[] fList = listFilesUtil.listFilesInArray();

        for (File file : fList){
            i++;
            if (sliderContainerToInt == i) {
                // Delete Header File
                new File("./media/uploads"
                        + File.separator + file.delete());
                // Debug
                System.out.println("Loop: " + i + " corresponding to file: " + file.getName());
                System.out.println("Finding:" + sliderContainer);
            }
        }

        return "redirect:/";

    }

    /**
     *
     * @param defaultSliderContainer
     * @return String
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam String defaultSliderContainer) {

        // sliderContairner is a String, so convert to int
        int sliderContainerToInt = Integer.parseInt(defaultSliderContainer);
        //String rootPath = System.getProperty("catalina.home");

        int i = 0;
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        File[] fList = listFilesUtil.listFilesInArray();

        for (File file : fList){
            i++;
            if (sliderContainerToInt == i) {
                // Create Default Header File
                File source = new File("./media/uploads"
                        + File.separator + file.getName());
                File dest = new File("./media"
                        + File.separator + "default.jpg");
                FileManager cp = new FileManager();
                try {
                    cp.copyFile(source, dest);
                } catch (Exception e) {
                    System.out.println("Can't save");
                }
                // Debug
                System.out.println("Loop: " + i + " corresponding to file: " + file.getName());
                System.out.println("Finding:" + defaultSliderContainer);
            }
        }

        return "redirect:/";

    }

}
