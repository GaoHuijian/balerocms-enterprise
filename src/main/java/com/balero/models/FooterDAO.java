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

}