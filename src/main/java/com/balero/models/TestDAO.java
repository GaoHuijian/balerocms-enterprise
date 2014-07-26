/**
 * <pre>
 * Balero CMS Enterprise Edition is free and open source software under MIT License.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 <Balero CMS All Rights Reserved>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * <a href="http://www.balerocms.com">BaleroCMS.com</a>
 * </pre>
 *
 * @author      Anibal Gomez
 * @version     1.0
 * @since       1.0
 */

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
        Session session = sessionFactory.getCurrentSession();
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