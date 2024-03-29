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
public class UsersDAO {
    @Autowired private SessionFactory sessionFactory;

    /**
     *
     * @return usr
     */
    @Transactional
    public String usrAdmin() {

        String usr = null;

        Session session = sessionFactory.getCurrentSession();
        // Do the rick  List<Users> Object
        List<Users> rows = session.createQuery("from Users where auth = 'god'").list();

        for(Users objUsers: rows) {
            usr = objUsers.getUsername();
        }

        return usr;
    }

    /**
     *
     * @return pwd
     */
    @Transactional
    public String pwdAdmin() {

        String pwd = null;

        Session session = sessionFactory.getCurrentSession();
        List<Users> rows = session.createQuery("from Users where auth = 'god'").list();

        for(Users objUsers: rows) {
            pwd = objUsers.getPassword();
        }

        return pwd;
    }

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
    public List<Users> user() {
        Session session = sessionFactory.getCurrentSession();
        List users = session.createQuery("from Users where auth = 'user'").list();
        return users;
    }

    @Transactional
    public void administratorCredentials(String password) {
        Session session = sessionFactory.getCurrentSession();
        Users users = new Users();
        users.setId(1); // Admin credentials id = 1
        users.setUsername("admin");
        users.setPassword(password);
        users.setAuth("god");
        session.update(users);
        session.flush();
    }

    @Transactional
    public void register(String username, String password,
                         String name, String lastname,
                         String email) {
        Session session = sessionFactory.getCurrentSession();
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        users.setName(name);
        users.setLastname(lastname);
        users.setEmail(email);
        users.setAuth("user");
        session.save(users);
        session.flush();
    }

    @Transactional
    public void make() {
        Session session = sessionFactory.getCurrentSession();
        Users users = new Users();
        users.setId(1);
        users.setUsername("admin");
        users.setPassword("admin");
        users.setAuth("god");
        session.save(users);
        session.flush();
    }

}