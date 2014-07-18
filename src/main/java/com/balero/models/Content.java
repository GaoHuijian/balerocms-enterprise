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
    private String full;
    private String slug;
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
    public String getFull() {
        return full;
    }
    public void setFull(String full) {
        this.full = full;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

}