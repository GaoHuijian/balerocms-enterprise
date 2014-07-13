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
     * INSERT INTO TABLE pages VALUES name, conteny, slug, lang
     *
     * @param name
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
     * @param name
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
        query = "create table if not exists pages (" +
                "id int not null auto_increment," +
                "name varchar (250) not null," +
                "content longtext not null," +
                "slug varchar (250) not null," +
                "lang varchar (250) not null," +
                "PRIMARY KEY (id)" +
                ");";
        session.createSQLQuery(query).executeUpdate();
    }

}