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
public class ContentDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     * SELECT * FROM 'content'
     *
     * @return rows
     */
    @Transactional
    public List<Content> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Content").list();
        return rows;
    }

    @Transactional
    public List<Content> findContent(int id) {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Content where id = '" + id +"'").list();
        return rows;
    }

    /**
     * INSERT INTO TABLE content VALUES content, body, lang
     *
     * @param postContainer
     * @param fullcontent
     * @param lang
     */
    @Transactional
    public void addPost(String postContainer, String fullcontent, String slug, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setContent(postContainer);
        content.setFull(fullcontent);
        content.setSlug(slug);
        content.setLang(lang);
        session.save(content);
        session.flush();
        session.close();
    }

    /**
     * UPDATE query
     *
     * @param postContainer
     * @param fullcontent
     * @param lang
     */
    @Transactional
    public void updatePost(int id, String postContainer, String fullcontent, String slug, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setId(id);
        content.setContent(postContainer);
        content.setFull(fullcontent);
        content.setSlug(slug);
        content.setLang(lang);
        session.update(content);
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
    public void deletePost(int id) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setId(id);
        session.delete(content);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
//        query = "create table if not exists content (" +
//                "id int not null auto_increment," +
//                "content longtext not null," +
//                "fullcontent longtext not null," +
//                "lang varchar (250) not null," +
//                "PRIMARY KEY (id)" +
//                ");";
//        session.createSQLQuery(query).executeUpdate();
        Content content = new Content();
        content.setId(1);
        content.setContent("<h1><img alt=\"Image\" class=\"left\" src=\"/resources/images/nopic.png\" />&nbsp;Welcome</h1>\n" +
                "\n" +
                "<hr />\n" +
                "<h3>Lorem ipsum</h3>\n" +
                "\n" +
                "<p>Lorem ipsum es el texto que se usa habitualmente en dise&ntilde;o gr&aacute;fico en demostraciones de tipograf&iacute;as o de borradores de dise&ntilde;o para probar el dise&ntilde;o visual antes de insertar el texto final.</p>\n" +
                "\n" +
                "<p>Aunque no posee actualmente fuentes para justificar sus hip&oacute;tesis, el profesor de filolog&iacute;a cl&aacute;sica Richard McClintock asegura que su uso se remonta a los impresores de comienzos del siglo XVI.1 Su uso en algunos editores de texto muy conocidos en la actualidad ha dado al texto lorem ipsum nueva popularidad.</p>\n" +
                "\n" +
                "<p>El texto en s&iacute; no tiene sentido, aunque no es completamente aleatorio, sino que deriva de un texto de Cicer&oacute;n en lengua latina, a cuyas palabras se les han eliminado s&iacute;labas o letras. El significado del texto no tiene importancia, ya que solo es una demostraci&oacute;n o prueba,</p>\n");
        content.setFull(null);
        content.setSlug("welcome-test-post");
        content.setLang("en");
        session.save(content);
        session.flush();
    }

}