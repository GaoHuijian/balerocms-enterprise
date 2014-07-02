package com.balero.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class ContentDAO {
    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public void save(String postContainer, String body, String lang) {
        Session session = sessionFactory.openSession();
        Content content = new Content();
        content.setContent(postContainer);
        content.setBody(body);
        content.setLang(lang);
        session.save(content);
        session.flush();
        session.close();
    }

}