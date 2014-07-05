package com.balero.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Content Class
 *
 * Getters and Setters Methods
 */
@Entity
@Table(name = "content")
public class Content {

    @Id @GeneratedValue private int id;
    private String content;
    private String fullcontent;
    private String body;
    private String lang;

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFullContent() {
        return fullcontent;
    }
    public void setFullContent(String fullcontent) {
        this.fullcontent = fullcontent;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

}