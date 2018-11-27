package com.snopkowski.elibrary.dto;

import com.snopkowski.elibrary.dao.GenreDao;

import java.util.HashSet;
import java.util.Set;

public class NewBookDto {

    private int id;

    private String name;

    private String isbn;

    private Set<GenreDao> genreDaos = new HashSet<GenreDao>();

    private String authorFirstName;

    private String authorLastName;


    private String publisher;

    public int getId() {
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<GenreDao> getGenreDaos() {
        return genreDaos;
    }

    public void setGenreDaos(Set<GenreDao> genreDaos) {
        this.genreDaos = genreDaos;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
