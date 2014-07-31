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


/**
 * Created by lastprophet on 25/06/14.
 */
public class Administrator {

    private boolean admin = false;

    private String localUsername = null;
    private String localPassword = null;
    private String remoteUsername = null;
    private String remotePassword = null;

    public boolean isAdmin(String baleroAdmin, String usr, String pwd) {

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

            // compare
            allowAccess();
        } else {
            denyAccess();
        }

        if(this.admin == true) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Allow access
     *
     * @return boolean
     */
    public void allowAccess() {
        if(getLocalUsername().equals(getRemoteUsername())
                && getLocalPassword().equals(getRemotePassword())) {
            this.admin = true;
        }
    }

    /**
     * Deny access
     *
     * @return boolean
     */
    public void denyAccess() {
       this.admin = false;
    }

    public boolean getAccess() {
        return this.admin;
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

}
