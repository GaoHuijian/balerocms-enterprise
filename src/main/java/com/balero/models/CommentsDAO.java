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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class CommentsDAO {
    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public List<Comments> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Comments").list();
        return rows;
    }

    @Transactional
    public List<Comments> findById(int postId) {
        Session session = sessionFactory.getCurrentSession();
        List rows = session.createQuery("from Comments where postId = " + postId + "").list();
        return rows;
    }

    @Transactional
    public void addComment(String name, String comment, int postId) {
        Session session = sessionFactory.openSession();
        Comments comments = new Comments();
        comments.setName(name);
        comments.setComment(comment);
        comments.setDate(new Date());
        comments.setPostId(postId);
        session.save(comments);
        session.flush();
        session.close();
    }

    @Transactional
    public void deleteComment(int id) {
        Session session = sessionFactory.openSession();
        Comments comments = new Comments();
        comments.setId(id);
        session.delete(comments);
        session.flush();
        session.close();
    }

    @Transactional
    public void make() {
        Session session = sessionFactory.getCurrentSession();
        Comments comments = new Comments();
        comments.setId(1);
        comments.setName("Anon");
        comments.setComment("Great CMS. :-)");
        comments.setDate(new Date());
        comments.setPostId(1);
        session.save(comments);
        session.flush();
    }

}