package com.snopkowski.elibrary.dao;

import com.snopkowski.elibrary.model.Book;

import java.util.List;

public interface BookDao {

    void save(Book book);

    void delete(Book book);

    Book findById(int id);

    Book findByISBN(String isbn);

    List<Book> findAll();

    List<Book> findByUserId(int userId);

}

