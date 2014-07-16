package com.balero.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Get, Insert and Update data from database
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class PagesDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     * SELECT * FROM 'pages'
     *
     * @return rows
     */
    @Transactional
    public List<Pages> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Pages").list();
        return rows;
    }

    /**
     * SELECT * FROM 'pages' where id = '{id}'
     *
     * @return rows
     */
    @Transactional
    public List<Pages> findPage(int pageId) {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Pages where id = '" + pageId + "'").list();
        return rows;
    }

    /**
     * INSERT INTO TABLE pages VALUES name, conteny, slug, lang
     *
     * @param content
     * @param  slug
     * @param lang
     */
    @Transactional
    public void addPage(String name, String content, String slug, String lang) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setName(name);
        pages.setContent(content);
        pages.setSlug(slug);
        pages.setLang(lang);
        session.save(pages);
        session.flush();
        session.close();
    }

    /**
     * UPDATE QUERY
     *
     * @param id
     * @param content
     * @param slug
     * @param lang
     */
    @Transactional
    public void updatePage(int id, String name, String content, String slug, String lang) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setId(id);
        pages.setName(name);
        pages.setContent(content);
        pages.setSlug(slug);
        pages.setLang(lang);
        session.update(pages);
        session.flush();
        session.close();
    }

    /**
     * DELETE FROM query
     *
     * @param id
     * @param lang
     */
    @Transactional
    public void deletePage(int id, String slug, String lang) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setId(id);
        pages.setSlug(slug);
        pages.setLang(lang);
        session.delete(pages);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
//        query = "create table if not exists pages (" +
//                "id int not null auto_increment," +
//                "content longtext not null," +
//                "slug varchar (250) not null," +
//                "lang varchar (250) not null," +
//                "PRIMARY KEY (id)" +
//                ");";
//        session.createSQLQuery(query).executeUpdate();
        Pages pages = new Pages();
        pages.setId(1);
        pages.setName("Welcome");
        pages.setContent("<h1><img alt=\"Image\" class=\"left\" src=\"/resources/images/nopic.png\" />&nbsp;Welcome</h1>\n" +
                "\n" +
                "<hr />\n" +
                "<h3>Lorem ipsum</h3>\n" +
                "\n" +
                "<p>Lorem ipsum es el texto que se usa habitualmente en dise&ntilde;o gr&aacute;fico en demostraciones de tipograf&iacute;as o de borradores de dise&ntilde;o para probar el dise&ntilde;o visual antes de insertar el texto final.</p>\n" +
                "\n" +
                "<p>Aunque no posee actualmente fuentes para justificar sus hip&oacute;tesis, el profesor de filolog&iacute;a cl&aacute;sica Richard McClintock asegura que su uso se remonta a los impresores de comienzos del siglo XVI.1 Su uso en algunos editores de texto muy conocidos en la actualidad ha dado al texto lorem ipsum nueva popularidad.</p>\n" +
                "\n" +
                "<p>El texto en s&iacute; no tiene sentido, aunque no es completamente aleatorio, sino que deriva de un texto de Cicer&oacute;n en lengua latina, a cuyas palabras se les han eliminado s&iacute;labas o letras. El significado del texto no tiene importancia, ya que solo es una demostraci&oacute;n o prueba,</p>\n");
        pages.setSlug("welcome-test-page");
        pages.setLang("en");
        session.save(pages);
        session.flush();
    }

}