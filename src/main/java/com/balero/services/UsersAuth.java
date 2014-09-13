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

package com.balero.services;


import com.balero.controllers.LoginController;
import com.balero.models.Users;
import com.balero.models.UsersDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lastprophet on 25/06/14.
 */
public class UsersAuth {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    private String localUsername = null;
    private String localPassword = null;
    private String remoteUsername = null;
    private String remotePassword = null;

    private String hierarchy = "Anonymous";

    /**
     * Sets username and password (local)
     *
     * @param baleroAdmin
     * @param UsersDAO UsersDAO Object from models. Cant call directly.
     */
    public void setCredentials(String baleroAdmin, UsersDAO UsersDAO) {
        logger.debug("baleroAdmin: " + baleroAdmin);
        /**
         * Sets local credentials
         */
        if(!baleroAdmin.equals("init")) {
            String[] credentials = baleroAdmin.split(":");
            setLocalUsername(credentials[0]);
            setLocalPassword(credentials[1]);
        } else {
            logger.debug("baleroAdmin: empty " + baleroAdmin);
        }

        logger.debug("local username" + getLocalUsername());

        /**
         * User Levels
         */
        List<Users> users = null;

        logger.debug("Hierarchy: " + getHierarchy());
        try {

            if(baleroAdmin.equals("init") ||
                    baleroAdmin.equals("")) {
                throw new Exception("Empty cookie credentials");
            }

            // Caution: This part can cause
            // a null error return
            switch (getLocalUsername()) {
                case "admin":
                    // Administrator Level
                    users = UsersDAO.administrator();
                    setHierarchy("admin");
                    break;

                // User Level
                case "user":
                    users = UsersDAO.user();
                    setHierarchy("user");
                    break;


                default:
                    setHierarchy("anonymous");
            }

            /**
             * Sets remote credentials
             */
            for(com.balero.models.Users obj: users) {
                setRemoteUsername(obj.getUsername());
                setRemotePassword(obj.getPassword());
            }
            logger.debug("remote username: "+ getRemoteUsername());

        } catch (Exception e) {
            logger.debug(e.getMessage() + baleroAdmin);
        }

        logger.debug("Hierarchy:" + getHierarchy());

    }

    public boolean auth(String baleroAdmin, String usr, String pwd) {

        boolean result = false;

        if(!baleroAdmin.equals("init")) {
            // Extract credentials
            String[] credentials = baleroAdmin.split(":");

            String localUsr = credentials[0];
            String localPwd = credentials[1];

            // Set local credentials
            setLocalUsername(localUsr);
            setLocalPassword(localPwd);

            // Set remote credentials
            setRemoteUsername(usr);
            setRemotePassword(pwd);

            if(getLocalUsername().equals(getRemoteUsername())
                    && getLocalPassword().equals(getRemotePassword())) {
                result = true;
            } else {
                result = false;
            }

        }

        return result;

    }

    public String getLocalUsername() {
        return localUsername;
    }

    public void setLocalUsername(String localUsername) {
        this.localUsername = localUsername;
    }

    public String getLocalPassword() {
        return localPassword;
    }

    public void setLocalPassword(String localPassword) {
        this.localPassword = localPassword;
    }

    /**
     * Database getters and setters
     */

    public String getRemoteUsername() {
        return remoteUsername;
    }

    public void setRemoteUsername(String remoteUsername) {
        this.remoteUsername = remoteUsername;
    }

    public String getRemotePassword() {
        return remotePassword;
    }

    public void setRemotePassword(String remotePassword) {
        this.remotePassword = remotePassword;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
}
