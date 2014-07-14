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
public class FooterDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     * SELECT * FROM 'footer'
     *
     * @return rows
     */
    @Transactional
    public List<Footer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Footer where lang = 'en'").list();
        return rows;
    }

    @Transactional
    public void updateFooter(int fid, String fContainer, String lang) {
        Session session = sessionFactory.openSession();
        Footer footer = new Footer();
        footer.setId(fid);
        footer.setContent(fContainer);
        footer.setLang(lang);
        session.update(footer);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
        query = "create table if not exists footer (" +
                "id int not null auto_increment," +
                "content longtext not null," +
                "lang varchar (250) not null," +
                "PRIMARY KEY (id)" +
                ");";
        session.createSQLQuery(query).executeUpdate();
        Footer footer = new Footer();
        footer.setId(1);
        footer.setContent("Copyright (c) 2013-2014 <a href=\"http://www.balerocms.com/\">BaleroCMS Enterprise</a>.");
        footer.setLang("en");
        session.save(footer);
        session.flush();
    }

}