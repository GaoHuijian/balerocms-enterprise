package com.balero.models;


import javax.persistence.*;

/**
 * Pages Class
 * Getters and Setters Methods
 *
 * @author Anibal Gomez
 *
 */
@Entity
@Table(name = "pages")
public class Pages {

    @Id @GeneratedValue
    private int id;
    private String name;
    @Column(columnDefinition="longtext")
    private String content;
    private String lang;

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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