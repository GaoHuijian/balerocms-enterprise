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
     * SELECT * FROM 'pages' where id = '{id}'
     *
     * @return rows
     */
    @Transactional
    public List<Pages> findPage(int pageId) {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Pages where id = '" + pageId + "'").list();
        return rows;
    }

    /**
     * SELECT * FROM 'pages' where slug = '{string}'
     *
     * @return rows
     */
    @Transactional
    public List<Pages> findPageBySlug(String slug) {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Pages where slug = '" + slug + "'").list();
        return rows;
    }

    /**
     * INSERT INTO TABLE pages VALUES name, conteny, slug, lang
     *
     * @param content
     */
    @Transactional
    public void addPage(String name, String content, String slug) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setName(name);
        pages.setContent(content);
        pages.setSlug(slug);
        session.save(pages);
        session.flush();
        session.close();
    }

    /**
     * UPDATE QUERY
     *
     * @param id
     * @param content
     */
    @Transactional
    public void updatePage(int id, String name, String content, String slug) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setId(id);
        pages.setName(name);
        pages.setContent(content);
        pages.setSlug(slug);
        session.update(pages);
        session.flush();
        session.close();
    }

    /**
     * DELETE FROM query
     *
     * @param id
     */
    @Transactional
    public void deletePage(int id) {
        Session session = sessionFactory.openSession();
        Pages pages = new Pages();
        pages.setId(id);
        session.delete(pages);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        Session session = sessionFactory.getCurrentSession();
        Pages pages = new Pages();
        pages.setId(1);
        pages.setName("Example");
        pages.setContent("\n" +
                "<p>Lorem Ipsum is text that is commonly used in graphic design typefaces demonstrations or draft design to test the visual design before inserting the final text.</p>\n" +
                "\n" +
                "<p>Although currently has no sources to justify ótesis hip, professor of classical philology Richard McClintock says its use dates back to the early printers XVI.1 century Its use in some text editors well known in today has given the new popularity lorem ipsum.</p>\n" +
                "\n" +
                "<p>The text itself no sense, although it is not completely random, but derives from a text by Cicero in Latin language, whose words have been removed them syllables or letters. The meaning of the text does not matter, since it is just a test demostracióno,</p>\n");
        pages.setSlug("example-page");
        session.save(pages);
        session.flush();
    }

}