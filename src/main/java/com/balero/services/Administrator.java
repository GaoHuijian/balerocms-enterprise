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
