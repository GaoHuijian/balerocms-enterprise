package com.balero.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Footer Class
 *
 * Getters and Setters Methods
 */
@Entity
@Table(name = "footer")
public class Footer {

    @Id @GeneratedValue private int id;
    private String content;
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
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }

}