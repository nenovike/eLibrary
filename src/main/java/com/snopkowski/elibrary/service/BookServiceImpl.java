package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.BookDao;
import com.snopkowski.elibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao dao;

    public void save(Book book) {
        dao.save(book);
    }

    public void delete(Book book) {
        dao.delete(book);
    }

    public List<Book> findAll() {
        return dao.findAll();
    }

    public List<Book> findByUserId(int userId) {
        return dao.findByUserId(userId);
    }

    public Book findByISBN(String isbn) {
        return dao.findByISBN(isbn);
    }

    public Book findById(int id) {
        return dao.findById(id);
    }
}
