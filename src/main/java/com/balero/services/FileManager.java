package com.balero.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by lastprophet on 13/07/14.
 *
 * @url http://todoelmed.blogspot.mx/2012/
 * 05/manejo-de-archivos-o-ficheros-en.html
 */
public class FileManager {

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
            System.out.println(ex.getMessage());
        }
    }

}
