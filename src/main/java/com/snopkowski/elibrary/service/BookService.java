package com.snopkowski.elibrary.service;

import com.snopkowski.elibrary.dao.BookDao;
import com.snopkowski.elibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void save(BookDao bookDao) {
        bookRepository.save(bookDao);
    }

    public void delete(BookDao bookDao) {
        bookRepository.delete(bookDao);
    }

    public Integer findCurrentBorrow(int id) {
        return bookRepository.findCurrentBorrow(id);
    }

    public List<BookDao> findAll() {
        return bookRepository.findAll();
    }

    public List<BookDao> findByAnything(String search) {
        return bookRepository.findByAnything(search);
    }

    public List<BookDao> findByUserId(int userId) {
        return bookRepository.findByUserId(userId);
    }

    public BookDao findByISBN(String isbn) {
        return bookRepository.findByISBN(isbn);
    }

    public BookDao findById(int id) {
        return bookRepository.findById(id);
    }

    public List<BookDao> findByAnything(int id, String search) {
        return bookRepository.findByAnything(id, search);
    }
}
