package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.model.Book;

import java.util.List;

public interface BookService {

    void save(Book book);

    void delete(Book book);

    List<Book> findAll();

    List<Book> findByUserId(int userId);

    Book findByISBN(String isbn);

    Book findById(int id);
}
