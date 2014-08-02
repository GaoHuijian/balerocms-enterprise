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

import java.io.*;

/**
 * Created by lastprophet on 13/07/14.
 *
 * @url http://todoelmed.blogspot.mx/2012/
 * 05/manejo-de-archivos-o-ficheros-en.html
 */
public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class);

    public void write(File file, String sString) {
        try {
            //Si no Existe el fichero lo crea
            if(!file.exists()){
                file.createNewFile();
            }
          /*Abre un Flujo de escritura,sobre el fichero con codificacion utf-8.
           *Además  en el pedazo de sentencia "FileOutputStream(file,true)",
           *true es por si existe el fichero seguir añadiendo texto y no borrar lo que tenia*/
            BufferedWriter Fescribe=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "utf-8"));
          /*Escribe en el fichero la cadena que recibe la función.
           *el string "\r\n" significa salto de linea*/
            Fescribe.write(sString + "\r\n");
            //Cierra el flujo de escritura
            Fescribe.close();
        } catch (Exception ex) {
            //Captura un posible error le imprime en pantalla
            logger.debug(ex.getMessage());
        }
    }

    /**
     * Copy file using stream
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}
