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

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SettingsDAO {
    @Autowired private SessionFactory sessionFactory;

    @Transactional
    public void save(long id, String sitename, String slogan, String url, String lang) {
        Session session = sessionFactory.getCurrentSession();
        Settings settings = new Settings();
        settings.setId(id);
        settings.setSitename(sitename);
        settings.setSlogan(slogan);
        settings.setUrl(url);
        settings.setLang(lang);
        session.update(settings);
        session.flush();
    }

    @Transactional
    public long settingsId() {
        long id = 1;
        Session session = sessionFactory.getCurrentSession();
        List<Settings> settings = session.createQuery("from Settings where lang = 'en'").list();

        for(Settings obj: settings) {
            id = obj.getId();
        }

        return id;

    }

    @Transactional
    public String siteName() {
        String sitename = null;
        Session session = sessionFactory.getCurrentSession();
        List<Settings> settings = session.createQuery("from Settings where lang = 'en'").list();

        for(Settings obj: settings) {
            sitename = obj.getSitename();
        }

        return sitename;

    }

    @Transactional
    public String siteSlogan() {
        String slogan = null;
        Session session = sessionFactory.getCurrentSession();
        List<Settings> settings = session.createQuery("from Settings where lang = 'en'").list();

        for(Settings obj: settings) {
            slogan = obj.getSlogan();
        }

        return slogan;

    }

    @Transactional
    public String siteURL() {
        String url = null;
        Session session = sessionFactory.getCurrentSession();
        List<Settings> settings = session.createQuery("from Settings where lang = 'en'").list();

        for(Settings obj: settings) {
            url = obj.getUrl();
        }

        return url;

    }

    @Transactional
    public void make() {
        Session session = sessionFactory.getCurrentSession();
        Settings settings = new Settings();
        settings.setId(1);
        settings.setSitename("Balero CMS");
        settings.setSlogan("Simple is powerful");
        settings.setUrl("http://www.balerocms.com/");
        settings.setLang("en");
        session.save(settings);
        session.flush();
    }
    
}