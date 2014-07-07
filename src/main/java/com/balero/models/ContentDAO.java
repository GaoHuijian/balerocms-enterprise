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

    /**
     * INSERT INTO TABLE content VALUES content, body, lang
     *
     * @param postContainer
     * @param fullcontent
     * @param lang
     */
    @Transactional
    public void addPost(String postContainer, String fullcontent, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setContent(postContainer);
        content.setFullContent(fullcontent);
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
    public void updatePost(int id, String postContainer, String fullcontent, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setId(id);
        content.setContent(postContainer);
        content.setFullContent(fullcontent);
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
    public void deletePost(int id, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setId(id);
        content.setLang(lang);
        session.delete(content);
        session.flush();
        session.close();
    }

}