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
        List rows = session.createQuery("from Footer").list();
        return rows;
    }

    @Transactional
    public void updateFooter(int fid, String fContainer, String lang) {
        Session session = sessionFactory.openSession();
        Footer footer = new Footer();
        footer.setId(fid);
        footer.setContent(fContainer);
        session.update(footer);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        String query;
        Session session = sessionFactory.getCurrentSession();
//        query = "create table if not exists footer (" +
//                "id int not null auto_increment," +
//                "content longtext not null," +
//                "lang varchar (250) not null," +
//                "PRIMARY KEY (id)" +
//                ");";
//        session.createSQLQuery(query).executeUpdate();
        Footer footer = new Footer();
        footer.setId(1);
        footer.setContent("Copyright (c) 2013-2014 <a href=\"http://www.balerocms.com/\">BaleroCMS Enterprise</a>.");
        session.save(footer);
        session.flush();
    }

}