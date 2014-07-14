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
public class UsersDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     * @Transactional annotation below will trigger Spring Hibernate transaction manager to automatically create
     * a hibernate session. See src/main/webapp/WEB-INF/servlet-context.xml
     */
    @Transactional
    public List<Users> administrator() {
        Session session = sessionFactory.getCurrentSession();
        List users = session.createQuery("from Users where auth = 'god'").list();
        return users;
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
//        query = "create table if not exists users (" +
//                "id int not null auto_increment," +
//                "username varchar (250) not null," +
//                "password varchar (250) not null," +
//                "auth varchar (250) not null," +
//                "PRIMARY KEY (id)" +
//                ");";
//        session.createSQLQuery(query).executeUpdate();
        Users users = new Users();
        users.setId(1);
        users.setUsername("demo");
        users.setPassword("demo");
        users.setAuth("god");
        session.save(users);
        session.flush();
    }

}