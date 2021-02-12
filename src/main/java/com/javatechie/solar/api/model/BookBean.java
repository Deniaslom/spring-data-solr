package com.javatechie.solar.api.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;
import java.util.Objects;

@SolrDocument(collection = "books")
public class BookBean {

    @Id
    @Field("id")
    private String id;
    @Field("name")
    private String name;
    @Field("genre")
    private String genre;
    @Field("authors")
    private List<String> authors;
    @Field("publisher")
    private String publisher;
    @Field("isbn")
    private String isbn;
    @Field("lang")
    private String lang;
    @Field("year")
    private Integer year;
    @Field("mainContent")
    private String mainContent;

    public BookBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBean bookBean = (BookBean) o;
        return year == bookBean.year &&
                Objects.equals(id, bookBean.id) &&
                Objects.equals(name, bookBean.name) &&
                Objects.equals(genre, bookBean.genre) &&
                Objects.equals(authors, bookBean.authors) &&
                Objects.equals(publisher, bookBean.publisher) &&
                Objects.equals(isbn, bookBean.isbn) &&
                Objects.equals(lang, bookBean.lang) &&
                Objects.equals(mainContent, bookBean.mainContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genre, authors, publisher, isbn, lang, year, mainContent);
    }
}
