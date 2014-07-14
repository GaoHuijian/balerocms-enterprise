package com.balero.models;


import javax.persistence.*;

/**
 * Content Class
 *
 * Getters and Setters Methods
 */
@Entity
@Table(name = "content")
public class Content {

    @Id @GeneratedValue
    private int id;
    @Column(columnDefinition="longtext")
    private String content;
    @Column(columnDefinition="longtext")
    private String fullcontent;
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
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

}