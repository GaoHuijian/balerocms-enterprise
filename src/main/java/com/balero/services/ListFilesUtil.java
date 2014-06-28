package com.balero.services;

import java.io.File;

/**
 * Contains some methods to list files and folders from a directory
 *
 * @author Loiane Groner
 * http://loiane.com (Portuguese)
 * http://loianegroner.com (English)
 */
public class ListFilesUtil {

    /**
     * List all the files under directory '/media/uploads/'
     *
     * @author Anibal Gomez
     */
    public String listFiles(){

        String headers = "";

        String rootPath = System.getProperty("catalina.home");
        File directory = new File(rootPath + File.separator + "webapps/media/uploads");

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getName());
                headers += "<li><img src=\"/media/uploads/" + file.getName() + "\"></li>\n";
            }
        }

        return headers;

    }

}