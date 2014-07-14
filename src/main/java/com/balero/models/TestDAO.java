package com.balero.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Unit Test Model Class
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class TestDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
     * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml
     */
    @Transactional
    public List<Test> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List users = session.createQuery("from Test").list();
        return users;
    }

    @Transactional
    public void add() {
        Session session = sessionFactory.openSession();
        Test test = new Test();
        Double n = Math.random();
        test.setName("user_" + n.toString());
        test.setEmail("user@" + n.toString());
        session.save(test);
        session.flush();
        session.close();
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Test test = new Test();
        Double n = Math.random();
        test.setId(id);
        session.delete(test);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
//        //query = "create table if not exists test (" +
//                "id int not null auto_increment," +
//                "name varchar (250) not null," +
//                "email varchar (250) not null," +
//                "PRIMARY KEY (id)" +
//                ");";
        //session.createSQLQuery(query).executeUpdate();
        Test test = new Test();
        test.setId(1);
        test.setName("Anibal Gomez");
        test.setEmail("anibalgomez@balerocms.com");
        session.save(test);
        session.flush();
    }


    /**
     * @deprecated makedb
     */
    @Transactional
    public void makedb() {
        String query;
        Session session = sessionFactory.getCurrentSession();
        query = "create database if not exists balero_cms";
        session.createSQLQuery(query).executeUpdate();
    }

    /**
     * @deprecated musedb
     */
    @Transactional
    public void usedb() {
        String query;
        Session session = sessionFactory.getCurrentSession();
        query = "use balero_cms";
        session.createSQLQuery(query).executeUpdate();
    }

}