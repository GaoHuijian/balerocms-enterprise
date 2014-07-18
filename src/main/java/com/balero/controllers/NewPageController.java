package com.balero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/new")
public class NewPageController {

    @Autowired
    private com.balero.models.PagesDAO PagesDAO;

    @RequestMapping(method = RequestMethod.POST)
    public String newPage(@RequestParam("name") String name) {

        if(name.equals("")) {
            name = "_empty";
        }

        String html;

        html = "<p>\n" +
                "Lorem ipsum es el texto que se usa habitualmente en diseño gráfico en demostraciones de tipografías o de borradores de diseño para probar el diseño visual antes de insertar el texto final.\n" +
                "\n" +
                "Aunque no posee actualmente fuentes para justificar sus hipótesis, el profesor de filología clásica Richard McClintock asegura que su uso se remonta a los impresores de comienzos del siglo XVI.1 Su uso en algunos editores de texto muy conocidos en la actualidad ha dado al texto lorem ipsum nueva popularidad.\n" +
                "\n" +
                "El texto en sí no tiene sentido, aunque no es completamente aleatorio, sino que deriva de un texto de Cicerón en lengua latina, a cuyas palabras se les han eliminado sílabas o letras.\n" +
                "</p>";

        PagesDAO.addPage(name, html, "en");

        return "redirect:/";

    }

}
