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

package com.balero.services;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Contains some methods to list files and folders from a directory
 *
 * @author Loiane Groner
 * http://loiane.com (Portuguese)
 * http://loianegroner.com (English)
 */
public class ListFilesUtil {

    private static final Logger logger = Logger.getLogger(ListFilesUtil.class);

    /**
     * List all the files under directory '/media/uploads/'
     *
     * @author Anibal Gomez
     */
    public String listFiles(){

        String headers = "";
        int i = 0;

        //String rootPath = System.getProperty("catalina.home");
        File directory = new File("./media/uploads");

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList){
            i++;
            if (file.isFile()){
                logger.debug(file.getName());
                headers += i + "<li><img src=\"/media/uploads/" + file.getName() + "\"></li>\n";
            }
        }

        return headers;

    }

    public File[] listFilesInArray(){

        //String rootPath = System.getProperty("catalina.home");
        File directory = new File("./media/uploads");

        //get all the files from a directory
        File[] fList = directory.listFiles();

        return fList;

    }

}