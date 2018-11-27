package com.snopkowski.elibrary.dto;

import com.snopkowski.elibrary.dao.BookDao;
import com.snopkowski.elibrary.dao.GenreDao;

import java.util.HashSet;
import java.util.Set;

public class UserBookDto {

    private int id;
    private String name;
    private String isbn;
    private Set<String> genres = new HashSet<String>();
    private String author;
    private String publisher;

    public UserBookDto(BookDao bookDao) {
        this.id = bookDao.getId();
        this.name = bookDao.getName();
        this.isbn = bookDao.getIsbn();
        for (GenreDao genreDao : bookDao.getGenreDaos())
            genres.add(genreDao.getName());
        this.author = bookDao.getAuthorDao().getFullName();
        this.publisher = bookDao.getPublisherDao().getName();

    }

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

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
